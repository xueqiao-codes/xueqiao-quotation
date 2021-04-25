/*
 * race_publish_quotation_handler.h
 *
 *  Created on: 2017年3月11日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_SERVER_RACE_PUBLISH_QUOTATION_HANDLER_H_
#define QUOTATION_RACE_SERVER_RACE_PUBLISH_QUOTATION_HANDLER_H_

#include <atomic>
#include <boost/shared_ptr.hpp>
#include <boost/lockfree/queue.hpp>
#include <memory>
#include <thread>
#include "quotation/race/server/race_quotation_handler.h"
#include "thrift/transport/TBufferTransports.h"
#include "quotation_common/thrift/quotation_item_types.h"

namespace xueqiao {
namespace quotation {

class RacePublishQuotationHandler : public IRaceQuotationHandler {
public:
	RacePublishQuotationHandler(void* publish_socket);
	virtual ~RacePublishQuotationHandler();

	virtual void handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept;

private:
	void* publish_socket_;
	boost::shared_ptr<apache::thrift::transport::TMemoryBuffer> write_buffer_;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_SERVER_RACE_PUBLISH_QUOTATION_HANDLER_H_ */
