/*
 * ctp_md_proxy.cpp
 *
 *  Created on: 2017年4月14日
 *      Author: wileywang
 */
#include "quotation/access/ctp/ctp_md_proxy.h"

#include <boost/date_time/gregorian/gregorian.hpp>
#include <boost/date_time/posix_time/posix_time.hpp>

#include "base/code_defense.h"
#include "base/string_util.h"
#include "base/time_helper.h"
#include "quotation_common/thrift/quotation_item_types.h"
#include "quotation_common/thrift/quotation_item_constants.h"
#include "contract_mapping.h"
#include "contract_symbol_utils.h"
#include "price_utils.h"
#include "race_client_factory.h"
#include "thrift/protocol/TDebugProtocol.h"

#include "ContractConvertor.h"
#include "effective_reporter.h"
#include "price_utils.h"


namespace xueqiao {
namespace quotation {
namespace access {

CTPMDProxy::CTPMDProxy(const std::string& front_address
        , const std::string& flow_path
        , const std::shared_ptr<GroupChooser>& group_chooser
        , const std::string& platform)
    : front_address_(front_address)
      , flow_path_(flow_path)
      , connected_(false)
      , group_chooser_(group_chooser)
      , platform_(platform)
      , attr_reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs()) {
    md_api_= CThostFtdcMdApi::CreateFtdcMdApi(flow_path_.c_str());
    CHECK(md_api_);

    proxy_thread_.reset(new std::thread(&CTPMDProxy::onProxyWork, this));

    quotations_received_key_ = attr_reporter_.requireKey(
            "quotation.access.received.count"
            , {{"platform", platform}});
    quotations_dispatched_failed_key_ = attr_reporter_.requireKey(
            "quotation.access.dispatched.failed.count"
            , {{"platform", platform}});

    pid_ = getpid();
}

CTPMDProxy::~CTPMDProxy() {
    if (md_api_) {
        md_api_->Release();
        md_api_ = nullptr;
    }

    proxy_thread_->join();
}

void CTPMDProxy::setAllInstruments(const std::vector<CThostFtdcInstrumentField>& instruments) {
    for(auto& instrument : instruments) {
        instrument_fields_[instrument.InstrumentID] = instrument;
    }
}

void CTPMDProxy::OnFrontConnected() {
    APPLOG_INFO("OnFrontConnected {}", front_address_);

    connected_ = true;
}

void CTPMDProxy::OnFrontDisconnected(int nReason) {
    APPLOG_INFO("OnFrontDisconnected {}, nReason={}", front_address_, nReason);

    if (connected_ == true) {
        APPLOG_FATAL("OnFrontDisconnected {}, nReason={} from connected, terminated it..."
                , front_address_, nReason);
    }
    connected_ = false;
}

void CTPMDProxy::OnHeartBeatWarning(int nTimeLapse) {
    APPLOG_INFO("OnHeartBeatWarning {}, nTimeLapse={}", front_address_, nTimeLapse);
}

int CTPMDProxy::login(const CTPMDLoginReq& req, CTPMDLoginResp& resp) {
    int result = 0;
    int request_id = -1;
    resp.errorCode = 0;
    resp.errorMsg = "";

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);

    sync_call->push(&resp);

    CThostFtdcReqUserLoginField login_field;
    strncpy(login_field.BrokerID, req.brokerId.c_str(), sizeof(TThostFtdcBrokerIDType) - 1);
    login_field.BrokerID[sizeof(TThostFtdcBrokerIDType) - 1] = 0;
    strncpy(login_field.UserID, req.brokerId.c_str(), sizeof(TThostFtdcUserIDType) - 1);
    login_field.UserID[sizeof(TThostFtdcUserIDType) - 1] = 0;
    strncpy(login_field.Password, req.userPasswd.c_str(), sizeof(TThostFtdcPasswordType) - 1);
    login_field.Password[sizeof(TThostFtdcPasswordType) - 1] = 0;

    last_login_request_id_ = request_id;
    result = md_api_->ReqUserLogin(&login_field, request_id);
    if (result != 0) {
        removeSyncCall(request_id);
        return result;
    }
    sync_call->wait();
    removeSyncCall(request_id);
    return result;
}

void CTPMDProxy::OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin
        , CThostFtdcRspInfoField *pRspInfo
        , int nRequestID
        , bool bIsLast) {
    CHECK(bIsLast);
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(last_login_request_id_);
    if (!sync_call) {
        APPLOG_DEBUG("OnRspUserLogin , not found call for {}", nRequestID);
        return ;
    }

    CTPMDLoginResp* resp = sync_call->at<CTPMDLoginResp>(0);
    if (pRspInfo) {
        resp->errorCode = pRspInfo->ErrorID;
        resp->errorMsg = pRspInfo->ErrorMsg;
    } else {
        resp->errorCode = -1001;
        resp->errorMsg = "OnRspUserLogin no rsp info";
    }
    sync_call->notify();
}

