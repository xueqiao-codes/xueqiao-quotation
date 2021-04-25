/*
 * esunny_quote_handler_impl.cpp
 *
 *  Created on: 2017年8月31日
 *      Author: wileywang
 */
#include "quotation/access/esunny/esunny_quote_handler_impl.h"

#include <boost/date_time/posix_time/posix_time.hpp>
#include <boost/date_time/gregorian/gregorian.hpp>

#include "base/app_log.h"
#include "base/code_defense.h"
#include "base/time_helper.h"
#include "attr/attr_reporter_factory.h"
#include "quotation_common/buildgen/quotation_common/thrift/quotation_item_constants.h"
#include "quotation_common/buildgen/quotation_common/thrift/quotation_item_types.h"
#include "quotation/access/common/contract_mapping.h"
#include "quotation/access/common/contract_symbol_utils.h"
#include "quotation/access/common/price_utils.h"
#include "quotation/race/client/race_client_factory.h"
#include "thrift/protocol/TDebugProtocol.h"
#include "ContractConvertor.h"

namespace xueqiao {
namespace quotation {
namespace access {

static boost::posix_time::ptime EPOCH(boost::gregorian::date(1970, boost::gregorian::Jan, 1));
static long UTC_SHANGHAI_MILLISECONDS = 8*3600*1000;

ESunnyQuoteHandlerImpl::ESunnyQuoteHandlerImpl(const std::shared_ptr<GroupChooser>& group_chooser
        , const std::string& platform)
    : group_chooser_(group_chooser)
      , platform_(platform)
      , attr_reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs()){

    CHECK(group_chooser_.get());

    quotations_received_key_ = attr_reporter_.requireKey("quotation.access.received.count", {{"platform", platform_}});
    quotations_dispatched_failed_key_ = attr_reporter_.requireKey("quotation.access.dispatched.failed.count", {{"platform", platform_}});
}

ESunnyQuoteHandlerImpl::~ESunnyQuoteHandlerImpl() {
}

void ESunnyQuoteHandlerImpl::onReceivedQuoteItem(const TapAPIQuoteWhole& quote) {
    attr_reporter_.inc(quotations_received_key_, 1);

    bool dispatched = false;
    try {
        dispatched = dispatchQuoteItem(platform_, quote);
    } catch (...) {
        APPLOG_ERROR("dispatchQuoteItem get uncaught exception...");
    }
    if (!dispatched) {
        attr_reporter_.inc(quotations_dispatched_failed_key_, 1);
    }
}

bool ESunnyQuoteHandlerImpl::dispatchQuoteItem(const std::string& platform, const TapAPIQuoteWhole& quote) {
    APPLOG_DEBUG("dispatchQuoteItem platform={}"
            ", ExchangeNo={}, CommodityType={}, CommodityNo={}"
            ", ContractNo1={}, StrikePrice1={}, CallOrPutFlag1={}"
            ", ContractNo2={}, StrikePrice2={}, CallOrPutFlag2={}"
            , platform, quote.Contract.Commodity.ExchangeNo, quote.Contract.Commodity.CommodityType, quote.Contract.Commodity.CommodityNo
            , quote.Contract.ContractNo1, quote.Contract.StrikePrice1, quote.Contract.CallOrPutFlag1
            , quote.Contract.ContractNo2, quote.Contract.StrikePrice2, quote.Contract.StrikePrice2);

    CommodityMappingOption mapping_option;
    mapping_option.sledBrokerId = 0;
    mapping_option.brokerExchangeCode = quote.Contract.Commodity.ExchangeNo;
    mapping_option.brokerCommodityType.append(1, quote.Contract.Commodity.CommodityType);
    mapping_option.brokerCommodityCode = quote.Contract.Commodity.CommodityNo;
    mapping_option.brokerTechPlatform = ::xueqiao::contract::standard::TechPlatform::ESUNNY;

    std::shared_ptr<CommodityMappingEntry> mapping_entry;
    uint64_t mapping_time_ns = 0;
    {
        S_BLOCK_TIMER(mapping_time_ns);
        mapping_entry = ContractMapping::Global().getCommodityMapping(mapping_option);
    }
    APPLOG_DEBUG("Get Commodity for {}, escaped time={}ns", mapping_option, mapping_time_ns);
    if (!mapping_entry) {
        APPLOG_WARN("Failed to found commodity mapping for ExchangeNo={}, CommodityType={}, CommodityNo={}"
                , quote.Contract.Commodity.ExchangeNo
                , quote.Contract.Commodity.CommodityType
                , quote.Contract.Commodity.CommodityNo);
        return false;
    }
    // 获取合约代码
    TechPlatformContractToSledArgs args;
    TechPlatformContractToSledResult result;
    memset(&args, 0, sizeof(TechPlatformContractToSledArgs));
    memset(&result, 0, sizeof(TechPlatformContractToSledResult));

    strcpy(args.CommonContract_.TechPlatform_Exchange_, quote.Contract.Commodity.ExchangeNo);
    args.CommonContract_.TechPlatform_CommodityType_[0] = quote.Contract.Commodity.CommodityType;
    strcpy(args.CommonContract_.TechPlatform_CommodityCode_, quote.Contract.Commodity.CommodityNo);
    strcpy(args.CommonContract_.TechPlatform_ContractCode_, quote.Contract.ContractNo1);
    if (quote.Contract.ContractNo2[0] != 0) {
        args.OtherCommonContractCount_ = 1;
        strcpy(args.OtherCommonContract_[0].TechPlatform_Exchange_, quote.Contract.Commodity.ExchangeNo);
        args.OtherCommonContract_[0].TechPlatform_CommodityType_[0] = quote.Contract.Commodity.CommodityType;
        strcpy(args.OtherCommonContract_[0].TechPlatform_CommodityCode_, quote.Contract.Commodity.CommodityNo);
        strcpy(args.OtherCommonContract_[0].TechPlatform_ContractCode_, quote.Contract.ContractNo2);
    }
    args.TechPlatform_ = TechPlatform_ESUNNY;
    result = PlatformToSledContract(args);

    if (0 == result.MixContract_.SledContractCode_[0]) {
        APPLOG_WARN("Failed to mapping contract code for ExchangeNo={}, CommodityType={}, CommodityNo={}, "
                ", ContractNo1={}, ContractNo2={}"
                , quote.Contract.Commodity.ExchangeNo
                , quote.Contract.Commodity.CommodityType
                , quote.Contract.Commodity.CommodityNo
                , quote.Contract.ContractNo1
                , quote.Contract.ContractNo2);
        return false;
    }

    std::string contract_symbol = ContractSymbolUtils::joinContractSymbols(mapping_entry->commodity.exchangeMic
                , mapping_entry->commodity.sledCommodityType
                , mapping_entry->commodity.sledCommodityCode
                , result.MixContract_.SledContractCode_);

    std::shared_ptr<QuotationItem> quotation_item(new QuotationItem());

    if (::xueqiao::quotation::g_quotation_item_constants.PLATFORM_ESUNNY == platform) {
        quotation_item->__set_platform(::xueqiao::quotation::g_quotation_item_constants.PLATFORM_XUEQIAO);
    } else if (::xueqiao::quotation::g_quotation_item_constants.PLATFROM_SIMY_ESUNNY == platform) {
        quotation_item->__set_platform(::xueqiao::quotation::g_quotation_item_constants.PLATFORM_SIMU_XUEQIAO);
    } else {
        return false;
    }

    quotation_item->__set_contractSymbol(contract_symbol);
    quotation_item->__set_sledExchangeCode(mapping_entry->commodity.exchangeMic);
    quotation_item->__set_sledCommodityType((int16_t)mapping_entry->commodity.sledCommodityType);
    quotation_item->__set_sledCommodityCode(mapping_entry->commodity.sledCommodityCode);
    quotation_item->__set_sledContractCode(result.MixContract_.SledContractCode_);

    quotation_item->__set_openPrice(quote.QOpeningPrice);
    quotation_item->__set_highPrice(quote.QHighPrice);
    quotation_item->__set_lowPrice(quote.QLowPrice);
    quotation_item->__set_preClosePrice(quote.QPreClosingPrice);
    quotation_item->__set_preSettlementPrice(quote.QPreSettlePrice);
    quotation_item->__set_preOpenInterest(quote.QPrePositionQty);
    quotation_item->__set_openInterest(quote.QPositionQty);
    quotation_item->__set_volumn(quote.QTotalQty);
    quotation_item->__set_turnover(quote.QTotalTurnover);
    quotation_item->__set_averagePrice(quote.QAveragePrice);
    quotation_item->__set_lastQty(quote.QLastQty);
    quotation_item->__set_lastPrice(quote.QLastPrice);
    quotation_item->__set_lowerLimitPrice(quote.QLimitDownPrice);
    quotation_item->__set_upperLimitPrice(quote.QLimitUpPrice);

    boost::posix_time::ptime update_time = boost::posix_time::time_from_string(quote.DateTimeStamp);
    quotation_item->__set_updateTimestampMs((update_time - EPOCH).total_milliseconds() - UTC_SHANGHAI_MILLISECONDS);

    for (int index = 0; index < 20; ++index) {
        if (quote.QBidQty[index] > 0 && PriceUtils::isAppropriatePrice(quote.QBidQty[index])) {
            // 非组合类型的行情不允许价格为0的情况
            if (quote.Contract.ContractNo2[0] != 0 || !PriceUtils::isZeroPrice(quote.QBidPrice[index])) {
                quotation_item->bidPrice.push_back(quote.QBidPrice[index]);
                quotation_item->bidQty.push_back(quote.QBidQty[index]);
            }
        }
        if (quote.QAskQty[index] > 0 && PriceUtils::isAppropriatePrice(quote.QAskPrice[index])) {
            if (quote.Contract.ContractNo2[0] != 0 || !PriceUtils::isZeroPrice(quote.QAskPrice[index])) {
                quotation_item->askPrice.push_back(quote.QAskPrice[index]);
                quotation_item->askQty.push_back(quote.QAskQty[index]);
            }
        }
    }
    quotation_item->__isset.bidPrice = true;
    quotation_item->__isset.bidQty = true;
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
        return false;
    }

    client->send2Race(quotation_item);
    return true;
}

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



