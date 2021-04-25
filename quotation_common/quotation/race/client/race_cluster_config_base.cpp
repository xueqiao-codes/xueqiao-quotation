/*
 * race_cluster_config_impl.cc
 *
 *  Created on: 2017年3月12日
 *      Author: 44385
 */
#include <algorithm>
#include "race_cluster_config_base.h"

#include "base/app_log.h"
#include "base/code_defense.h"

namespace xueqiao {
namespace quotation {

void RaceClusterConfigProviderBase::registerListener(IRaceClusterConfigListener* listener) noexcept {
	if (listener == nullptr) {
		return ;
	}

	std::unique_lock<std::mutex> auto_lock(listener_lock_);
	if (listeners_.end() == std::find(listeners_.begin(), listeners_.end(), listener)) {
		listeners_.push_back(listener);
	}
}

void RaceClusterConfigProviderBase::unRegisterListener(IRaceClusterConfigListener* listener) noexcept {
	if (listener == nullptr) {
		return ;
	}

	std::unique_lock<std::mutex> auto_lock(listener_lock_);
	for (auto iter = listeners_.begin(); iter != listeners_.end();) {
		if (*iter == listener) {
			listeners_.erase(iter);
			break;
		}
		++iter;
	}
}

void RaceClusterConfigProviderBase::getClusterList(
                const std::string& group_name
                , std::vector<RaceHostConfig>& race_host_list) noexcept {
    race_host_list.clear();

    configs_lock_.lock();
    auto it = configs_map_.find(group_name);
    if (it != configs_map_.end()) {
        if (it->second.get() != nullptr) {
            race_host_list = *(it->second);
        }
    }
    configs_lock_.unlock();
}

void RaceClusterConfigProviderBase::getClusterGroupNameList(
                std::vector<std::string>& group_name_list) noexcept {
    group_name_list.clear();
    configs_lock_.lock();
    for (auto& config_pair : configs_map_) {
        group_name_list.push_back(config_pair.first);
    }
    configs_lock_.unlock();
}

void RaceClusterConfigProviderBase::getMaster(const std::string& group_name
                , RaceHostConfig& master_host) noexcept {
    master_host.setHostIp("");
    master_host.setHostPort(0);
    configs_lock_.lock();
    auto it = master_map_.find(group_name);
    if (it != master_map_.end()) {
        if (it->second.get() != nullptr) {
            master_host = *(it->second);
        }
    }
    configs_lock_.unlock();
}


void RaceClusterConfigProviderBase::InitConfig() {
    checkConfig();
}

void RaceClusterConfigProviderBase::checkConfig() {
    std::map<std::string, std::shared_ptr<std::vector<RaceHostConfig>>> now_configs_map;
    std::map<std::string, std::shared_ptr<RaceHostConfig>> now_master_map;
    onReadConfig(now_configs_map, now_master_map);

    std::map<std::string, std::shared_ptr<std::vector<RaceHostConfig>>> origin_configs_map(configs_map_);
    std::map<std::string, std::shared_ptr<RaceHostConfig>> origin_master_map(master_map_);

    std::vector<std::string> new_group_list;
    std::vector<std::string> update_group_list;
    std::vector<std::string> delete_group_list;
    std::vector<std::string> unexpected_group_list;

    for (auto& now_pair : now_configs_map) {
        auto now_master_it = now_master_map.find(now_pair.first);
        if (now_master_it == now_master_map.end() || now_master_it->second.get() == nullptr) {
            APPLOG_ERROR("config error, no master found for group({})", now_pair.first);
            unexpected_group_list.push_back(now_pair.first);
            continue;
        }
        if (now_pair.second.get() == nullptr || now_pair.second->empty()) {
            APPLOG_ERROR("config error, host list is empty for group {}", now_pair.first);
            unexpected_group_list.push_back(now_pair.first);
            continue;
        }
        if (now_pair.second->end()
            == std::find(now_pair.second->begin(), now_pair.second->end(), *(now_master_it->second))) {
            APPLOG_ERROR("config error, master {} is not in hosts", now_master_it->second->urlDesc());
            unexpected_group_list.push_back(now_pair.first);
            continue;
        }

        auto it = origin_configs_map.find(now_pair.first);
        if (it == origin_configs_map.end()) {
            new_group_list.push_back(now_pair.first);
            continue;
        }

        auto origin_master_it = origin_master_map.find(now_pair.first);
        CHECK(origin_master_it != origin_master_map.end()
              && origin_master_it->second.get() != nullptr);
        CHECK(now_pair.second.get()!= nullptr);
        if (*(it->second) != *(now_pair.second)
             || *(now_master_it->second) != *(origin_master_it->second)){
            update_group_list.push_back(now_pair.first);
        }
    }

    for (auto& unexpected_group_name : unexpected_group_list) {
        now_configs_map.erase(unexpected_group_name);
        now_master_map.erase(unexpected_group_name);
        APPLOG_ERROR("erase group {} for config map", unexpected_group_name);
    }

    for (auto& origin_pair : origin_configs_map) {
        auto it = now_configs_map.find(origin_pair.first);
        if (it == now_configs_map.end()) {
            delete_group_list.push_back(origin_pair.first);
        }
    }

    configs_lock_.lock();
    configs_map_ = now_configs_map;
    master_map_ = now_master_map;
    configs_lock_.unlock();

    for (auto& group_name : new_group_list) {
        APPLOG_INFO("RaceClusterGroup {} Created!", group_name);
        listener_lock_.lock();
        for (auto listener : listeners_) {
            listener->onGroupCreated(group_name);
        }
        listener_lock_.unlock();
    }
    for (auto& group_name : update_group_list) {
        APPLOG_INFO("RaceClusterGroup {} Updated!", group_name);
        listener_lock_.lock();
        for (auto listener : listeners_) {
            listener->onClusterConfigChanged(group_name);
        }
        listener_lock_.unlock();
    }
    for (auto& group_name : delete_group_list) {
        APPLOG_INFO("RaceClusterGroup {} Deleted!", group_name);
        listener_lock_.lock();
        for (auto listener : listeners_) {
            listener->onGroupDeleted(group_name);
        }
        listener_lock_.unlock();
    }
}

RaceClusterConfigWithTimer::RaceClusterConfigWithTimer(int check_period_seconds)
	: check_period_seconds_(check_period_seconds) {
	watch_timer_thread_.reset(new std::thread(&RaceClusterConfigWithTimer::onTimer, this));
}

RaceClusterConfigWithTimer::~RaceClusterConfigWithTimer() {
	end_watching_ = true;
	watch_timer_thread_->join();
}

void RaceClusterConfigWithTimer::onTimer() {
	while(!end_watching_) {
		int sleep_count = check_period_seconds_*10;
		for (int count = 0; count < sleep_count; ++count) {
			if (end_watching_) {
				return ;
			}

			std::this_thread::sleep_for(std::chrono::milliseconds(100));
		}

		checkConfig();
	}
}

} // end namespace quotation
} // end namespace xueqiao



