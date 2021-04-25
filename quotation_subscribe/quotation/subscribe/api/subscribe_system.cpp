/*
 * subscribe_system.cpp
 *
 *  Created on: 2017年4月6日
 *      Author: wileywang
 */
#include "subscribe_system.h"

#include "base/app_log.h"

namespace xueqiao {
namespace quotation {

SubscribeSystem* SubscribeSystem::s_instance_ = nullptr;

static std::mutex s_subscribe_instance_lock_;
void SubscribeSystem::Init(const std::shared_ptr<ISubscribeCallback>& callback) {
    if (!s_instance_) {
        s_subscribe_instance_lock_.lock();
        if (!s_instance_) {
            s_instance_ = new SubscribeSystem(callback);
            std::atexit(&SubscribeSystem::Destroy);
        }
        s_subscribe_instance_lock_.unlock();
    }
}

void SubscribeSystem::Destroy() {
    if (s_instance_) {
        s_subscribe_instance_lock_.lock();
        if (s_instance_) {
            delete s_instance_;
            s_instance_ = nullptr;
        }
        s_subscribe_instance_lock_.unlock();
    }
}

SubscribeSystem* SubscribeSystem::Get() {
    return s_instance_;
}

SubscribeSystem::SubscribeSystem(const std::shared_ptr<ISubscribeCallback>& callback)
    : callback_(callback) {
    group_config_ = GroupConfig::newInstance("/data/configs/qconf/xueqiao/quotation/race/groups", this);

    std::shared_ptr<std::vector<GroupConfig::GroupItem>> groups =  group_config_->getGroups();
    for (auto& group : *groups) {
        group_receivors_[group.name] = std::shared_ptr<GroupReceivor>(
                new GroupReceivor(group_config_, group.name, register_topics_map_, this));
    }
}

void SubscribeSystem::addTopic(const Topic& topic) {
    topic_lock_.lock();
    auto it = register_topics_map_.find(topic);
    if (it != register_topics_map_.end()) {
        topic_lock_.unlock();
        return ;
    }

    register_topics_map_[topic] = true;
    topic_lock_.unlock();

    group_receivor_lock_.lock();
    std::map<std::string, std::shared_ptr<GroupReceivor>> cp_group_receivors(group_receivors_);
    group_receivor_lock_.unlock();

    for (auto& group_receivor_pair : cp_group_receivors) {
        group_receivor_pair.second->addTopic(topic);
    }
}

void SubscribeSystem::removeTopic(const Topic& topic) {
    topic_lock_.lock();
    auto it = register_topics_map_.find(topic);
    if (it == register_topics_map_.end()) {
        topic_lock_.unlock();
        return ;
    }

    register_topics_map_.erase(it);
    topic_lock_.unlock();

    group_receivor_lock_.lock();
    std::map<std::string, std::shared_ptr<GroupReceivor>> cp_group_receivors(group_receivors_);
    group_receivor_lock_.unlock();

    for (auto& group_receivor_pair : cp_group_receivors) {
        group_receivor_pair.second->removeTopic(topic);
    }
}

void SubscribeSystem::onReceivedItemData(const Topic& topic, const void* p_data, size_t size) noexcept {
    try {
        if (callback_) {
            callback_->onReceivedItemData(topic, (uint8_t*)p_data, size);
        }
    } catch (...) {
        APPLOG_ERROR("onRecivedItemData callback error!");
    }
}

void SubscribeSystem::onGroupConfigChanged() noexcept {
    std::shared_ptr<std::vector<GroupConfig::GroupItem>> groups =  group_config_->getGroups();

    std::vector<std::string> new_group_names;
    std::vector<std::string> maybe_changed_group_names;
    std::vector<std::string> removed_group_names;
    for (auto& group : *groups) {
        if (group_receivors_.find(group.name) == group_receivors_.end()) {
            new_group_names.push_back(group.name);
        } else {
            maybe_changed_group_names.push_back(group.name);
        }
    }
    for (auto& current_group_pair : group_receivors_) {
        int index = 0;
        for (; index < (int)groups->size(); ++index) {
            if ((*groups)[index].name == current_group_pair.first) {
                break;
            }
        }
        if (index >= (int)groups->size()) {
            removed_group_names.push_back(current_group_pair.first);
        }
    }

    for (auto& name : maybe_changed_group_names) {
        group_receivors_[name]->onGroupItemConfigChanged();
    }

    group_receivor_lock_.lock();
    for (auto& name : removed_group_names) {
        APPLOG_INFO("SubscribeSystem Removed group {}", name);
        group_receivors_.erase(name);
    }

    topic_lock_.lock();
    for (auto& name : new_group_names) {
        APPLOG_INFO("SubscribeSystem Add Group {} ", name);
        group_receivors_[name] = std::shared_ptr<GroupReceivor>(
                        new GroupReceivor(group_config_, name, register_topics_map_, this));
    }
    topic_lock_.unlock();
    group_receivor_lock_.unlock();
}


} // end namespace quotation
} // end namespace xueqiao



