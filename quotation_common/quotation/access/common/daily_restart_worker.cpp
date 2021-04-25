/*
 * daily_restart_worker.cpp
 *
 *  Created on: 2017年9月1日
 *      Author: wileywang
 */
#include "daily_restart_worker.h"

#include <boost/date_time/gregorian/gregorian.hpp>
#include <boost/date_time/posix_time/posix_time.hpp>
#include <boost/lexical_cast.hpp>
#include <chrono>
#include <memory>

#include <stdint.h>
#include "base/app_log.h"
#include "base/string_util.h"

namespace xueqiao {
namespace quotation {
namespace access {

bool formatClock(const std::string& clock
        , std::pair<int32_t, int32_t>& hour_minute) {
    if (clock.length() != 5 || clock[2] != ':') {
        return false;
    }

    try {
        hour_minute.first = boost::lexical_cast<int32_t>(clock.substr(0, 2));
        hour_minute.second = boost::lexical_cast<int32_t>(clock.substr(3, 2));
    } catch (boost::bad_lexical_cast& e) {
        return false;
    }

    if (hour_minute.first < 0 || hour_minute.first >= 24) {
        return false;
    }
    if (hour_minute.second < 0 || hour_minute.second >= 60) {
        return false;
    }

    return true;
}


bool DailyRestartWorker::start(const std::string& restart_clock_config) {
    if (restart_clock_config.empty()) {
        return false;
    }

    std::vector<std::string> restart_clocks;
    soldier::base::StringUtil::tokenize(restart_clock_config, restart_clocks, ";", true);

    std::vector<int32_t> restart_day_seconds_list;
    for (auto& restart_clock : restart_clocks) {
        std::pair<int32_t, int32_t> hour_minute;
        if (formatClock(restart_clock, hour_minute)) {
            restart_day_seconds_list.push_back(hour_minute.first * 3600 + hour_minute.second * 60);
        } else {
            APPLOG_ERROR("restartClock containes error clock {}", restart_clock);
        }
    }
    if (restart_day_seconds_list.empty()) {
        return false;
    }

    // 寻找最近的重启时间点,获取当前的时分
    const boost::posix_time::ptime now = boost::posix_time::second_clock::local_time();
    int32_t current_hour = now.time_of_day().hours();
    int32_t current_minute = now.time_of_day().minutes();
    int32_t current_seconds = now.time_of_day().seconds();
    int32_t current_day_seconds = current_hour * 3600 + current_minute * 60 + current_seconds;

    APPLOG_WARN("current time is {}:{}:{}, current_day_seconds={}",
            current_hour, current_minute, current_seconds, current_day_seconds);

    int32_t min_period = 24 * 3600;
    for (auto restart_day_seconds : restart_day_seconds_list) {
        int32_t period = restart_day_seconds - current_day_seconds;
        if (period <= 0) {
            period += 24 * 3600;
        }
        if (period < min_period) {
            min_period = period;
        }
    }

    std::unique_ptr<std::thread> timer_thread(new std::thread(&DailyRestartWorker::onWaitingRestart, min_period));
    timer_thread->detach();
    return true;
}


void DailyRestartWorker::onWaitingRestart(int32_t waiting_seconds) {
    APPLOG_WARN("DailyRestartWorker::onWaitingRestart after {} seconds, that is {} hour and {} minutes"
                        , waiting_seconds, waiting_seconds/3600, (waiting_seconds%3600)/60);

    std::this_thread::sleep_for(std::chrono::seconds(waiting_seconds));
    APPLOG_WARN("DailyRestartWorker cause ternmiate for restart");
    soldier::base::AppLog::FlushLog();
    std::terminate();
}


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao


