/*
 * daily_restart_worker.h
 *
 *  Created on: 2017年9月1日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_COMMON_DAILY_RESTART_WORKER_H_
#define QUOTATION_ACCESS_COMMON_DAILY_RESTART_WORKER_H_

#include <condition_variable>
#include <memory>
#include <mutex>
#include <string>
#include <thread>

namespace xueqiao {
namespace quotation {
namespace access {

class DailyRestartWorker {
public:
    static bool start(const std::string& restart_clock_config);

private:
    static void onWaitingRestart(int32_t waiting_seconds);
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_DAILY_RESTART_WORKER_H_ */
