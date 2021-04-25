/*
 * race_cluster_config.h
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLUSTER_CONFIG_H_
#define QUOTATION_RACE_CLIENT_RACE_CLUSTER_CONFIG_H_

#include <string>
#include <sstream>
#include <vector>

namespace xueqiao {
namespace quotation {

class RaceHostConfig {
public:
	RaceHostConfig() {
	}

	RaceHostConfig(const std::string& host_ip, int host_port)
		:host_ip_(host_ip), host_port_(host_port) {
	}

	RaceHostConfig(const RaceHostConfig&) = default;
	~RaceHostConfig() = default;
	RaceHostConfig& operator=(const RaceHostConfig&) = default;

	bool operator==(const RaceHostConfig& config) const {
		return host_ip_ == config.host_ip_ && host_port_ == config.host_port_;
	}

	bool operator!=(const RaceHostConfig& config) const {
	    return !(this->operator ==(config));
	}

	bool operator <(const RaceHostConfig& other) const {
		if (host_ip_ < other.host_ip_) {
			return true;
		} else if (host_ip_ ==  other.host_ip_) {
			return host_port_ < other.host_port_;
		}
		return false;
	}

	const std::string& getHostIp() const {
		return host_ip_;
	}

	void setHostIp(const std::string& host_ip) { host_ip_ = host_ip; }

	const int& getHostPort() const {
		return host_port_;
	}

	void setHostPort(int host_port) {
		host_port_ = host_port;
	}

	std::string urlDesc() const {
		std::stringstream ss;
		ss << host_ip_ << ":" << host_port_;
		return ss.str();
	}

private:
	std::string host_ip_;
	int host_port_ = 0;
};

class IRaceClusterConfigListener {
public:
	virtual ~IRaceClusterConfigListener() = default;

	virtual void onGroupCreated(const std::string& new_group_name) noexcept = 0;
	virtual void onClusterConfigChanged(const std::string& update_group_name) noexcept = 0;
	virtual void onGroupDeleted(const std::string& delete_group_name) noexcept = 0;

protected:
	IRaceClusterConfigListener() = default;
};

class IRaceClusterConfigProvider {
public:
	virtual ~IRaceClusterConfigProvider() = default;

	virtual void getClusterList(
			const std::string& group_name
			, std::vector<RaceHostConfig>& race_host_list) noexcept = 0;

	virtual void getMaster(
	        const std::string& group_name
	        , RaceHostConfig& master_host) noexcept = 0;

	virtual void getClusterGroupNameList(
			std::vector<std::string>& group_name_list) noexcept = 0;

	virtual void registerListener(IRaceClusterConfigListener* listener) noexcept = 0;
	virtual void unRegisterListener(IRaceClusterConfigListener* listener) noexcept = 0;

protected:
	IRaceClusterConfigProvider() = default;

};

} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_CLIENT_RACE_CLUSTER_CONFIG_H_ */
