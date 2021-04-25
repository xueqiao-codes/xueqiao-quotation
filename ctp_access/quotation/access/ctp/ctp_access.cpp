/*
 * ctp_access.cpp
 *
 *  Created on: 2017年4月13日
 *      Author: wileywang
 */
#include <iostream>
#include <stdlib.h>

#include "attr/attr_reporter_factory.h"
#include "base/app_log.h"
#include "base/code_defense.h"
#include "base/net_helper.h"
#include "base/time_helper.h"
#include "base/string_util.h"
#include "group_chooser.h"
#include "daily_restart_worker.h"
#include "quotation/access/ctp/ctp_access_processor.h"
#include "race_client_factory_impl.h"
#include "effective_reporter.h"

static const std::string LOG_MODULE("apps/ctp_access");
static const std::string FLOW_PATH_TRADER("/data/applog/apps/ctp_access/trader/");
static const std::string FLOW_PATH_MD("/data/applog/apps/ctp_access/md/");

using namespace xueqiao::quotation;
using namespace xueqiao::quotation::access;
using namespace soldier::base;
using namespace soldier::attr;

int main() {
    soldier::base::AppLog::Init(LOG_MODULE);
    mkdir(FLOW_PATH_TRADER.c_str(), 0755);
    mkdir(FLOW_PATH_MD.c_str(), 0755);

    RaceClientFactoryImpl::Init();
    std::shared_ptr<GroupChooser> group_chooser = GroupChooser::newInstance();
    std::shared_ptr<AccountConfig> account_config(new AccountConfig());

    const char* env_account_id = getenv("ACCOUNT_ID");
    if (env_account_id == NULL) {
        std::cout << "env ACCOUNT_ID is null! please set!" << std::endl;
        return -1;
    }
    int64_t quotation_account_id = boost::lexical_cast<int64_t>(env_account_id);

    const char* env_platform = getenv("PLATFORM");
    if (env_platform == NULL) {
        std::cout << "env PLATFORM is null! please set!" << std::endl;
        return -1;
    }
    account_config->setPlatform(env_platform);

    const char* env_user_name = getenv("USER_NAME");
    if (env_user_name == NULL) {
        std::cout << "env USER_NAME is null! please set!" << std::endl;
        return -1;
    }
    account_config->setUserName(env_user_name);

    const char* env_user_passwd = getenv("USER_PASSWD");
    if (env_user_passwd == NULL) {
        std::cout << "env USER_PASSWD is null! please set!" << std::endl;
        return -1;
    }
    account_config->setUserPasswd(env_user_passwd);

    const char* env_broker_id = getenv("BROKER_ID");
    if (env_broker_id == NULL) {
        std::cout << "env BROKER_ID is null! please set!" << std::endl;
        return -1;
    }
    account_config->setProperty("brokerId", env_broker_id);

    const char* env_trade_frontend = getenv("TRADE_FRONTEND");
    if (env_trade_frontend == NULL) {
        std::cout << "env TRADE_FRONTEND is null! please set!" << std::endl;
        return -1;
    }
    account_config->setProperty("tradeFrontend", env_trade_frontend);

    const char* env_md_frontend = getenv("MD_FRONTEND");
    if (env_md_frontend == NULL) {
        std::cout << "env MD_FRONTEND is null! please set!" << std::endl;
        return -1;
    }
    account_config->setProperty("mdFrontend", env_md_frontend);

    const char* env_sts_appid = getenv("STS_APPID");
    if (env_sts_appid == NULL) {
        std::cout << "env STS_APPID is null! please set!" << std::endl;
        return -1;
    }
    account_config->setProperty("stsAppId", env_sts_appid);

    const char* env_sts_authcode = getenv("STS_AUTHCODE");
    if (env_sts_authcode == NULL) {
        std::cout << "env STS_AUTHCODE is null! please set!" << std::endl;
        return -1;
    }
    account_config->setProperty("stsAuthCode", env_sts_authcode);

    const char* env_restart_clocks = getenv("RESTART_CLOCKS");
    if (env_restart_clocks == NULL) {
        std::cout << "env RESTART_CLOCKS is null! please set!" << std::endl;
        return -1;
    }
    std::string restart_clocks_config = env_restart_clocks;
    if (!restart_clocks_config.empty()) {
        CHECK(DailyRestartWorker::start(restart_clocks_config));
    }

    std::shared_ptr<CTPAccessProcessor> access_processor(
            new CTPAccessProcessor(account_config, group_chooser,FLOW_PATH_TRADER, FLOW_PATH_MD));
    access_processor->start();

    EffectiveReporter::Global().setEffective();
    QuotationAccessServiceImpl::runLoop(quotation_account_id, access_processor);

    return 0;
}

