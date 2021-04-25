/*
 * quotation_receivor.h
 *
 *  Created on: 2017年4月3日
 *      Author: 44385
 */

#ifndef QUOTATION_SUBSCRIBE_QUOTATION_RECEIVOR_H_
#define QUOTATION_SUBSCRIBE_QUOTATION_RECEIVOR_H_

#include <memory>
#include "quotation_common/thrift/quotation_item_types.h"
#include "quotation/subscribe/api/topic.h"

namespace xueqiao {
namespace quotation {

class IQuotationRecivor {
public:
    virtual ~IQuotationRecivor() = default;

    virtual void onReceivedQuotation(const std::shared_ptr<QuotationItem>& item) noexcept = 0;

protected:
    IQuotationRecivor() = default;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_SUBSCRIBE_QUOTATION_RECEIVOR_H_ */
