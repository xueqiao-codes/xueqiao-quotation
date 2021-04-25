/*
 * esunny_quote_handler_impl.h
 *
 *  Created on: 2017年8月31日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_HANDLER_IMPL_H_
#define QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_HANDLER_IMPL_H_

#include <vector>

#include "attr/attr_reporter.h"
#include "quotation/access/esunny/esunny_quote_handler.h"
#include "quotation/access/common/group_chooser.h"


namespace xueqiao {
namespace quotation {
namespace access {

class ESunnyQuoteHandlerImpl : public IESunnyQuoteHandler {
public:
    ESunnyQuoteHandlerImpl(const std::shared_ptr<GroupChooser>& group_chooser
            , const std::string& platform);
    virtual ~ESunnyQuoteHandlerImpl();

    virtual void onReceivedQuoteItem(const TapAPIQuoteWhole& quote);

private:
    bool dispatchQuoteItem(const std::string& platform, const TapAPIQuoteWhole& quote);

    std::shared_ptr<GroupChooser> group_chooser_;
    std::string platform_;

    soldier::attr::IAttrReporter& attr_reporter_;
    int64_t quotations_received_key_ = -1;
    int64_t quotations_dispatched_failed_key_ = -1;
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_HANDLER_IMPL_H_ */
