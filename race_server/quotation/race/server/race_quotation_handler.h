/*
 * race_quotation_handler.h
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_SERVER_RACE_QUOTATION_HANDLER_H_
#define QUOTATION_RACE_SERVER_RACE_QUOTATION_HANDLER_H_

#include <memory>
#include "quotation_common/thrift/quotation_item_types.h"

namespace xueqiao {
namespace quotation {

// 竞速成功的行情的处理器
class IRaceQuotationHandler {
public:
	virtual ~IRaceQuotationHandler() = default;

	virtual void handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept = 0;

protected:
	IRaceQuotationHandler() = default;
};


class LinkedRaceQuotationHandler : public IRaceQuotationHandler {
public:
	LinkedRaceQuotationHandler() = default;
	virtual ~LinkedRaceQuotationHandler() = default;

	void addHandler(const std::shared_ptr<IRaceQuotationHandler>& handler);

	virtual void handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept;

private:
	std::vector<std::shared_ptr<IRaceQuotationHandler>> handlers_;
};

} // end namespace quotation
} // end namespace xueqiao

#endif /* QUOTATION_RACE_SERVER_RACE_QUOTATION_HANDLER_H_ */
