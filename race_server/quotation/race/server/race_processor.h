/*
 * race_processor.h
 *
 *	 竞速集群处理器
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */
#ifndef QUOTATION_RACE_SERVER_RACE_PROCESSOR
#define QUOTATION_RACE_SERVER_RACE_PROCESSOR

#include <boost/lockfree/queue.hpp>
#include <memory>
#include <thread>
#include "attr/attr_reporter.h"
#include "quotation_common/thrift/quotation_item_types.h"
#include "quotation/race/server/race_quotation_chooser.h"
#include "quotation/race/server/race_quotation_handler.h"

namespace xueqiao {
namespace quotation {


class RaceProcessor {
public:
	RaceProcessor(const std::shared_ptr<IRaceQuotationChooser>& chooser
			, const std::shared_ptr<IRaceQuotationHandler>& handler);
	~RaceProcessor();

	void processQuotationItem(std::shared_ptr<QuotationItem>& quotation_item) noexcept;

private:
	void onWorking();
	void onProcessQuotationItem(std::shared_ptr<QuotationItem>& quotation_item) noexcept;

	struct QueuedItem {
	    QueuedItem(std::shared_ptr<QuotationItem>& quotation_item)
	        :quotation_item_(quotation_item) {
	    }

	    std::shared_ptr<QuotationItem> quotation_item_;
	};

	std::shared_ptr<IRaceQuotationChooser> chooser_;
	std::shared_ptr<IRaceQuotationHandler> handler_;

	boost::lockfree::queue<QueuedItem*> queue_items_;

	std::atomic_bool end_working_ = {false};
	std::unique_ptr<std::thread> working_thread_;

	soldier::attr::IAttrReporter& reporter_;
	int64_t race_dropped_item_key_ = -1;
	int64_t race_process_item_key_ = -1;
	int64_t race_choosed_item_key_ = -1;
	int64_t race_process_timens_key_ = -1;
};



} // end namespace quotation
} // end namespace xueqiao

#endif // QUOTATION_RACE_SERVER_RACE_PROCESSOR

