/*
 * group_config.h
 *
 *  Created on: 2017年3月25日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_COMMON_GROUP_CONFIG_H_
#define QUOTATION_RACE_COMMON_GROUP_CONFIG_H_

#include <atomic>
#include <memory>
#include <mutex>
#include <string>
#include <thread>
#include <vector>

#include "watcher/file_watcher_module.h"

namespace xueqiao {
namespace quotation {


class GroupConfig : public soldier::watcher::IFileListener {
public:
	struct HostItem {
		std::string host_id;
		std::string host_addr;
		int frontend = 0;
		int backend = 0;
	};

	struct MasterItem {
		std::string host_id;
	};

	struct GroupItem {
		GroupItem()
			:  hosts(new std::vector<HostItem>)
			 , master(new MasterItem()){}

		std::string name;
		std::shared_ptr<std::vector<HostItem>> hosts;
		std::shared_ptr<MasterItem> master;

		template<typename OStream>
		friend OStream& operator<<(OStream& os, const GroupItem &item)
		{
		    os << "{group(" << item.name << "), hosts=[";
		    for (auto& host_item : *(item.hosts)) {
		        os << host_item.host_id
		           << "->"
		           << host_item.host_addr
		           << "("
		           << host_item.frontend << ":" << host_item.backend
		           << "); ";
		    }
		    os << "], master(" << item.master->host_id << ")";
		    return os;
		}
	};

	class ICallback {
	public:
		virtual ~ICallback() = default;

		virtual void onGroupConfigChanged() noexcept = 0;
	};

	static std::shared_ptr<GroupConfig> newInstance(
	            const std::string& config_file_path
	            , ICallback* callback);

	std::shared_ptr<std::vector<GroupItem>> getGroups() noexcept;
	virtual void onFileChanged(
	            const std::string& file_path) noexcept;

	virtual ~GroupConfig();

private:
	GroupConfig(const std::string& config_file_path
	        , ICallback* callback);

	void readConfig(bool need_notify) noexcept;

	const std::string config_file_path_;
	ICallback* callback_;

	std::shared_ptr<std::vector<GroupItem>> group_config_items_;
	std::mutex config_lock_;

	std::mutex read_lock_;
};


} // end namespace quotation
} // end namespace xueqiao


#endif /* QUOTATION_RACE_COMMON_GROUP_CONFIG_H_ */