static void freeMarketDataInstruments(char** market_data_instruments, int size) {
    if (market_data_instruments == nullptr) {
        return ;
    }

    for (int index = 0; index < size; ++index) {
        if (market_data_instruments[index] != NULL) {
            free(market_data_instruments[index]);
        }
    }
    delete[] market_data_instruments;
}

int CTPMDProxy::subscribeContracts(const CTPMDSubscribeContractsReq& req
            , CTPMDSubscribeContractsResp& resp) {
    resp.failedContracts.clear();
    resp.failedErrorCodes.clear();
    resp.failedErrorMsgs.clear();
    resp.successContracts.clear();

    if (req.contracts.empty()) {
        return 0;
    }

    int result = 0;
    int request_id = -1;
    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    char** market_data_instruments = new char*[req.contracts.size()];
    memset(market_data_instruments, 0, sizeof(char*)*req.contracts.size());
    int current_index = 0;
    for (auto& contract : req.contracts) {
        char* instrument = (char*)malloc(sizeof(TThostFtdcInstrumentIDType));
        strncpy(instrument, contract.c_str(), sizeof(TThostFtdcInstrumentIDType) - 1);
        instrument[sizeof(TThostFtdcInstrumentIDType) - 1] = 0;
        market_data_instruments[current_index] = instrument;
        ++current_index;
    }

    last_subscribe_request_id_ = request_id;
    result = md_api_->SubscribeMarketData(market_data_instruments, req.contracts.size());
    if (result != 0) {
        freeMarketDataInstruments(market_data_instruments, req.contracts.size());
        removeSyncCall(request_id);
        return result;
    }

    sync_call->wait();
    freeMarketDataInstruments(market_data_instruments, req.contracts.size());
    removeSyncCall(request_id);
    return result;
}

void CTPMDProxy::OnRspSubMarketData(CThostFtdcSpecificInstrumentField *pSpecificInstrument
        , CThostFtdcRspInfoField *pRspInfo
        , int nRequestID
        , bool bIsLast) {
    APPLOG_DEBUG("OnRspSubMarketData nRequestID={}, bIsLast={}, pRspInfo->ErrorID={}", nRequestID, bIsLast
            , pRspInfo ? pRspInfo->ErrorID : -1001);
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(last_subscribe_request_id_);
    if (!sync_call) {
        APPLOG_DEBUG("NOT FOUND call for {}", last_subscribe_request_id_);
        return ;
    }

    CTPMDSubscribeContractsResp* resp = sync_call->at<CTPMDSubscribeContractsResp>(0);
    if (pRspInfo) {
        if (pSpecificInstrument) {
            if (pRspInfo->ErrorID != 0) {
                resp->failedContracts.push_back(pSpecificInstrument->InstrumentID);
                resp->failedErrorCodes.push_back(pRspInfo->ErrorID);
                resp->failedErrorMsgs.push_back(pRspInfo->ErrorMsg);
                APPLOG_INFO("failed instrument {}, ErrorID={}", pSpecificInstrument->InstrumentID
                        , pRspInfo->ErrorID);
            } else {
                EffectiveReporter::Global().setEffective();
                APPLOG_INFO("subscribe success {}", pSpecificInstrument->InstrumentID);
                resp->successContracts.push_back(pSpecificInstrument->InstrumentID);
            }
        } else {
            APPLOG_INFO("unfind instrument for request={}", nRequestID);
        }
    } else {
        if (pSpecificInstrument) {
            resp->failedContracts.push_back(pSpecificInstrument->InstrumentID);
            resp->failedErrorCodes.push_back(-1001);
            resp->failedErrorMsgs.push_back("OnRspSubMarketData no rsp info");
            APPLOG_INFO("failed instrument {}, error_code=-1001", pSpecificInstrument->InstrumentID);
        } else {
            APPLOG_INFO("unfind instrument for request={}", nRequestID);
        }
    }

    if (bIsLast) {
        sync_call->notify();
    }
}

void CTPMDProxy::OnRspError(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast) {
    APPLOG_ERROR("{} failed, bIsLast={}", nRequestID, bIsLast);
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(nRequestID);
    if (!sync_call) {
        return ;
    }

    if (bIsLast) {
        sync_call->notify();
    }
}

