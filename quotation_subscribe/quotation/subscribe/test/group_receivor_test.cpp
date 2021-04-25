/*
 * group_receivor_test.cpp
 *
 *  Created on: 2017年4月3日
 *      Author: 44385
 */

#include <iostream>
#include "base/app_log.h"
#include "attr/attr_reporter_factory.h"
#include "quotation/subscribe/api/group_receivor.h"

using namespace soldier::attr;
using namespace xueqiao::quotation;

static std::unique_ptr<GroupReceivor> group1_receivor_;

class TestCallback : public GroupConfig::ICallback, public GroupReceivor::ICallback {
public:
    TestCallback() = default;
    virtual ~TestCallback() = default;

    virtual void onGroupConfigChanged() noexcept {
        std::cout << "onGroupConfigChanged..." << std::endl;
        if (group1_receivor_) {
            group1_receivor_->onGroupItemConfigChanged();
        }
    }

    virtual void onReceivedItemData(const Topic& topic
            , const void* p_data
            , size_t size) noexcept {
//        APPLOG_DEBUG("receive item platform={}, contract_symbols={}", topic.Platform(), topic.contractSymbol());
        AttrReporterFactory::Global().thirtySecs().inc(
                AttrReporterFactory::Global().thirtySecs().requireKey("quotation.subscribe.received.count"
                        , {{"program_name","group_receivor_test"}})
                , 1);
    }
};

static std::shared_ptr<TestCallback> group_changed_callback_(new TestCallback());

int main() {
    soldier::base::AppLog::Init("group_receivor_test");

    std::shared_ptr<GroupConfig> group_config =
            GroupConfig::newInstance("/data/configs/qconf/xueqiao/quotation/race/groups", group_changed_callback_.get());

    std::map<Topic, bool> topics;
    topics[Topic("Test", "CONTRACT01")] = true;
    group1_receivor_.reset(new GroupReceivor(group_config, "group1", topics, group_changed_callback_.get()));

    getchar();
    std::cout << "remove topic ..." << std::endl;
    group1_receivor_->removeTopic(Topic("Test", "CONTRACT01"));

    getchar();
    std::cout << "add topic..." << std::endl;
    group1_receivor_->addTopic(Topic("Test", "CONTRACT01"));

    getchar();

    group1_receivor_.reset();
    group_config.reset();

    return 0;
}


