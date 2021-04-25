/*
 * esunny_quote_handler.h
 *
 *  Created on: 2017年8月31日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_HANDLER_H_
#define QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_HANDLER_H_

#include "TapQuoteAPI.h"

namespace xueqiao {
namespace quotation {
namespace access {

class IESunnyQuoteHandler {
public:
    IESunnyQuoteHandler() = default;

    virtual void onReceivedQuoteItem(const TapAPIQuoteWhole& quote) = 0;

protected:
    virtual ~IESunnyQuoteHandler() = default;
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_HANDLER_H_ */
