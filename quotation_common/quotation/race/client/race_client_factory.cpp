/*
 * race_client_factory.cpp
 *
 *  Created on: 2017年3月18日
 *      Author: 44385
 */
#include "race_client_factory.h"

#include "base/app_log.h"

namespace xueqiao {
namespace quotation {

std::unique_ptr<RaceClientFactory> RaceClientFactory::global_factory_ = nullptr;

RaceClientFactory* RaceClientFactory::Global() {
	if (global_factory_.get() == nullptr) {
		APPLOG_FATAL("RaceClientFactory has not initilize one implemention");
	}
	return global_factory_.get();
}

void RaceClientFactory::Destroy() {
	global_factory_.reset();
}


} // end namespace quotation
} // end namespace xueqiao



