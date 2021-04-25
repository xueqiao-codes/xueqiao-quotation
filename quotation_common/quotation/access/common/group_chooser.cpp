/*
 * group_chooser.cpp
 *
 *  Created on: 2017年4月22日
 *      Author: 44385
 */
#include "group_chooser.h"

#include "base/hash.h"
#include "base/time_helper.h"
#include "base/string_util.h"
#include "race_client_factory.h"

using namespace soldier::base;

namespace xueqiao {
namespace quotation {
namespace access {

#define CONFIG_EFFECTIVE_TIMEMS  5000

struct QconfConfigEntry {
    int64_t last_access_timestampms_ = 0;
    std::vector<std::string> enabled_group_names_;
};

static thread_local QconfConfigEntry s_config_entry;

std::shared_ptr<GroupChooser> GroupChooser::newInstance() {
    return std::shared_ptr<GroupChooser>(new GroupChooser());
}

GroupChooser::~GroupChooser() {
}

std::string GroupChooser::chooseGroup(const std::string& key) {
    if (s_config_entry.last_access_timestampms_ + CONFIG_EFFECTIVE_TIMEMS < NowInMilliSeconds()) {
        RaceClientFactory::Global()->getClusterGroupNameList(s_config_entry.enabled_group_names_);
        s_config_entry.last_access_timestampms_ = NowInMilliSeconds();
    }

    if (s_config_entry.enabled_group_names_.empty()) {
        return "";
    }

    return s_config_entry.enabled_group_names_[jsHash(key.c_str(), key.length())%s_config_entry.enabled_group_names_.size()];
}


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao

