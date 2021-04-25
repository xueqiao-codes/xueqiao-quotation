/*
 * race_client_factory.h
 *
 *  Created on: 2017年3月11日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLIENT_FACTORY_H_
#define QUOTATION_RACE_CLIENT_RACE_CLIENT_FACTORY_H_

#include "race_client.h"
#include "race_cluster_config.h"

namespace xueqiao {
namespace quotation {

class RaceClientFactory {
public:
	static RaceClientFactory* Global();
	static void Destroy();

	virtual ~RaceClientFactory() = default;

	virtual void setSendHWM(int hwm) noexcept = 0;

	virtual void getClusterGroupNameList(std::vector<std::string>& group_names) noexcept = 0;
	virtual std::shared_ptr<IRaceClient> getClient(const std::string& group_name) noexcept = 0;

protected:
	RaceClientFactory() = default;

	static std::unique_ptr<RaceClientFactory> global_factory_;

};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_CLIENT_RACE_CLIENT_FACTORY_H_ */
