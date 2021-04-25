/*
 * monitor.cpp
 *
 *  Created on: 2018年11月24日
 *      Author: wangli
 */

#ifndef QUOTATION_PROCESS_MONITOR_CPP_
#define QUOTATION_PROCESS_MONITOR_CPP_

#include <boost/lexical_cast.hpp>
#include <stdio.h>
#include <stdlib.h>
#include <chrono>
#include <thread>
#include <signal.h>
#include <sys/wait.h>

#include "accessstate_reporter.h"
#include "base/app_log.h"
#include "base/code_defense.h"
#include "subprocess_manager.h"
#include "effective_manager.h"
#include "route_finder.h"

using namespace soldier::base;
using namespace xueqiao::quotation::process;

static void onProcessSignal() {
    sigset_t waitset;
    sigemptyset(&waitset);
    sigaddset(&waitset, SIGCHLD);

    while (true)  {
        siginfo_t info;
        int rc = sigwaitinfo(&waitset, &info);
        if (rc != -1) {
            APPLOG_INFO("onProcessSignal fetch the signal {}", rc);
            SubProcessManager::Global().checkSubProcesses();
        } else {
            APPLOG_ERROR("onProcessSignal sigwaitinfo returned err: {}, {}", errno, strerror(errno));
        }
    }
}

static void onSubProcessCheckTimer() {
    while(true) {
        std::this_thread::sleep_for(std::chrono::seconds(10));
        SubProcessManager::Global().retryProcesses();
    }
}

class ProcessRestartWorker : public IRestartWorker {
public:
    ProcessRestartWorker(const std::string& process_name)
    : process_name_(process_name) {
    }

    virtual void onRestartProcess(int64_t quotation_account_id) {
        SubProcessManager::Global().restartSubProcess(process_name_);
    }

private:
    std::string process_name_;
};


int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "please input the sub process execute command\n");
        return 1;
    }

    const char* env_account_id = getenv("ACCOUNT_ID");
    if (env_account_id == NULL) {
        fprintf(stderr, "please set env for ACCOUNT_ID !\n");
        return 1;
    }
    int64_t quotation_account_id = boost::lexical_cast<int64_t>(env_account_id);

    sigset_t bset, oset;
    sigemptyset(&bset);
    sigaddset(&bset, SIGCHLD);
    if (pthread_sigmask(SIG_BLOCK, &bset, &oset) != 0) {
        fprintf(stderr, "Set pthread mask failed\n");
        return 1;
    }

    AppLog::Init("apps/quotation_process_monitor");
    CHECK(0 == platform::InitRouteFinder());

    std::shared_ptr<AccessStateReporter> reporter(new AccessStateReporter(quotation_account_id));
    SubProcessManager::Global().addListener(reporter);
    SubProcessManager::Global().addSubProcess(argv[1]);

    EffectiveManager::Global().setRestartWorker(std::shared_ptr<IRestartWorker>(new ProcessRestartWorker(argv[1])));
    EffectiveManager::Global().addQuotationAccount(quotation_account_id);

    std::thread subprocess_check_timer_thread(&onSubProcessCheckTimer);
    subprocess_check_timer_thread.detach();

    onProcessSignal();
    return 0;
}



#endif /* QUOTATION_PROCESS_MONITOR_CPP_ */
