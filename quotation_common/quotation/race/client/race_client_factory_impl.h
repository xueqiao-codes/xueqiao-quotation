/*
 * race_client_factory_impl.h
 *
 *  Created on: 2017年3月18日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLIENT_FACTORY_IMPL_H_
#define QUOTATION_RACE_CLIENT_RACE_CLIENT_FACTORY_IMPL_H_

#include <memory>
#include <mutex>

#include "zmq.hpp"
#include "race_client_factory.h"

namespace xueqiao {
namespace quotation {

class RaceClientFactoryImpl : public RaceClientFactory, public IRaceClusterConfigListener {
public:
    static void Init();
	static void Init(const std::shared_ptr<IRaceClusterConfigProvider>& config_provider);

	virtual ~RaceClientFactoryImpl();

	virtual void getClusterGroupNameList(std::vector<std::string>& group_names) noexcept;
	virtual std::shared_ptr<IRaceClient> getClient(const std::string& group_name) noexcept;
	virtual void setSendHWM(int hwm) noexcept;

	virtual void onGroupCreated(const std::string& new_group_name) noexcept;
	virtual void onClusterConfigChanged(const std::string& update_group_name) noexcept;
	virtual void onGroupDeleted(const std::string& delete_group_name) noexcept;

protected:
	RaceClientFactoryImpl(const std::shared_ptr<IRaceClusterConfigProvider>& config_provider);

private:
	std::shared_ptr<IRaceClient> findOrInsertRaceClient(
			const std::string& group_name
			, const std::shared_ptr<RaceHostConfig>& master_host
			, const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists);

	std::shared_ptr<IRaceClusterConfigProvider> config_provider_;

	std::mutex race_clients_lock_;
	std::map<std::string, std::shared_ptr<IRaceClient>> race_clients_;

	std::shared_ptr<zmq::context_t> context_;

	int hwm_ = 100;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_CLIENT_RACE_CLIENT_FACTORY_IMPL_H_ */
