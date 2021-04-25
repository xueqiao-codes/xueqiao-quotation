/*
 * contract_mapping.h
 *
 *  Created on: 2017年11月30日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_COMMON_CONTRACT_MAPPING_H_
#define QUOTATION_ACCESS_COMMON_CONTRACT_MAPPING_H_

#include <atomic>
#include <memory>

#include "quotation_common/thrift/quotation_item_types.h"
#include "spdlog/fmt/ostr.h"
#include "contract_standard_types.h"


namespace xueqiao {
namespace quotation {
namespace access {

struct CommodityMappingEntry {
    ::xueqiao::contract::standard::CommodityMapping mapping;
    ::xueqiao::contract::standard::SledCommodity commodity;
};

struct CommodityMappingOption {
    std::string brokerExchangeCode;    // 券商交易所代码
    std::string brokerCommodityType;   // 券商商品类型
    std::string brokerCommodityCode;   // 券商商品代码
    ::xueqiao::contract::standard::TechPlatform::type brokerTechPlatform; // 券商技术平台
    int32_t sledBrokerId;       // 雪橇对应的券商ID

    template<typename OStream>
    friend OStream& operator<<(OStream& os, const CommodityMappingOption& c) {
        return os << "CommodityMappingOption[brokerExchangeCode=" << c.brokerExchangeCode
                  << ", brokerCommodityType=" << c.brokerCommodityType
                  << ", brokerCommodityCode=" << c.brokerCommodityCode
                  << ", brokerTechPlatform=" << c.brokerTechPlatform
                  << ", sledBrokerId=" << c.sledBrokerId
                  << "]";
    }
};


class ContractMapping {
public:
    static ContractMapping& Global();
    ~ContractMapping();

    std::shared_ptr<CommodityMappingEntry> getCommodityMapping(
            const CommodityMappingOption& option);

    std::shared_ptr<::xueqiao::contract::standard::CommodityMapping> getCommodityMappingForSure(
            const int32_t& sled_commodity_id);

protected:
    ContractMapping();

    void lockCache();
    void unlockCache();

    void setCache(const std::string& key, std::shared_ptr<CommodityMappingEntry> mapping_entry);
    inline void setCacheEmpty(const std::string& key) {
        setCache(key, std::shared_ptr<CommodityMappingEntry>());
    }
    std::shared_ptr<CommodityMappingEntry> getCache(const std::string& cache_key, bool& need_update);

    std::string getKey(const CommodityMappingOption& option);

    std::string qconf_idc_name_;

    std::atomic_flag lock_flag_ = ATOMIC_FLAG_INIT;

    struct CommodityMappingCacheEntry {
        long cached_timestampms = 0;
        std::shared_ptr<CommodityMappingEntry> entry;
    };

    std::map<std::string, std::shared_ptr<CommodityMappingCacheEntry>> commodity_mapping_cache_;
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_CONTRACT_MAPPING_H_ */