std::shared_ptr<soldier::base::SyncCall> CTPMDProxy::acquireSyncCall(int& request_id){
    request_id = last_request_id_.fetch_add(1);
    if (last_request_id_ >= INT_MAX/2) {
        last_request_id_ = 1;
    }
    std::shared_ptr<soldier::base::SyncCall> sync_call(new soldier::base::SyncCall());

    lock_.lock();
    CHECK(running_call_.find(request_id) == running_call_.end());
    running_call_[request_id] = sync_call;
    lock_.unlock();
    return sync_call;
}

std::shared_ptr<soldier::base::SyncCall> CTPMDProxy::findSyncCall(int request_id) {
    std::shared_ptr<soldier::base::SyncCall> sync_call;
    lock_.lock();
    auto it = running_call_.find(request_id);
    if (it != running_call_.end()) {
        sync_call = it->second;
    }
    lock_.unlock();
    return sync_call;
}

void CTPMDProxy::removeSyncCall(int request_id) {
    lock_.lock();
    running_call_.erase(request_id);
    lock_.unlock();
}


void CTPMDProxy::onProxyWork() {
    std::vector<std::string> fronts;
    soldier::base::StringUtil::tokenize(front_address_, fronts, ";", true);
    if (fronts.empty()) {
        APPLOG_FATAL("front_address_ {} empty!", front_address_);
    }

    for(auto& front : fronts) {
        md_api_->RegisterFront((char*)front.c_str());
        APPLOG_INFO("md add front {}", front);
    }
    md_api_->RegisterSpi(this);

    APPLOG_INFO("md api work flow_path={}, front_address={}", flow_path_, front_address_);

    md_api_->Init();
    md_api_->Join();

    APPLOG_INFO("md api existed, flow_path={}, front_address={}", flow_path_, front_address_);
}

