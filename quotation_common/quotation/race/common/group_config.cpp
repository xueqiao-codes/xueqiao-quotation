/*
 * group_config.cpp
 *
 *  Created on: 2017年3月25日
 *      Author: 44385
 */
#include "group_config.h"

#include <errno.h>
#include <spdlog/fmt/ostr.h>
#include <sys/stat.h>
#include "base/code_defense.h"
#include "base/smart_ptr_helper.h"
#include "base/time_helper.h"
#include "rapidjson/document.h"
#include "rapidjson/reader.h"
#include "rapidjson/filereadstream.h"


namespace xueqiao {
namespace quotation {

std::shared_ptr<GroupConfig> GroupConfig::newInstance(
                const std::string& config_file_path
                , ICallback* callback) {
    std::shared_ptr<GroupConfig> instance(new GroupConfig(config_file_path, callback));
    soldier::watcher::FileWatcherModule::Instance().addWatchFile(config_file_path, instance);
    instance->readConfig(false);
    return instance;
}

GroupConfig::GroupConfig(const std::string& config_file_path, ICallback* callback)
    : config_file_path_(config_file_path)
      , callback_(callback)
      , group_config_items_(new std::vector<GroupItem>()) {
}

GroupConfig::~GroupConfig() {
    APPLOG_INFO("Destroying group config {}", config_file_path_);
    soldier::watcher::FileWatcherModule::Instance().removeWatchFile(config_file_path_);
}

std::shared_ptr<std::vector<GroupConfig::GroupItem>> GroupConfig::getGroups() noexcept {
    std::shared_ptr<std::vector<GroupItem>> result;
    config_lock_.lock();
    result = group_config_items_;
    config_lock_.unlock();
    return result;
}

void GroupConfig::onFileChanged(
                const std::string& file_path) noexcept {
    if (file_path == config_file_path_) {
        readConfig(true);
    }
}

void GroupConfig::readConfig(bool need_notify) noexcept {
    APPLOG_INFO("readConfig {}, notify={}", config_file_path_, need_notify);
    std::unique_lock<std::mutex> auto_lock(read_lock_);
    int count = 0;
    while((count++) < 3) {
        std::shared_ptr<std::vector<GroupItem>> group_items(new std::vector<GroupItem>());
        std::unique_ptr<FILE, soldier::base::FileDeleter> file(fopen(config_file_path_.c_str(), "rb"));
        CHECK(file.get());

        char buffer[1024];
        rapidjson::FileReadStream stream(file.get(), buffer, sizeof(buffer)/sizeof(char));

        try {
            rapidjson::Document root;
            root.ParseStream(stream);

            if (root.HasParseError()) {
                APPLOG_ERROR("config error {}, offset={}", (int)root.GetParseError(), root.GetErrorOffset());
                std::this_thread::sleep_for(std::chrono::seconds(1));
                continue;
            }

            const rapidjson::Value& groups_array = root["groups"];
            for(rapidjson::SizeType group_index = 0; group_index < groups_array.Size(); ++group_index) {
                const rapidjson::Value& group_value = groups_array[group_index];

                GroupItem group_item;
                group_item.name = group_value["name"].GetString();

                const rapidjson::Value& hosts_array = group_value["hosts"];
                if (hosts_array.Empty()) {
                    APPLOG_ERROR("config error, group {} has no hosts!!!", group_item.name);
                    continue;
                }

                for (rapidjson::SizeType host_index = 0; host_index < hosts_array.Size(); ++host_index) {
                    const rapidjson::Value& host_value = hosts_array[host_index];

                    HostItem host_item;
                    host_item.host_id = host_value["host_id"].GetString();
                    host_item.host_addr = host_value["host_addr"].GetString();
                    host_item.frontend = host_value["frontend"].GetInt();
                    host_item.backend = host_value["backend"].GetInt();

                    group_item.hosts->push_back(std::move(host_item));
                }

                const rapidjson::Value& master_obj = group_value["master"];
                group_item.master->host_id = master_obj["host_id"].GetString();

                APPLOG_WARN("GroupConfig {}", group_item);
                group_items->push_back(std::move(group_item));
            }

            config_lock_.lock();
            group_config_items_ = group_items;
            config_lock_.unlock();

            if (callback_ && need_notify) {
                callback_->onGroupConfigChanged();
            }

            break;
        } catch (std::exception& e) {
            APPLOG_ERROR("ParseConfig Error!{}", e.what());
        }
    }
}

} // end namespace quotation
} // end namespace xueqiao




