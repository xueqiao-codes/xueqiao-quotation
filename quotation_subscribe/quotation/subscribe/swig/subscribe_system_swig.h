/*
 * subscribe_system_swig.h
 *
 *  Created on: 2017年4月6日
 *      Author: wileywang
 */

#ifndef QUOTATION_SUBSCRIBE_SWIG_SUBSCRIBE_SYSTEM_SWIG_H_
#define QUOTATION_SUBSCRIBE_SWIG_SUBSCRIBE_SYSTEM_SWIG_H_

#include "quotation/subscribe/api/topic.h"
#include "quotation/subscribe/api/subscribe_callback.h"

namespace xueqiao {
namespace quotation {
namespace swig {

class QuotationSubscriberSwig {
public:
    static void init(const std::string& name, ISubscribeCallback* callback);
    static void addTopic(const Topic& topic);
    static void removeTopic(const Topic& topic);
    static void destroy();
};


} // end namespace swig
} // end namespace quotation
} // end namespace xueqiao






#endif /* QUOTATION_SUBSCRIBE_SWIG_SUBSCRIBE_SYSTEM_SWIG_H_ */