void CTPMDProxy::OnRtnDepthMarketData(CThostFtdcDepthMarketDataField *pDepthMarketData) {
    if (!pDepthMarketData) {
        return ;
    }

    EffectiveReporter::Global().setEffective();

    attr_reporter_.inc(quotations_received_key_, 1);

    auto it = instrument_fields_.find(pDepthMarketData->InstrumentID);
    if (it == instrument_fields_.end()) {
        APPLOG_ERROR("Failed to find instrument field for InstrumentID={}", pDepthMarketData->InstrumentID);
        return ;
    }

    // 获取雪橇商品映射
    CommodityMappingOption mapping_option;
    mapping_option.sledBrokerId = 0;
    mapping_option.brokerExchangeCode = it->second.ExchangeID;
    mapping_option.brokerCommodityType.append(&(it->second.ProductClass), 1);
    mapping_option.brokerCommodityCode = it->second.ProductID;
    mapping_option.brokerTechPlatform = ::xueqiao::contract::standard::TechPlatform::CTP;

    std::shared_ptr<CommodityMappingEntry> mapping_entry;
    uint64_t mapping_time_ns = 0;
    {
        S_BLOCK_TIMER(mapping_time_ns);
        mapping_entry = ContractMapping::Global().getCommodityMapping(mapping_option);
    }
    APPLOG_DEBUG("Get Commodity for {}, escaped time={}ns", mapping_option, mapping_time_ns);
    if (!mapping_entry) {
        APPLOG_WARN("Failed to found commodity mapping for InstrumentID={}, ExchangeID={}, ProductClass={}, ProductID={}"
                , pDepthMarketData->InstrumentID
                , it->second.ExchangeID
                , it->second.ProductClass
                , it->second.ProductID);
        return ;
    }

    // 获取合约代码
    TechPlatformContractToSledArgs args;
    TechPlatformContractToSledResult result;
    memset(&args, 0, sizeof(TechPlatformContractToSledArgs));
    memset(&result, 0, sizeof(TechPlatformContractToSledResult));

    strcpy(args.CommonContract_.TechPlatform_Exchange_, it->second.ExchangeID);
    args.CommonContract_.TechPlatform_CommodityType_[0] = it->second.ProductClass;
    strcpy(args.CommonContract_.TechPlatform_CommodityCode_, it->second.ProductID);
    strcpy(args.CommonContract_.TechPlatform_ContractCode_, pDepthMarketData->InstrumentID);
    args.TechPlatform_ = TechPlatform_CTP;
    result = PlatformToSledContract(args);

    if (0 == result.MixContract_.SledContractCode_[0]) {
        APPLOG_WARN("Failed to mapping contract code for InstrumentID={}, ExchangeID={}, ProductClass={}, ProductID={}"
                , pDepthMarketData->InstrumentID
                , it->second.ExchangeID
                , it->second.ProductClass
                , it->second.ProductID);
        return ;
    }

    APPLOG_INFO("Commodity InstrumentID={}, ExchangeID={}, ProductClass={}, ProductID={} "
                "-> mappingID={}, sledCommodityId={}, sledExchangeCode={}"
                ", sledCommodityType={}, sledCommodityCode={}, sledContractCode={}"
                , pDepthMarketData->InstrumentID, it->second.ExchangeID, it->second.ProductClass, it->second.ProductID
                , mapping_entry->mapping.mappingId, mapping_entry->mapping.sledCommodityId
                , mapping_entry->commodity.exchangeMic, mapping_entry->commodity.sledCommodityType
                , mapping_entry->commodity.sledCommodityCode
                , result.MixContract_.SledContractCode_);

    std::string contract_symbol = ContractSymbolUtils::joinContractSymbols(mapping_entry->commodity.exchangeMic
            , mapping_entry->commodity.sledCommodityType
            , mapping_entry->commodity.sledCommodityCode
            , result.MixContract_.SledContractCode_);

   std::shared_ptr<QuotationItem> quotation_item(new QuotationItem());

   if (::xueqiao::quotation::g_quotation_item_constants.PLATFORM_CTP == platform_) {
       quotation_item->__set_platform(::xueqiao::quotation::g_quotation_item_constants.PLATFORM_XUEQIAO);
   } else if (::xueqiao::quotation::g_quotation_item_constants.PLATFORM_SIMU_CTP == platform_) {
       quotation_item->__set_platform(::xueqiao::quotation::g_quotation_item_constants.PLATFORM_SIMU_XUEQIAO);
   } else {
       return ;
   }

   quotation_item->__set_contractSymbol(contract_symbol);
   quotation_item->__set_sledExchangeCode(mapping_entry->commodity.exchangeMic);
   quotation_item->__set_sledCommodityType((int16_t)mapping_entry->commodity.sledCommodityType);
   quotation_item->__set_sledCommodityCode(mapping_entry->commodity.sledCommodityCode);
   quotation_item->__set_sledContractCode(result.MixContract_.SledContractCode_);

//        quotation_item->__set_platformContractId(sledContractEntry->platformContractId);
//        quotation_item->__set_sledPlatform(sledContractEntry->sledPlatform);
//        quotation_item->__set_sledContractId(sledContractEntry->sledContractId);

   quotation_item->__set_openPrice(pDepthMarketData->OpenPrice);
   quotation_item->__set_highPrice(pDepthMarketData->HighestPrice);
   quotation_item->__set_lowPrice(pDepthMarketData->LowestPrice);
   quotation_item->__set_preClosePrice(pDepthMarketData->PreClosePrice);
   quotation_item->__set_preSettlementPrice(pDepthMarketData->PreSettlementPrice);
   quotation_item->__set_preOpenInterest(pDepthMarketData->PreOpenInterest);
   quotation_item->__set_openInterest(pDepthMarketData->OpenInterest);
   quotation_item->__set_volumn(pDepthMarketData->Volume);

   if (!std::isnan(pDepthMarketData->Turnover) && !std::isinf(pDepthMarketData->Turnover)) {
       if (mapping_entry->commodity.exchangeMic == "XZCE") {
           if (it->second.VolumeMultiple != 0) {
               // 郑商所要乘以合约乘数
               quotation_item->__set_turnover(pDepthMarketData->Turnover * it->second.VolumeMultiple);
           }
       } else {
           quotation_item->__set_turnover(pDepthMarketData->Turnover);
       }
   }

   // 修正成交均价
   if (PriceUtils::isAppropriatePrice(pDepthMarketData->AveragePrice)) {
       if (mapping_entry->commodity.exchangeMic == "XSGE"
               || mapping_entry->commodity.exchangeMic == "XDCE"
               || mapping_entry->commodity.exchangeMic == "CCFX"
               || mapping_entry->commodity.exchangeMic == "XINE") {
           // 上交所, 大商所, 中金所和能源所 要除以合约乘数
           if (it->second.VolumeMultiple != 0) {
               quotation_item->__set_averagePrice(pDepthMarketData->AveragePrice/it->second.VolumeMultiple);
           }
       }  else {
           quotation_item->__set_averagePrice(pDepthMarketData->AveragePrice);
       }
   }

   boost::gregorian::date today = boost::gregorian::day_clock::universal_day();
   boost::posix_time::time_duration update_time
       = boost::posix_time::duration_from_string(pDepthMarketData->UpdateTime);
   boost::posix_time::time_duration now_time
       = boost::posix_time::second_clock::local_time().time_of_day();
   int64_t updateTimestampMs = 0;

   std::tm record_update_time_tm;
   if (update_time > now_time && (update_time - now_time).hours() >= 23) {
       boost::gregorian::day_iterator it(today);
       boost::posix_time::ptime update_yesterday_ptime(*(--it), update_time);
       record_update_time_tm = boost::posix_time::to_tm(update_yesterday_ptime);
   } else {
       boost::posix_time::ptime update_today_ptime(today, update_time);
       record_update_time_tm = boost::posix_time::to_tm(update_today_ptime);
   }
   updateTimestampMs = ((int64_t)mktime(&record_update_time_tm))* 1000 + pDepthMarketData->UpdateMillisec;

   quotation_item->__set_lastPrice(pDepthMarketData->LastPrice);
   quotation_item->__set_lastQty(0);
   quotation_item->__set_upperLimitPrice(pDepthMarketData->UpperLimitPrice);
   quotation_item->__set_lowerLimitPrice(pDepthMarketData->LowerLimitPrice);
   quotation_item->__set_updateTimestampMs(updateTimestampMs);

   if (pDepthMarketData->BidVolume1 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->BidPrice1)) {
       quotation_item->bidPrice.push_back(pDepthMarketData->BidPrice1);
       quotation_item->bidQty.push_back(pDepthMarketData->BidVolume1);
   }

   if (pDepthMarketData->BidVolume2 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->BidPrice2)) {
       quotation_item->bidPrice.push_back(pDepthMarketData->BidPrice2);
       quotation_item->bidQty.push_back(pDepthMarketData->BidVolume2);
   }

   if (pDepthMarketData->BidVolume3 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->BidPrice3)) {
       quotation_item->bidPrice.push_back(pDepthMarketData->BidPrice3);
       quotation_item->bidQty.push_back(pDepthMarketData->BidVolume3);
   }

   if (pDepthMarketData->BidVolume4 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->BidPrice4)) {
       quotation_item->bidPrice.push_back(pDepthMarketData->BidPrice4);
       quotation_item->bidQty.push_back(pDepthMarketData->BidVolume4);
   }

   if (pDepthMarketData->BidVolume5 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->BidPrice5)) {
       quotation_item->bidPrice.push_back(pDepthMarketData->BidPrice5);
       quotation_item->bidQty.push_back(pDepthMarketData->BidVolume5);
   }

   quotation_item->__isset.bidPrice = true;
   quotation_item->__isset.bidQty = true;

   if (pDepthMarketData->AskVolume1 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->AskPrice1)) {
       quotation_item->askPrice.push_back(pDepthMarketData->AskPrice1);
       quotation_item->askQty.push_back(pDepthMarketData->AskVolume1);
   }

   if (pDepthMarketData->AskVolume2 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->AskPrice2)) {
       quotation_item->askPrice.push_back(pDepthMarketData->AskPrice2);
       quotation_item->askQty.push_back(pDepthMarketData->AskVolume2);
   }

   if (pDepthMarketData->AskVolume3 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->AskPrice3)) {
       quotation_item->askPrice.push_back(pDepthMarketData->AskPrice3);
       quotation_item->askQty.push_back(pDepthMarketData->AskVolume3);
   }

   if (pDepthMarketData->AskVolume4 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->AskPrice4)) {
       quotation_item->askPrice.push_back(pDepthMarketData->AskPrice4);
       quotation_item->askQty.push_back(pDepthMarketData->AskVolume4);
   }

   if (pDepthMarketData->AskVolume5 > 0 && PriceUtils::isAppropriatePrice(pDepthMarketData->AskPrice5)) {
       quotation_item->askPrice.push_back(pDepthMarketData->AskPrice5);
       quotation_item->askQty.push_back(pDepthMarketData->AskVolume5);
   }

   quotation_item->__isset.askPrice = true;
   quotation_item->__isset.askQty = true;

   std::string key("/");
   key.append(quotation_item->platform);
   key.append("/");
   key.append(boost::lexical_cast<std::string>(quotation_item->contractSymbol));

   APPLOG_DEBUG("Send Quotation {}", apache::thrift::ThriftDebugString(*quotation_item));

   std::shared_ptr<::xueqiao::quotation::IRaceClient> client
   = ::xueqiao::quotation::RaceClientFactory::Global()->getClient(group_chooser_->chooseGroup(key));
   if (!client) {
       attr_reporter_.inc(quotations_dispatched_failed_key_, 1);
       return ;
   }

   client->send2Race(quotation_item);
}




} // end namespace access
} // end namespace quotation
} // end namespace xueqiao


