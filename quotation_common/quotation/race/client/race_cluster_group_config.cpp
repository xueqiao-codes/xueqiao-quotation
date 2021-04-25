/*
 * race_cluster_group_config.cpp
 *
 *  Created on: 2017年3月25日
 *      Author: 44385
 */
#include "race_cluster_group_config.h"


namespace xueqiao {
namespace quotation {

RaceClusterGroupConfig::RaceClusterGroupConfig(const char* config_file_path) {
    group_config_ = GroupConfig::newInstance(config_file_path, this);
}

RaceClusterGroupConfig::~RaceClusterGroupConfig() {
}

void RaceClusterGroupConfig::onGroupConfigChanged() noexcept {
    checkConfig();
}

void RaceClusterGroupConfig::onReadConfig(
        std::map<std::string, std::shared_ptr<std::vector<RaceHostConfig>>>& configs_map
      , std::map<std::string, std::shared_ptr<RaceHostConfig>>& master_map) noexcept {
    std::shared_ptr<std::vector<GroupConfig::GroupItem>> groups = group_config_->getGroups();
    for (auto& group_item : *groups) {
        std::shared_ptr<std::vector<RaceHostConfig>> group_config(new std::vector<RaceHostConfig>());
        std::shared_ptr<RaceHostConfig> master_host(new RaceHostConfig());
        for (auto& host_item : *(group_item.hosts)) {
            if (host_item.frontend > 0 && !host_item.host_addr.empty()) {
                RaceHostConfig host_config(host_item.host_addr, host_item.frontend);
                if (std::find(group_config->begin(), group_config->end(), host_config) == group_config->end()) {
                    group_config->push_back(std::move(host_config));
                }
            }

            if (group_item.master->host_id == host_item.host_id) {
                master_host->setHostIp(host_item.host_addr);
                master_host->setHostPort(host_item.frontend);
            }
        }

        if (group_config->size() > 0 && master_host->getHostPort() != 0) {
            configs_map[group_item.name] = group_config;
            master_map[group_item.name] = master_host;
        }
    }
}

} // end namespace quotation
} // end namespace xueqiao



