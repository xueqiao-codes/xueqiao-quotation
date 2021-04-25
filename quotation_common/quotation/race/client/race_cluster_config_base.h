/*
 * race_cluster_config_impl.h
 *
 *  Created on: 2017年3月12日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLUSTER_CONFIG_BASE_H_
#define QUOTATION_RACE_CLIENT_RACE_CLUSTER_CONFIG_BASE_H_

#include <atomic>
#include <mutex>
#include <vector>
#include <map>
#include <thread>
#include "race_cluster_config.h"


namespace xueqiao {
namespace quotation {


class RaceClusterConfigProviderBase : public IRaceClusterConfigProvider {
public:
	virtual ~RaceClusterConfigProviderBase() = default;

	void InitConfig();

	virtual void registerListener(IRaceClusterConfigListener* listener) noexcept;
	virtual void unRegisterListener(IRaceClusterConfigListener* listener) noexcept;

	virtual void getClusterList(
	                const std::string& group_name
	                , std::vector<RaceHostConfig>& race_host_list) noexcept;
	virtual void getClusterGroupNameList(
	                std::vector<std::string>& group_name_list) noexcept;
	virtual void getMaster(const std::string& group_name
                    , RaceHostConfig& master_host) noexcept;

protected:
	RaceClusterConfigProviderBase() = default;

	void checkConfig();

	virtual void onReadConfig(std::map<std::string, std::shared_ptr<std::vector<RaceHostConfig>>>& configs_map
	              , std::map<std::string, std::shared_ptr<RaceHostConfig>>& master_map) noexcept = 0;

	std::mutex listener_lock_;
	std::vector<IRaceClusterConfigListener*> listeners_;

	std::mutex configs_lock_;
	std::map<std::string, std::shared_ptr<std::vector<RaceHostConfig>>> configs_map_;
	std::map<std::string, std::shared_ptr<RaceHostConfig>> master_map_;
};


class RaceClusterConfigWithTimer : public RaceClusterConfigProviderBase {
public:
	virtual ~RaceClusterConfigWithTimer();

protected:
	RaceClusterConfigWithTimer(int check_period_seconds);

private:
	void onTimer();

	const int check_period_seconds_;
	std::unique_ptr<std::thread> watch_timer_thread_;
	std::atomic_bool end_watching_ = {false};
};



} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_CLIENT_RACE_CLUSTER_CONFIG_BASE_H_ */
