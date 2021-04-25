/*
 * race_quotation_chooser.h
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_SERVER_RACE_QUOTATION_CHOOSER_H_
#define QUOTATION_RACE_SERVER_RACE_QUOTATION_CHOOSER_H_

#include <memory>
#include "quotation_common/thrift/quotation_item_types.h"

namespace xueqiao {
namespace quotation {

enum class QuotationType {
	QuotationUnkown,  // 未知行情
	QuotationLastest, // 最新行情
	QuotationObsolete, // 过时行情
};

class IRaceQuotationChooser {
public:
	virtual ~IRaceQuotationChooser() = default;

	virtual QuotationType choose(const std::shared_ptr<QuotationItem>& item) noexcept = 0;

protected:
	IRaceQuotationChooser() = default;

};

} // end namespace quotation
} // end namespace xueqiao


#endif /* QUOTATION_RACE_SERVER_RACE_QUOTATION_CHOOSER_H_ */
