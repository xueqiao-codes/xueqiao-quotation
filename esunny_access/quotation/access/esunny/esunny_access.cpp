/*
 * esunny_access.cpp
 *
 *  Created on: 2017年8月25日
 *      Author: wileywang
 */
#include <boost/lexical_cast.hpp>
#include <chrono>
#include <sstream>
#include <iostream>
#include "base/code_defense.h"
#include "base/app_log.h"
#include "route_finder.h"
#include "TapQuoteAPI.h"
#include "quotation/race/client/race_client_factory_impl.h"
#include "daily_restart_worker.h"
#include "group_chooser.h"
#include "effective_reporter.h"
#include "esunny_quote_worker.h"
#include "esunny_quote_handler_impl.h"

using namespace xueqiao::quotation::access;

#define BASELOG_DIR "/data/applog/apps/esunny_access"

int main(int argc, char* argv[]) {
    soldier::base::AppLog::Init("apps/esunny_access");

    CHECK(0 == platform::InitRouteFinder());

    SetTapQuoteAPIDataPath(BASELOG_DIR);
    SetTapQuoteAPILogLevel(APILOGLEVEL_NONE);

    const char* env_account_id = getenv("ACCOUNT_ID");
    if (env_account_id == NULL) {
        std::cout << "env ACCOUNT_ID is null! please set!" << std::endl;
        return -1;
    }
    int64_t quotation_account_id = boost::lexical_cast<int64_t>(env_account_id);
    if (quotation_account_id <= 0) {
        std::cout << "env ACCOUNT_ID should not <= 0" << std::endl;
        return -1;
    }

    const char* env_platform = getenv("PLATFORM");
    if (env_platform == NULL) {
        std::cout << "env PLATFORM is null! please set!" << std::endl;
        return -1;
    }

    const char* env_user_name = getenv("USER_NAME");
    if (env_user_name == NULL) {
        std::cout << "env USER_NAME is null! please set!" << std::endl;
        return -1;
    }

    const char* env_user_passwd = getenv("USER_PASSWD");
    if (env_user_passwd == NULL) {
        std::cout <<  "env USER_PASSWD is null! please set!" << std::endl;
        return -1;
    }

    const char* env_quot_addr = getenv("QUOT_ADDR");
    if (env_quot_addr == NULL) {
        std::cout << "env QUOT_ADD is null! please set!" << std::endl;
        return -1;
    }

    const char* env_auth_code = getenv("AUTH_CODE");
    if (env_auth_code == NULL) {
        std::cout <<  "env AUTH_CODE is null! please set!" << std::endl;
        return -1;
    }

    const char* env_restart_clocks = getenv("RESTART_CLOCKS");
    if (env_restart_clocks == NULL) {
        std::cout << "env RESTART_CLOCKS is null! please set!" << std::endl;
        return -1;
    }
    std::string restart_clocks_config = env_restart_clocks;
    if (!restart_clocks_config.empty()) {
        CHECK(DailyRestartWorker::start(restart_clocks_config));
    }

    xueqiao::quotation::RaceClientFactoryImpl::Init();
    std::shared_ptr<GroupChooser> group_chooser = GroupChooser::newInstance();

    std::shared_ptr<IESunnyQuoteHandler> quot_handler(new ESunnyQuoteHandlerImpl(group_chooser, env_platform));
    std::shared_ptr<ESunnyQuoteWorker> quot_worker(new ESunnyQuoteWorker(
            quotation_account_id
            , BASELOG_DIR
            , env_quot_addr
            , env_user_name
            , env_user_passwd
            , env_auth_code
            , quot_handler));
    quot_worker->start();

    EffectiveReporter::Global().setEffective();
    QuotationAccessServiceImpl::runLoop(quotation_account_id, quot_worker);

    return 0;
}
