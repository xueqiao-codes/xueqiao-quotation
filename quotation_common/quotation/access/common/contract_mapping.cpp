/*
 * contract_mapping.cpp
 *
 *  Created on: 2017年11月30日
 *      Author: wileywang
 */
#include "contract_mapping.h"

#include <boost/lexical_cast.hpp>
#include <mutex>

#include "base/code_defense.h"
#include "base/hash.h"
#include "base/url_util.h"
#include "base/time_helper.h"
#include "qconf.h"
#include "thrift/protocol/TJSONProtocol.h"
#include "contract_online_dao_service_stub.h"
#include "ContractOnlineDaoService_variables.h"

namespace xueqiao {
namespace quotation {
namespace access {

static const int NOT_FOUND_CACHE_TIMEMS = 5*60*1000; // 五分钟更新一次查询不存在的缓存

static std::unique_ptr<ContractMapping> s_instance;

static std::string contract_online_socket_file;

static void initContractOnlineSocketFileIfNeeded() {
    if (contract_online_socket_file.empty()) {
        static std::mutex lock;
        lock.lock();
        if (contract_online_socket_file.empty()) {
            contract_online_socket_file.append("/data/run/service_")
                               .append(boost::lexical_cast<std::string>(
                                       ::xueqiao::contract::online::dao::ContractOnlineDaoService_service_key))
                               .append(".sock");
        }
        lock.unlock();
    }
}

ContractMapping& ContractMapping::Global() {
    static std::mutex lock;
    if (!s_instance) {
        lock.lock();
        if (!s_instance) {
            s_instance.reset(new ContractMapping());
        }
        lock.unlock();
    }
    return *s_instance;
}


ContractMapping::ContractMapping() {
}

ContractMapping::~ContractMapping() {
}

void ContractMapping::lockCache() {
    while(std::atomic_flag_test_and_set_explicit(
                                &lock_flag_, std::memory_order_seq_cst));
}

void ContractMapping::unlockCache() {
    lock_flag_.clear();
}

void ContractMapping::setCache(const std::string& cache_key, std::shared_ptr<CommodityMappingEntry> mapping_entry) {
    std::shared_ptr<CommodityMappingCacheEntry> cache_entry(new CommodityMappingCacheEntry());
    cache_entry->cached_timestampms = soldier::base::NowInMilliSeconds();
    cache_entry->entry = mapping_entry;
    lockCache();
    commodity_mapping_cache_[cache_key] = cache_entry;
    unlockCache();
}

std::string ContractMapping::getKey(const CommodityMappingOption& option) {
    if (option.brokerExchangeCode.empty()) {
        return "";
    }
    if (option.brokerCommodityCode.empty()) {
        return "";
    }
    if (option.brokerCommodityType.empty()) {
        return "";
    }
    std::string cache_key;
    cache_key.append(boost::lexical_cast<std::string>(option.brokerTechPlatform))
             .append("_").append(option.brokerExchangeCode)
             .append("_").append(option.brokerCommodityType)
             .append("_").append(option.brokerCommodityCode)
             .append("_").append(boost::lexical_cast<std::string>(option.sledBrokerId));
    return cache_key;
}

std::shared_ptr<CommodityMappingEntry> ContractMapping::getCache(const std::string& cache_key, bool& need_update) {
    lockCache();
    auto cache_it = commodity_mapping_cache_.find(cache_key);
    if (cache_it != commodity_mapping_cache_.end()) {
        if (cache_it->second->entry) {
            unlockCache();
            need_update = false;
            return cache_it->second->entry;
        }
        if (soldier::base::NowInMilliSeconds() <= cache_it->second->cached_timestampms + NOT_FOUND_CACHE_TIMEMS) {
            unlockCache();
            need_update = false;
            return cache_it->second->entry;
        }
    }
    unlockCache();
    need_update = true;
    return std::shared_ptr<CommodityMappingEntry>();
}

static void initContractOnlineDaoServiceStub(::xueqiao::contract::online::dao::ContractOnlineDaoServiceStub& stub) {
    const char* env_host_addr = getenv("HOST_ADDR");
    if (env_host_addr != NULL && 0 != env_host_addr[0]) {
        stub.setPeerAddr(env_host_addr);
    } else {
        initContractOnlineSocketFileIfNeeded();
        stub.setSocketFile(contract_online_socket_file);
    }
}

std::shared_ptr<CommodityMappingEntry> ContractMapping::getCommodityMapping(
            const CommodityMappingOption& option) {
    std::string cache_key = getKey(option);
    if (cache_key.empty()) {
        APPLOG_WARN("Unaccept option={}", option);
        return std::shared_ptr<CommodityMappingEntry>();
    }
    bool need_update = false;
    std::shared_ptr<CommodityMappingEntry> cache_entry = getCache(cache_key, need_update);
    if (!need_update) {
        return cache_entry;
    }

    CHECK(!cache_entry);

    ::xueqiao::contract::online::dao::ContractOnlineDaoServiceStub stub;
    initContractOnlineDaoServiceStub(stub);

    ::xueqiao::contract::standard::ReqCommodityMappingOption mapping_option;
    mapping_option.__set_brokerEntryId(option.sledBrokerId);
    mapping_option.__set_commodityCode(option.brokerCommodityCode);
    mapping_option.__set_exchange(option.brokerExchangeCode);
    mapping_option.__set_commodityType(option.brokerCommodityType);
    mapping_option.__set_techPlatform(option.brokerTechPlatform);

    ::xueqiao::contract::standard::CommodityMappingPage mapping_page;
    try {
        STUB_SYNC_INVOKE(stub, reqCommodityMapping, mapping_page, mapping_option, 0, 10);
    } catch(::apache::thrift::TException& e) {
        APPLOG_ERROR("failed to found commodity mapping, e.what()={}", e.what());
        return std::shared_ptr<CommodityMappingEntry>();
    }

    if (mapping_page.page.empty()) {
        setCacheEmpty(cache_key);
        return std::shared_ptr<CommodityMappingEntry>();
    }

    if (mapping_page.page.size() > 1) {
        APPLOG_ERROR("mapping size > 1 for sledBrokerId={}, brokerCommodityCode={}"
                ", brokerExchangeCode={}, brokerCommodityType={}, brokerTechPlatform={}"
                , option.sledBrokerId, option.brokerCommodityCode
                , option.brokerExchangeCode, option.brokerCommodityType
                , option.brokerTechPlatform);
        return std::shared_ptr<CommodityMappingEntry>();
    }

    std::shared_ptr<CommodityMappingEntry> mapping_entry(new CommodityMappingEntry());
    mapping_entry->mapping = mapping_page.page[0];

    ::xueqiao::contract::standard::ReqSledCommodityOption commodity_option;
    std::vector<int32_t> commodity_ids;
    commodity_ids.push_back(mapping_entry->mapping.sledCommodityId);
    commodity_option.__set_sledCommodityIdList(commodity_ids);

    ::xueqiao::contract::standard::SledCommodityPage commodity_page;
    try {
        STUB_SYNC_INVOKE(stub, reqSledCommodity, commodity_page, commodity_option, 0, 10);
    } catch(::apache::thrift::TException& e) {
        APPLOG_ERROR("failed to found commodity for sledCommodityId={}, e.what()={}"
                , mapping_entry->mapping.sledCommodityId
                , e.what());
        return std::shared_ptr<CommodityMappingEntry>();
    }

    if (commodity_page.page.size() != 1) {
        APPLOG_ERROR("found {} commodity entry for sledCommodityId={}"
                , commodity_page.page.size(), mapping_entry->mapping.sledCommodityId);
        return std::shared_ptr<CommodityMappingEntry>();
    }

    mapping_entry->commodity = commodity_page.page[0];
    setCache(cache_key, mapping_entry);
    return mapping_entry;
}

std::shared_ptr<::xueqiao::contract::standard::CommodityMapping> ContractMapping::getCommodityMappingForSure(const int32_t& sled_commodity_id) {
    ::xueqiao::contract::online::dao::ContractOnlineDaoServiceStub stub;
    initContractOnlineDaoServiceStub(stub);

    ::xueqiao::contract::standard::ReqCommodityMappingOption mapping_option;
    mapping_option.sledCommodityIdList.push_back(sled_commodity_id);
    mapping_option.__isset.sledCommodityIdList = true;

    ::xueqiao::contract::standard::CommodityMappingPage mapping_page;
    while(true) {
        try {
            STUB_SYNC_INVOKE(stub, reqCommodityMapping, mapping_page, mapping_option, 0, 10);
            break;
        } catch(::apache::thrift::TException& e) {
            APPLOG_ERROR("failed to call reqCommodityMapping, e.what()={}", e.what());
            std::this_thread::sleep_for(std::chrono::seconds(1));
        }
    }

    if (mapping_page.page.empty()) {
        return std::shared_ptr<::xueqiao::contract::standard::CommodityMapping>();
    }

    if (mapping_page.page.size() > 1) {
        APPLOG_ERROR("mapping size > 1 for sledCommodityId={}" , sled_commodity_id);
        return std::shared_ptr<::xueqiao::contract::standard::CommodityMapping>();
    }

    return std::shared_ptr<::xueqiao::contract::standard::CommodityMapping>(
            new ::xueqiao::contract::standard::CommodityMapping(mapping_page.page[0]));
}

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



