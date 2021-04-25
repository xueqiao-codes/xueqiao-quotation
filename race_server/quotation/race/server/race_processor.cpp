/*
 * race_processor.cc
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */
#include "quotation/race/server/race_processor.h"

#include "attr/attr_reporter_factory.h"
#include "base/code_defense.h"
#include "base/time_helper.h"
#include "quotation_common/thrift/quotation_item_constants.h"
#include "thrift/protocol/TDebugProtocol.h"

namespace xueqiao{
namespace quotation {

RaceProcessor::RaceProcessor(const std::shared_ptr<IRaceQuotationChooser>& chooser
			, const std::shared_ptr<IRaceQuotationHandler>& handler)
	: chooser_(chooser)
	 , handler_(handler)
     , queue_items_(1000)
     , reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs()){
	CHECK(chooser_.get() != nullptr);
	CHECK(handler_.get() != nullptr);

	race_dropped_item_key_ = reporter_.requireKey("quotation.race.dropped.count", {});
	race_process_item_key_ = reporter_.requireKey("quotation.race.processed.count", {});
	race_choosed_item_key_ = reporter_.requireKey("quotation.race.choosed.count", {});
	race_process_timens_key_ = reporter_.requireKey("quotation.race.processed.timens", {});

	working_thread_.reset(new std::thread(&RaceProcessor::onWorking, this));
}

RaceProcessor::~RaceProcessor() {
    end_working_ = true;
    working_thread_->join();
}

void RaceProcessor::onWorking() {
    while(!end_working_) {
        QueuedItem* item = nullptr;

        if (!queue_items_.pop(item)) {
            std::this_thread::sleep_for(std::chrono::microseconds(100));
            continue;
        }

        std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();
        std::unique_ptr<QueuedItem> queued_item(item);
        onProcessQuotationItem(item->quotation_item_);
        reporter_.average(race_process_timens_key_,
                std::chrono::duration_cast<std::chrono::nanoseconds>(
                        std::chrono::high_resolution_clock::now() - start).count());
    }
}

void RaceProcessor::onProcessQuotationItem(std::shared_ptr<QuotationItem>& quotation_item) noexcept {
    reporter_.inc(race_process_item_key_, 1);

    quotation_item->__set_raceTimestampMs(soldier::base::NowInMilliSeconds());

    QuotationType type = chooser_->choose(quotation_item);
//    if (type == QuotationType::QuotationLastest) {
//        if (quotation_item->platform == g_quotation_item_constants.PLATFORM_CTP
//            && !quotation_item->contractSymbol.empty()) {
//            auto it = ctp_latest_quotation_items_.find(quotation_item->contractSymbol);
//            if (it != ctp_latest_quotation_items_.end()) {
//                if (it->second->volumn < quotation_item->volumn ) {
//                    quotation_item->__set_lastQty(quotation_item->volumn - it->second->volumn );
//                }
//                it->second = quotation_item;
//            } else {
//                ctp_latest_quotation_items_[quotation_item->contractSymbol] = quotation_item;
//            }
//        }
//    }

    std::string quotation_item_str;
    if (APPLOG_DEBUG_OPENED()) {
       quotation_item_str =  apache::thrift::ThriftDebugString(*quotation_item);
    }
    if (type != QuotationType::QuotationLastest) {
        if (APPLOG_DEBUG_OPENED()) {
            APPLOG_DEBUG("Drop Quotation(type={}), {}", (int)type, quotation_item_str);
        }
        return ;
    }

    reporter_.inc(race_choosed_item_key_, 1);
    handler_->handleQuotation(quotation_item);
    if (APPLOG_DEBUG_OPENED()) {
        APPLOG_DEBUG("RaceQuotation {}", quotation_item_str);
    }
}

void RaceProcessor::processQuotationItem(std::shared_ptr<QuotationItem>& quotation_item) noexcept {
	if (quotation_item.get() == nullptr) {
		return ;
	}

	std::unique_ptr<QueuedItem> queue_item(new QueuedItem(quotation_item));
	if (!queue_items_.bounded_push(queue_item.get())) {
	    reporter_.inc(race_dropped_item_key_, 1);
	} else {
	    queue_item.release();
	}
}


} // end namespace quotation
} // end namespace xueqiao

