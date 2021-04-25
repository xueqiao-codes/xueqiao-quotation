/*
 * race_cluster_group_config.h
 *
 *  Created on: 2017年3月25日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLUSTER_GROUP_CONFIG_H_
#define QUOTATION_RACE_CLIENT_RACE_CLUSTER_GROUP_CONFIG_H_

#include "group_config.h"
#include "race_cluster_config_base.h"

namespace xueqiao {
namespace quotation {

class RaceClusterGroupConfig : public RaceClusterConfigProviderBase, public GroupConfig::ICallback {
public:
    RaceClusterGroupConfig(const char* config_file_path = "/data/configs/qconf/xueqiao/quotation/race/groups");
    virtual ~RaceClusterGroupConfig();

    virtual void onGroupConfigChanged() noexcept override;

protected:
    virtual void onReadConfig(std::map<std::string, std::shared_ptr<std::vector<RaceHostConfig>>>& configs_map
                    , std::map<std::string, std::shared_ptr<RaceHostConfig>>& master_map) noexcept override;

private:
    std::shared_ptr<GroupConfig> group_config_;
};


} // end namespace quotation
} // end namespace xueqiao


#endif /* QUOTATION_RACE_CLIENT_RACE_CLUSTER_GROUP_CONFIG_H_ */
