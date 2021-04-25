/*
 * subscribe_system_swig.cpp
 *
 *  Created on: 2017年4月6日
 *      Author: wileywang
 */

#include "quotation/subscribe/swig/subscribe_system_swig.h"

#include "quotation/subscribe/api/subscribe_system.h"
#include "base/app_log.h"

namespace xueqiao {
namespace quotation {
namespace swig {

//static std::shared_ptr<ISubscribeCallback>* s_callback;

void QuotationSubscriberSwig::init(const std::string& name, ISubscribeCallback* callback) {
    if (SubscribeSystem::Get()) {
        return ;
    }
    soldier::base::AppLog::Init("subscribers/" + name);

//     let the memory leak, because swig callback will crashed when release
//    s_callback = new std::shared_ptr<ISubscribeCallback>(callback);
//    SubscribeSystem::Init(*s_callback);
    SubscribeSystem::Init(std::shared_ptr<ISubscribeCallback>(callback));
}

void QuotationSubscriberSwig::addTopic(const Topic& topic) {
    if (SubscribeSystem::Get()) {
        SubscribeSystem::Get()->addTopic(topic);
    }
}

void QuotationSubscriberSwig::removeTopic(const Topic& topic) {
    if (SubscribeSystem::Get()) {
        SubscribeSystem::Get()->removeTopic(topic);
    }
}

void QuotationSubscriberSwig::destroy() {
    SubscribeSystem::Destroy();
}


} // end namespace swig
} // end namespace quotation
} // end namespace xueqiao
