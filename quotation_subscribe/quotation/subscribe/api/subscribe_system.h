/*
 * subscribe_system.h
 *
 *  配接层
 *
 *  Created on: 2017年4月3日
 *      Author: 44385
 */

#ifndef QUOTATION_SUBSCRIBE_SUBSCRIBE_SYSTEM_H_
#define QUOTATION_SUBSCRIBE_SUBSCRIBE_SYSTEM_H_

#include <map>
#include <memory>
#include <mutex>
#include <vector>
#include "group_config.h"
#include "quotation/subscribe/api/group_receivor.h"
#include "quotation/subscribe/api/topic.h"
#include "quotation/subscribe/api/subscribe_callback.h"

namespace xueqiao {
namespace quotation {

class SubscribeSystem : public GroupReceivor::ICallback, public GroupConfig::ICallback {
public:
    static void Init(const std::shared_ptr<ISubscribeCallback>& callback);
    static void Destroy();
    static SubscribeSystem* Get();

    virtual ~SubscribeSystem() = default;

    void addTopic(const Topic& topic);
    void removeTopic(const Topic& topic);

    virtual void onReceivedItemData(const Topic& topic, const void* p_data, size_t size) noexcept;
    virtual void onGroupConfigChanged() noexcept;

private:
    SubscribeSystem(const std::shared_ptr<ISubscribeCallback>& callback);

    static SubscribeSystem* s_instance_;

    std::shared_ptr<ISubscribeCallback> callback_;

    std::mutex topic_lock_;
    std::map<Topic, bool> register_topics_map_;

    std::shared_ptr<GroupConfig> group_config_;
    std::mutex group_receivor_lock_;
    std::map<std::string, std::shared_ptr<GroupReceivor>> group_receivors_;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_SUBSCRIBE_SUBSCRIBE_SYSTEM_H_ */
