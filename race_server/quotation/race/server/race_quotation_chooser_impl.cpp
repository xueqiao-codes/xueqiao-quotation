/*
 * race_quotation_chooser_impl.cpp
 *
 *  Created on: 2017年3月11日
 *      Author: 44385
 */
#include "quotation/race/server/race_quotation_chooser_impl.h"

#include <sstream>
#include "base/app_log.h"

namespace xueqiao {
namespace quotation {

QuotationType RaceQuotationChooserImpl::choose(const std::shared_ptr<QuotationItem>& item) noexcept {
	if (item->platform.empty()) {
		APPLOG_INFO("Platform Empty! Drop it!");
		return QuotationType::QuotationUnkown;
	}
	if (item->contractSymbol.empty()) {
		APPLOG_INFO("contractSymbol Empty! Drop it!");
		return QuotationType::QuotationUnkown;
	}
	if (item->volumn < 0) {
	    APPLOG_INFO("/{}/{} volumn{} < 0! Drop it!", item->platform, item->contractSymbol, item->volumn);
		return QuotationType::QuotationUnkown;
	}
	if (item->turnover < 0) {
	    APPLOG_INFO("/{}/{} turnover{} < 0| Drop it!", item->platform, item->contractSymbol, item->turnover);
		return QuotationType::QuotationUnkown;
	}

	std::string key = generateKey(*item);
	auto it = lastest_raced_quotation_map_.find(key);
	if (it == lastest_raced_quotation_map_.end()) {
	    APPLOG_INFO("QuotationLastest /{}/{} new from {}"
	            , item->platform, item->contractSymbol, item->receivedHostName);
		std::shared_ptr<QuotationItem> new_quotation_item =  std::make_shared<QuotationItem>();
		reserveKeyMembers(new_quotation_item, item);
		lastest_raced_quotation_map_.insert(
				std::pair<std::string, std::shared_ptr<QuotationItem>>(key, new_quotation_item));
		return QuotationType::QuotationLastest;
	}

	std::shared_ptr<QuotationItem>& old_quotation_item = it->second;

	if (item->volumn > 0) {
	    if (item->lastQty <= 0) {
	        item->__set_lastQty(item->volumn - old_quotation_item->volumn);
	    }
	    if (item->lastQty <= 0) {
	        item->__set_lastQty(old_quotation_item->lastQty);
	    }
	} else if (item->volumn == 0) {
	    // 过滤最新价
	    item->__isset.lastPrice = false;
	    item->__isset.averagePrice = false;
	}

	// 成交量增加，肯定是最新行情
	if (old_quotation_item->volumn <  item->volumn ) {
	    APPLOG_INFO("QuotationLastest /{}/{} , volumn added, new is {}, old is {}"
	                    , item->platform, item->contractSymbol
	                    , item->volumn, old_quotation_item->volumn);

		reserveKeyMembers(it->second, item);
		return QuotationType::QuotationLastest;
	}

	// 同一个来源行情来自上个接收server,
	if (old_quotation_item->receivedHostName == item->receivedHostName) {
	    APPLOG_INFO("QuotationLastest /{}/{}, same host from {}"
	            , item->platform, item->contractSymbol
	            , item->receivedHostName);
		reserveKeyMembers(it->second, item);
		return QuotationType::QuotationLastest;
	}

	// 行情來源包含了更新時間戳, 換源的誤差允許
	if ((item->updateTimestampMs - old_quotation_item->updateTimestampMs) > 15000) {
	    APPLOG_INFO("QuotationLastest /{}/{}, new updateTimestamp {} from {} bigger than old updateTimestamp {} from {}"
	                    , item->platform, item->contractSymbol
	                    , item->updateTimestampMs, item->receivedHostName
	                    , old_quotation_item->updateTimestampMs, old_quotation_item->receivedHostName);
		reserveKeyMembers(it->second, item);
		return QuotationType::QuotationLastest;
	}

	// 換源選擇， 如果老的源一分鐘都沒有行情進入，新的來源進入行情，則進行來源切換
	if (item->raceTimestampMs - old_quotation_item->raceTimestampMs > 60*1000) {
	    APPLOG_INFO("QuotationLastest /{}/{}, new raceTimestamp {} from {} bigger than old updateTimestamp {} from {}"
	                    , item->platform, item->contractSymbol
	                    , item->raceTimestampMs, item->receivedHostName
	                    , old_quotation_item->raceTimestampMs, old_quotation_item->receivedHostName);
	    reserveKeyMembers(it->second, item);
	    return QuotationType::QuotationLastest;
	}

	// 源携帶的時間戳告知過時行情
	if (item->updateTimestampMs > 0
	    && item->updateTimestampMs <= old_quotation_item->updateTimestampMs) {
	    APPLOG_INFO("QuotationObsolete, /{}/{} updateTime new is {} from {}, old is {} from {}"
	            , item->platform, item->contractSymbol
	            , item->updateTimestampMs, item->receivedHostName
	            , old_quotation_item->updateTimestampMs, old_quotation_item->receivedHostName);
	    return QuotationType::QuotationObsolete;
	 }

	APPLOG_INFO("QuotationObsolete /{}/{}, not choosed from {}, old from {}!"
	        , item->platform, item->contractSymbol
	        , item->receivedHostName, old_quotation_item->receivedHostName);
	return QuotationType::QuotationObsolete;
}

void RaceQuotationChooserImpl::reserveKeyMembers(std::shared_ptr<QuotationItem>& update_item
			, const std::shared_ptr<QuotationItem>& reserve_item) {
	update_item->__set_platform(reserve_item->platform);
	update_item->__set_contractSymbol(reserve_item->contractSymbol);
	update_item->__set_volumn(reserve_item->volumn);
	update_item->__set_turnover(reserve_item->turnover);
	update_item->__set_updateTimestampMs(reserve_item->updateTimestampMs);
	update_item->__set_raceTimestampMs(reserve_item->raceTimestampMs);
	update_item->__set_receivedTimestampMs(reserve_item->receivedTimestampMs);
	update_item->__set_receivedProcessId(reserve_item->receivedProcessId);
	update_item->__set_receivedHostName(reserve_item->receivedHostName);
	update_item->__set_lastQty(reserve_item->lastQty);
}


std::string RaceQuotationChooserImpl::generateKey(const QuotationItem& item) {
	std::stringstream ss;
	ss << "/" << item.platform << "/" << item.contractSymbol;
	return ss.str();
}


} // end namespace quotation
} // end namespace xueqiao



