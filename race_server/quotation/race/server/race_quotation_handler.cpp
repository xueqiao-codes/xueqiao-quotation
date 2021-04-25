/*
 * race_quotation_handler.cpp
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */
#include "quotation/race/server/race_quotation_handler.h"

namespace xueqiao {
namespace quotation {

void LinkedRaceQuotationHandler::addHandler(const std::shared_ptr<IRaceQuotationHandler>& handler) {
	handlers_.push_back(handler);
}

void LinkedRaceQuotationHandler::handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept {
	for (auto& handler : handlers_) {
		handler->handleQuotation(item);
	}
}


} // end namespace quotation
} // end namespace xueqiao



