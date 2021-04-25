/*
 * subscribe_system_test.cpp
 *
 *  Created on: 2017年4月6日
 *      Author: wileywang
 */
#include "quotation/subscribe/api/subscribe_system.h"

#include "attr/attr_reporter_factory.h"
#include "base/app_log.h"

#include <iostream>

using namespace soldier::base;
using namespace xueqiao::quotation;
using namespace soldier::attr;

class SubscribeSystemCallback : public ISubscribeCallback {
public:
    virtual void onReceivedItemData(const Topic& topic, uint8_t* data , size_t size) {
        APPLOG_DEBUG("receive topic {}", topic.description());
        AttrReporterFactory::Global().thirtySecs().inc(
                        AttrReporterFactory::Global().thirtySecs().requireKey("quotation.subscribe.received.count"
                                , {{"program_name","subscribe_system_test"}})
                        , 1);
    }
};

int main() {
    AppLog::Init("subscribe_system_test");

    SubscribeSystem::Init(std::shared_ptr<SubscribeSystemCallback>(new SubscribeSystemCallback()));

    SubscribeSystem::Get()->addTopic(Topic("SXQ", "11893"));
    SubscribeSystem::Get()->addTopic(Topic("Test", "CONTRACT02"));

    getchar();
    SubscribeSystem::Get()->removeTopic(Topic("Test", "CONTRACT01"));

    getchar();
    SubscribeSystem::Get()->addTopic(Topic("Test", "CONTRACT01"));

    getchar();

    return 0;
}
