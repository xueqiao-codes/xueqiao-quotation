/*
 * subscribe_callback.h
 *
 *  Created on: 2017年4月6日
 *      Author: wileywang
 */

#ifndef QUOTATION_SUBSCRIBE_API_SUBSCRIBE_CALLBACK_H_
#define QUOTATION_SUBSCRIBE_API_SUBSCRIBE_CALLBACK_H_

#include "quotation/subscribe/api/topic.h"

namespace xueqiao {
namespace quotation {

/**
 * will be called in multi thread
 */
class ISubscribeCallback {
public:
    virtual ~ISubscribeCallback() {}

    virtual void onReceivedItemData(const Topic& topic, uint8_t* data , size_t size) = 0;

protected:
    ISubscribeCallback() {}
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_SUBSCRIBE_API_SUBSCRIBE_CALLBACK_H_ */
