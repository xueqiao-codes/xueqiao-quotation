/*
 * race_quotation_chooser_impl.h
 *
 *  Created on: 2017年3月11日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_SERVER_RACE_QUOTATION_CHOOSER_IMPL_H_
#define QUOTATION_RACE_SERVER_RACE_QUOTATION_CHOOSER_IMPL_H_

#include <string>
#include <memory>
#include "quotation/race/server/race_quotation_chooser.h"

namespace xueqiao {
namespace quotation {

class RaceQuotationChooserImpl : public IRaceQuotationChooser {
public:
	RaceQuotationChooserImpl() = default;
	virtual ~RaceQuotationChooserImpl() = default;

	virtual QuotationType choose(const std::shared_ptr<QuotationItem>& item) noexcept;

private:
	std::string generateKey(const QuotationItem& key);
	void reserveKeyMembers(std::shared_ptr<QuotationItem>& update_item
			, const std::shared_ptr<QuotationItem>& reserve_item);

	std::map<std::string, std::shared_ptr<QuotationItem>> lastest_raced_quotation_map_;
};



} // end namespace quotation
} // end namespace xueqiao




#endif /* QUOTATION_RACE_SERVER_RACE_QUOTATION_CHOOSER_IMPL_H_ */
