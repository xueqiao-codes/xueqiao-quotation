/*
 * esunny_quote_worker.cpp
 *
 *  Created on: 2017年8月31日
 *      Author: wileywang
 */
#include "quotation/access/esunny/esunny_quote_worker.h"

#include <sys/stat.h>
#include <boost/lexical_cast.hpp>
#include "base/app_log.h"
#include "base/code_defense.h"
#include "contract_online_dao_service_stub.h"
#include "contract_mapping.h"
#include "ContractConvertor.h"
#include "TapAPIError.h"
#include "accessstate_holder.h"
#include "effective_reporter.h"

using namespace xueqiao::quotation::plan::bo;

namespace xueqiao {
namespace quotation {
namespace access {

ESunnyQuoteWorker::ESunnyQuoteWorker(
        const int64_t& quotation_account_id
        , const std::string& base_logdir
        , const std::string& quote_addr
        , const std::string& user_name
        , const std::string& user_password
        , const std::string& auth_code
        , const std::shared_ptr<IESunnyQuoteHandler>& quote_handler)
    : quotation_account_id_(quotation_account_id)
     , base_logdir_(base_logdir)
     , quote_addr_(quote_addr)
     , user_name_(user_name)
     , user_password_(user_password)
     , auth_code_(auth_code) {
    std::string log_dir = base_logdir_ + "/" + boost::lexical_cast<std::string>(quotation_account_id_);
    if (0 != ::access(log_dir.c_str(), 0)) {
        mkdir(log_dir.c_str(), 0755);
    }
    api_proxy_.reset(new ESunnyQuoteProxy(log_dir, quote_addr_, auth_code_, quote_handler));
}

void ESunnyQuoteWorker::start() {
    scmanager_listener_.reset(new ESunnySCItemListener(this));
    scmanager_.reset(new SCItemManager(scmanager_listener_, quotation_account_id_));

    worker_thread_.reset(new soldier::base::TaskThread());

    is_start_successful_ = false;
    worker_thread_->postTask(&ESunnyQuoteWorker::onStart, this);
}

ESunnyQuoteWorker::~ESunnyQuoteWorker() {
    worker_thread_.reset();
    api_proxy_.reset();
}

void ESunnyQuoteWorker::onStart() {
    QuotationAccessStateHolder::Global().setAccessStateInvalid("登录连接中...");

    ESunnyLoginReq loginReq;
    loginReq.userName = user_name_;
    loginReq.userPasswd = user_password_;

    ESunnyLoginResp loginResp;
    int ret = api_proxy_->loginReady(loginReq, loginResp);
    if (ret != 0) {
        APPLOG_ERROR("login failed to {}, using {}:{} , ret={}", quote_addr_, user_name_, user_password_, ret);
        std::this_thread::sleep_for(std::chrono::seconds(1));
        worker_thread_->postTask(&ESunnyQuoteWorker::onStart, this);
        return ;
    }
    if (loginResp.errorCode != 0) {
        APPLOG_ERROR("login failed to {}, using {}:{} , errorCode={}", quote_addr_, user_name_, user_password_, loginResp.errorCode);
        if (loginResp.errorCode == TAPIERROR_LOGIN_PASS) {
            QuotationAccessStateHolder::Global().setAccessStateInvalid("用户名密码错误");
            EffectiveReporter::Global().setAccountInfoInvalid(loginResp.errorCode);
        } else if (loginResp.errorCode == TAPIERROR_LOGIN_USER){
            QuotationAccessStateHolder::Global().setAccessStateInvalid("登陆用户不存在");
            EffectiveReporter::Global().setAccountInfoInvalid(loginResp.errorCode);
        } else if (loginResp.errorCode == TAPIERROR_LOGIN_FORCE) {
            QuotationAccessStateHolder::Global().setAccessStateInvalid("需要强制修改密码");
            EffectiveReporter::Global().setAccountInfoInvalid(loginResp.errorCode);
        } else if (loginResp.errorCode == TAPIERROR_LOGIN_STATE) {
            QuotationAccessStateHolder::Global().setAccessStateInvalid("登录状态禁止登陆");
        } else if (loginResp.errorCode == TAPIERROR_LOGIN_DDA) {
            QuotationAccessStateHolder::Global().setAccessStateInvalid("登录需要验证码");
        } else {
            QuotationAccessStateHolder::Global().setAccessStateInvalid("登陆异常, 错误码为" + boost::lexical_cast<std::string>(loginResp.errorCode));
        }
        return ;
    }

    APPLOG_INFO("login success to {}, using {}:{}", quote_addr_, user_name_, user_password_);

    QuotationAccessStateHolder::Global().setAccessStateValid();
    is_start_successful_ = true;
    scmanager_->start();
}

void ESunnyQuoteWorker::onSubscribeContractInited(const std::vector<TapAPIContract>& subscribe_contracts) {
    worker_thread_->postTask(&ESunnyQuoteWorker::onHandleSubscribe
            , this
            , std::vector<TapAPIContract>()
            , subscribe_contracts);
}

void ESunnyQuoteWorker::onSubscribeContractChanged(const std::vector<TapAPIContract>& removed_contracts
            , const std::vector<TapAPIContract>& added_contracts) {
    worker_thread_->postTask(&ESunnyQuoteWorker::onHandleSubscribe
            , this
            , removed_contracts
            , added_contracts);
}

void ESunnyQuoteWorker::sendUpsideHeartBeat() {
    if (!is_start_successful_) {
        APPLOG_INFO("sendUpsideHeartBeat, but start has not successful!");
        return ;
    }

    worker_thread_->postTask(&ESunnyQuoteWorker::onHandleHeartBeat, this);
}

void ESunnyQuoteWorker::onHandleHeartBeat() {
    if (subscribed_contracts_.empty() && retry_subscribe_contracts_.empty()) {
        APPLOG_INFO("onHandleHeartBeat, but subscribed_contracts_ && retry_subscribe_contracts_ is empty!");
        return ;
    }

    for (const auto& it : subscribed_contracts_) {
        // 从订阅的商品中，再次发起订阅
        ESunnySubscribeQuoteReq subscribeReq;
        ESunnySubscribeQuoteResp subscribeResp;
        subscribeReq.contract = it.second;
        int ret = api_proxy_->subscribeQuote(subscribeReq, subscribeResp);
        APPLOG_INFO("onHandleHeartBeat subscribeQuote  ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, ret={}, respCode={}"
                , subscribeReq.contract.Commodity.ExchangeNo
                , subscribeReq.contract.Commodity.CommodityType
                , subscribeReq.contract.Commodity.CommodityNo
                , subscribeReq.contract.ContractNo1
                , subscribeReq.contract.ContractNo2
                , ret,
                subscribeResp.errorCode);
        break;
    }
}

void ESunnyQuoteWorker::subscribeContract(const TapAPIContract& contract) {
    ESunnySubscribeQuoteReq subscribeReq;
    ESunnySubscribeQuoteResp subscribeResp;
    subscribeReq.contract = contract;

    ESunnyQuoteWorker::FillContractResult contract_result = fillContract(subscribeReq.contract);
    if (contract_result == FillContractResult::CONTRACT_NOT_EXIST) {
        APPLOG_ERROR("subscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, contract info not found in esunny"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2);
        removeSubscribeRetry(subscribeReq.contract);
        return ;
    } else if (contract_result == FillContractResult::CONTRACT_NOT_SURE) {
        APPLOG_ERROR("subscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, contract info not sure"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2);
        addSubscribeRetry(subscribeReq.contract);
        return ;
    }

    int ret = api_proxy_->subscribeQuote(subscribeReq, subscribeResp);
    if (ret != 0) {
        APPLOG_ERROR("subscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, ret = {}"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2
                , ret);
        addSubscribeRetry(subscribeReq.contract);
        return ;
    }

    if (subscribeResp.errorCode != 0) {
        APPLOG_ERROR("subscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                    ", ContractNo1={}, ContractNo2={}, errorCode = {}"
                    , contract.Commodity.ExchangeNo
                    , contract.Commodity.CommodityType
                    , contract.Commodity.CommodityNo
                    , contract.ContractNo1
                    , contract.ContractNo2
                    , subscribeResp.errorCode);
        addSubscribeRetry(subscribeReq.contract);
        return ;
    }

    APPLOG_INFO("subscribe success, ExchangeNo={}, CommodityType={}, CommodityNo={}, ContractNo1={}, ContractNo2={}"
            , contract.Commodity.ExchangeNo
            , contract.Commodity.CommodityType
            , contract.Commodity.CommodityNo
            , contract.ContractNo1
            , contract.ContractNo2);
    subscribed_contracts_[getKey(subscribeReq.contract)] = subscribeReq.contract;
    removeSubscribeRetry(subscribeReq.contract);
}

void ESunnyQuoteWorker::unsubscribeContract(const TapAPIContract& contract) {
    if (subscribed_contracts_.find(getKey(contract)) == subscribed_contracts_.end()) {
        // 未订阅
        retry_subscribe_contracts_.erase(getKey(contract));
        return ;
    }

    ESunnyUnSubscribeQuoteReq unsubscribeReq;
    ESunnyUnSubscribeQuoteResp unsubscribeResp;
    unsubscribeReq.contract = contract;

    FillContractResult contract_result = fillContract(unsubscribeReq.contract);
    if (contract_result == FillContractResult::CONTRACT_NOT_EXIST) {
        APPLOG_ERROR("unsubscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, not found for contract info"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2);
        removeSubscribeRetry(unsubscribeReq.contract);
        return ;
    } else if (contract_result == FillContractResult::CONTRACT_NOT_SURE) {
        APPLOG_FATAL("unsubscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, contract not sure"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2);
        return ;
    }

    int ret = api_proxy_->unsubscribeQuote(unsubscribeReq, unsubscribeResp);
    if (ret != 0) {
        APPLOG_FATAL("unsubscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, ret = {}"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2
                , ret);
        return ;
    }

    if (unsubscribeResp.errorCode != 0) {
        APPLOG_FATAL("unsubscribe failed, ExchangeNo={}, CommodityType={}, CommodityNo={}"
                ", ContractNo1={}, ContractNo2={}, errorCode = {}"
                , contract.Commodity.ExchangeNo
                , contract.Commodity.CommodityType
                , contract.Commodity.CommodityNo
                , contract.ContractNo1
                , contract.ContractNo2
                , unsubscribeResp.errorCode);
        return ;
    }

    APPLOG_INFO("unsubscribe success, ExchangeNo={}, CommodityType={}, CommodityNo={}"
            ", ContractNo1={}, ContractNo2={}"
            , contract.Commodity.ExchangeNo
            , contract.Commodity.CommodityType
            , contract.Commodity.CommodityNo
            , contract.ContractNo1
            , contract.ContractNo2
            , unsubscribeResp.errorCode);

    subscribed_contracts_.erase(getKey(unsubscribeReq.contract));
    removeSubscribeRetry(unsubscribeReq.contract);
}

std::string ESunnyQuoteWorker::getKey(const TapAPIContract& contract) {
    std::stringstream ss;
    ss << contract.Commodity.ExchangeNo
       << "|" << contract.Commodity.CommodityType
       << "|" << contract.Commodity.CommodityNo
       << "|" << contract.ContractNo1;
    if (contract.ContractNo2[0] != 0) {
        ss << "&" << contract.ContractNo2;
    }
    return ss.str();
}

void ESunnyQuoteWorker::addSubscribeRetry(const TapAPIContract& contract) {
    retry_subscribe_contracts_[getKey(contract)] = contract;
}

void ESunnyQuoteWorker::removeSubscribeRetry(const TapAPIContract& contract) {
    retry_subscribe_contracts_.erase(getKey(contract));
}

bool ESunnyQuoteWorker::containsSubscribeRetry(const TapAPIContract& contract) {
    return retry_subscribe_contracts_.end() != retry_subscribe_contracts_.find(getKey(contract));
}

void ESunnyQuoteWorker::onHandleRetry() {
    if (retry_subscribe_contracts_.empty()) {
        return ;
    }

    std::this_thread::sleep_for(std::chrono::seconds(10));
    std::map<std::string, TapAPIContract> need_retry_contracts(retry_subscribe_contracts_);

    APPLOG_INFO("onHandleRetry, size={}", need_retry_contracts.size());
    for (const auto& it : need_retry_contracts) {
        subscribeContract(it.second);
    }

    worker_thread_->postTask(&ESunnyQuoteWorker::onHandleRetry, this);
}

ESunnyQuoteWorker::FillContractResult ESunnyQuoteWorker::fillContract(TapAPIContract& input) {
    memset(input.StrikePrice1, 0, sizeof(input.StrikePrice1));
    input.CallOrPutFlag1 = TAPI_CALLPUT_FLAG_NONE;
    memset(input.StrikePrice2, 0, sizeof(input.StrikePrice2));
    input.CallOrPutFlag2 = TAPI_CALLPUT_FLAG_NONE;

    return FillContractResult::CONTRACT_SELF_FILL;

    // 原有采用易盛API确认合约的情况，但是易盛查询合约的情况不明，目前只支持期货，采用自己填充合约信息的方式
//    auto cache_it = full_contract_cache_.find(getKey(input));
//    if (cache_it != full_contract_cache_.end()) {
//        input = cache_it->second;
//        return FillContractResult::CONTRACT_EXIST;
//    }
//
//    // 对易盛的API实在无语，一切行为无明确的说明行为，异常没有约束
//    // 有没有可能实际API没有成功，但是API行为却是成功的呢？ 这个不确定
//    // 我们认为我们的合约，应该是确认存在的! 对于一切易盛的API导致和我们系统不一致的行为，全部采用不确定策略
//    // 目前发现存在，商品存在，但是可能出现无法找到合约的形式
//    ESunnyQueryContractReq req;
//    ESunnyQueryContractResp resp;
//
//    req.commodity.reset(new TapAPICommodity(input.Commodity));
//    int ret = api_proxy_->queryContracts(req, resp);
//    if (ret != 0) {
//        APPLOG_ERROR("query contracts for commodity ExchangeNo={}, CommodityType={}, CommodityNo={} failed, ret = {}"
//                    , req.commodity->ExchangeNo, req.commodity->CommodityType, req.commodity->CommodityNo
//                    , ret);
//        return FillContractResult::CONTRACT_NOT_SURE;
//    }
//
//    if (resp.errorCode != 0) {
//        APPLOG_ERROR("query contracts for commodity ExchangeNo={}, CommodityType={}, CommodityNo={} failed, errorCode = {}"
//                    , req.commodity->ExchangeNo, req.commodity->CommodityType, req.commodity->CommodityNo
//                    , resp.errorCode);
//
//        return FillContractResult::CONTRACT_NOT_SURE;
//    }
//
//    if (resp.rspContracts.empty()) {
//        APPLOG_ERROR("query contracts for commodity ExchangeNo={}, CommodityType={}, CommodityNo={}"
//                ", but resp.rspContracts empty"
//                , input.Commodity.ExchangeNo, input.Commodity.CommodityType
//                , input.Commodity.CommodityNo);
//
//        return FillContractResult::CONTRACT_NOT_SURE;
//    }
//
//    for (const auto& contract_info : resp.rspContracts) {
//        if (0 != strcmp(contract_info.Contract.Commodity.ExchangeNo, input.Commodity.ExchangeNo)
//                || 0 != strcmp(contract_info.Contract.Commodity.CommodityNo, input.Commodity.CommodityNo)
//                || contract_info.Contract.Commodity.CommodityType != input.Commodity.CommodityType) {
//            APPLOG_ERROR("query contracts for commodity ExchangeNo={}, CommodityType={}, CommodityNo={}"
//                    ", but has unsame commodity! Fuck esunny!!!"
//                    , input.Commodity.ExchangeNo, input.Commodity.CommodityType
//                    , input.Commodity.CommodityNo);
//            continue;
//        }
//
//
//        if (0 == strcmp(contract_info.Contract.ContractNo1, input.ContractNo1)
//                && 0 == strcmp(contract_info.Contract.ContractNo2, input.ContractNo2)) {
//            input = contract_info.Contract;
//
//            APPLOG_INFO("ExchangeNo={}, CommodityType={}, CommodityNo={}, ContractNo1={}"
//                    ", StrikePrice1={}, CallOrPutFlag1={},  ContractNo2={}, StrikePrice2={}, CallOrPutFlag2={}"
//                    , input.Commodity.ExchangeNo, input.Commodity.CommodityType
//                    , input.Commodity.CommodityNo, input.ContractNo1
//                    , input.StrikePrice1, input.CallOrPutFlag1
//                    , input.ContractNo2, input.StrikePrice2
//                    , input.CallOrPutFlag2);
//
//            full_contract_cache_[getKey(contract_info.Contract)] = contract_info.Contract;
//
//            return FillContractResult::CONTRACT_EXIST;
//        }
//    }
//
//    APPLOG_INFO("found contracts, but no contract info found for ExchangeNo={}"
//            ", CommodityType={}, CommodityNo={}, ContractNo1={}, ContractNo2={}"
//            , input.Commodity.ExchangeNo
//            , input.Commodity.CommodityType
//            , input.Commodity.CommodityNo
//            , input.ContractNo1
//            , input.ContractNo2);
//    return FillContractResult::CONTRACT_NOT_SURE;
}

void ESunnyQuoteWorker::onHandleSubscribe(const std::vector<TapAPIContract>& removed_contracts
        , const std::vector<TapAPIContract>& added_contracts) {
    APPLOG_INFO("onHandleSubscribe remove_contracts.size={}, added_contracts.size={}"
            , removed_contracts.size(), added_contracts.size());

    for (const auto& op_rm_contract : removed_contracts) {
        unsubscribeContract(op_rm_contract);
    }

    for (const auto& op_add_contract : added_contracts) {
        subscribeContract(op_add_contract);
    }

    // 驱动重试队列
    worker_thread_->postTask(&ESunnyQuoteWorker::onHandleRetry, this);
}


ESunnySCItemListener::ESunnySCItemListener(IESunnySCItemOpCallback* op_callback)
    : op_callback_(op_callback) {
    CHECK(op_callback_);
}

void ESunnySCItemListener::onItemsInited(
        const std::map<std::string, SubscribeContractItem>& items) noexcept {
    APPLOG_INFO("onItemsInited items.size()={}", items.size());

    std::vector<TapAPIContract> subscribe_contracts;
    scitems2Contracts(items, subscribe_contracts);
    op_callback_->onSubscribeContractInited(subscribe_contracts);
}

void ESunnySCItemListener::onItemsChanged(
        const std::map<std::string, SubscribeContractItem>& removed_items
        , const std::map<std::string, SubscribeContractItem>& added_items) noexcept {
    std::vector<TapAPIContract> removed_contracts, added_contracts;
    scitems2Contracts(removed_items, removed_contracts);
    scitems2Contracts(added_items, added_contracts);
    op_callback_->onSubscribeContractChanged(removed_contracts, added_contracts);
}

void ESunnySCItemListener::scitems2Contracts(const std::map<std::string, SubscribeContractItem>& items
            , std::vector<TapAPIContract>& contracts) {
    for (const auto& it : items) {
        TapAPIContract contract;
        memset(&contract, 0, sizeof(TapAPIContract));
        if (convertContract(it.second, contract)) {
            contracts.push_back(contract);
        } else {
            APPLOG_ERROR("Failed to convert contract for key={}, commodityId={}", it.first, it.second.sledCommodityId);
        }
    }
}

bool ESunnySCItemListener::convertContract(
        const SubscribeContractItem& scitem, TapAPIContract& contract) {
    // 暂时只支持普通期货映射行情
    if (scitem.sledCommodityType != (int16_t)xueqiao::contract::standard::SledCommodityType::FUTURES) {
        return false;
    }

    std::shared_ptr<xueqiao::contract::standard::CommodityMapping> mapping_entry
        = ContractMapping::Global().getCommodityMappingForSure((int32_t)scitem.sledCommodityId);
    if (!mapping_entry) {
        return false;
    }

    SledContractToTechPlatformArgs args;
    memset(&args, 0, sizeof(SledContractToTechPlatformArgs));

    args.TechPlatform_ = TechPlatform::TechPlatform_ESUNNY;

    strcpy(args.SledContract_.SledBaseContract_.ExchangeMic_, scitem.sledExchangeMic.c_str());
    strcpy(args.SledContract_.SledBaseContract_.SledCommodityCode_ , scitem.sledCommodityCode.c_str());
    args.SledContract_.SledBaseContract_.SledCommodityType_ = (SledCommodityType)scitem.sledCommodityType;
    strcpy(args.SledContract_.SledBaseContract_.SledContractCode_, scitem.sledContractCode.c_str());

    args.CommodityMap_.TechPlatform_ = TechPlatform::TechPlatform_ESUNNY;
    strcpy(args.CommodityMap_.ExchangeMic_, scitem.sledExchangeMic.c_str());
    args.CommodityMap_.SledCommodityType_ = (SledCommodityType)scitem.sledCommodityType;
    strcpy(args.CommodityMap_.SledCommodityCode_, scitem.sledCommodityCode.c_str());
    strcpy(args.CommodityMap_.TechPlatform_Exchange_, mapping_entry->exchange.c_str());
    strcpy(args.CommodityMap_.TechPlatform_CommodityType_, mapping_entry->commodityType.c_str());
    strcpy(args.CommodityMap_.TechPlatform_CommodityCode_, mapping_entry->commodityCode.c_str());

    SledContractToTechPlatformResult result = SledToPlatformContract(args);

    APPLOG_INFO("sledCommodityId={}, sledExchangeMic={}, sledCommodityCode={}, sledCommodityType={}, sledContractCode"
                "--> exchange={}, commodityType={}, commodityCode={}, contractCode={}"
                , scitem.sledCommodityId, scitem.sledExchangeMic, scitem.sledCommodityCode, scitem.sledCommodityCode
                , mapping_entry->exchange, mapping_entry->commodityType, mapping_entry->commodityCode
                , result.CommonContract_.TechPlatform_ContractCode_);

    if (result.CommonContract_.TechPlatform_ContractCode_[0] == 0) {
        return false;
    }

    // 从易胜API查找合约
    strcpy(contract.Commodity.CommodityNo, mapping_entry->commodityCode.c_str());
    contract.Commodity.CommodityType = mapping_entry->commodityType[0];
    strcpy(contract.Commodity.ExchangeNo, mapping_entry->exchange.c_str());
    strcpy(contract.ContractNo1, result.CommonContract_.TechPlatform_ContractCode_);

    return true;
}





} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



