/*
 * race_client_factory_impl.cpp
 *
 *  Created on: 2017年3月18日
 *      Author: 44385
 */
#include "race_client_factory_impl.h"

#include "base/code_defense.h"
#include "race_client_impl.h"
#include "race_cluster_group_config.h"

namespace xueqiao {
namespace quotation {

void RaceClientFactoryImpl::Init() {
    std::unique_ptr<RaceClusterGroupConfig> group_config(new RaceClusterGroupConfig());
    group_config->InitConfig();
    Init(std::shared_ptr<IRaceClusterConfigProvider>(group_config.release()));
}

void RaceClientFactoryImpl::Init(const std::shared_ptr<IRaceClusterConfigProvider>& config_provider) {
	global_factory_.reset(new RaceClientFactoryImpl(config_provider));
}

RaceClientFactoryImpl::RaceClientFactoryImpl(const std::shared_ptr<IRaceClusterConfigProvider>& config_provider)
	: config_provider_(config_provider){
	CHECK(config_provider_.get());

	unsigned int cpu_num = std::thread::hardware_concurrency();
	if (cpu_num == 0) {
		cpu_num = 1;
	}
	context_.reset(new zmq::context_t((cpu_num > 1) ? (cpu_num -1) : 1));

	std::vector<std::string> group_name_list;
	config_provider_->getClusterGroupNameList(group_name_list);
	for (auto& group_name : group_name_list) {
		std::shared_ptr<std::vector<RaceHostConfig>> race_host_list(new std::vector<RaceHostConfig>());
		config_provider_->getClusterList(group_name, *race_host_list);
		std::shared_ptr<RaceHostConfig> master_host(new RaceHostConfig());
		config_provider_->getMaster(group_name, *master_host);
		race_clients_[group_name] = std::shared_ptr<IRaceClient>(new RaceClientImpl(group_name, context_, master_host, race_host_list));
	}

	config_provider_->registerListener(this);
}

RaceClientFactoryImpl::~RaceClientFactoryImpl() {
	config_provider_->unRegisterListener(this);
}

void RaceClientFactoryImpl::getClusterGroupNameList(std::vector<std::string>& group_names) noexcept {
    return config_provider_->getClusterGroupNameList(group_names);
}

std::shared_ptr<IRaceClient> RaceClientFactoryImpl::getClient(const std::string& group_name) noexcept {
	race_clients_lock_.lock();
	auto it = race_clients_.find(group_name);
	if (it != race_clients_.end()) {
		std::shared_ptr<IRaceClient> result = it->second;
		race_clients_lock_.unlock();
		return result;
	}
	race_clients_lock_.unlock();

	std::shared_ptr<std::vector<RaceHostConfig>> race_host_list(new std::vector<RaceHostConfig>());
	config_provider_->getClusterList(group_name, *race_host_list);
	if (race_host_list->empty()) {
		return std::shared_ptr<IRaceClient>();
	}
	std::shared_ptr<RaceHostConfig> master_host(new RaceHostConfig());
	config_provider_->getMaster(group_name, *master_host);

	return findOrInsertRaceClient(group_name, master_host, race_host_list);
}

void RaceClientFactoryImpl::setSendHWM(int hwm) noexcept {
	hwm_ = hwm;
}

void RaceClientFactoryImpl::onClusterConfigChanged(const std::string& update_group_name) noexcept {
	std::shared_ptr<std::vector<RaceHostConfig>> race_host_lists(new std::vector<RaceHostConfig>());
	config_provider_->getClusterList(update_group_name, *race_host_lists);
	std::shared_ptr<RaceHostConfig> master_host(new RaceHostConfig());
	config_provider_->getMaster(update_group_name, *master_host);

	std::shared_ptr<IRaceClient> update_race_client;
	race_clients_lock_.lock();
	auto it = race_clients_.find(update_group_name);
	if (it != race_clients_.end()) {
		update_race_client = it->second;
	}
	race_clients_lock_.unlock();
	if (update_race_client.get()) {
		static_cast<RaceClientImpl*>(update_race_client.get())->clusterConfigChanged(master_host, race_host_lists);
	}
}

void RaceClientFactoryImpl::onGroupCreated(const std::string& new_group_name) noexcept {
	std::shared_ptr<std::vector<RaceHostConfig>> race_host_lists(new std::vector<RaceHostConfig>());
	config_provider_->getClusterList(new_group_name, *race_host_lists);
	std::shared_ptr<RaceHostConfig> master_host(new RaceHostConfig());
	config_provider_->getMaster(new_group_name, *master_host);

	findOrInsertRaceClient(new_group_name, master_host, race_host_lists);
}

void RaceClientFactoryImpl::onGroupDeleted(const std::string& delete_group_name) noexcept {
	std::shared_ptr<IRaceClient> deleted_race_client;
	race_clients_lock_.lock();
	auto it = race_clients_.find(delete_group_name);
	if (it != race_clients_.end()) {
		deleted_race_client = it->second;
		race_clients_.erase(it);
	}
	race_clients_lock_.unlock();
}

std::shared_ptr<IRaceClient> RaceClientFactoryImpl::findOrInsertRaceClient(
			const std::string& group_name
			, const std::shared_ptr<RaceHostConfig>& master_host
			, const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists) {
	std::shared_ptr<IRaceClient> result_race_client;
	race_clients_lock_.lock();
	auto it = race_clients_.find(group_name);
	if (it != race_clients_.end()) {
		result_race_client = it->second;
	} else {
		result_race_client.reset(new RaceClientImpl(group_name, context_, master_host, race_host_lists, hwm_));
		race_clients_[group_name] = result_race_client;
	}
	race_clients_lock_.unlock();
	return result_race_client;
}


} // end namespace quotation
} // end namespace xueqiao




