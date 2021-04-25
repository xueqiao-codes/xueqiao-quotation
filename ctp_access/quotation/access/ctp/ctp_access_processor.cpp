/*
 * ctp_access_processor.cpp
 *
 *  Created on: 2017年4月23日
 *      Author: 44385
 */

#include "quotation/access/ctp/ctp_access_processor.h"

#include "base/code_defense.h"
#include "base/string_util.h"
#include "accessstate_holder.h"
#include "effective_reporter.h"
#include "quotation_common/thrift/quotation_item_constants.h"

#define CTP_INVALID_LOGIN 3
#define CTP_USER_NOT_ACTIVE 4
#define CTP_USER_NOT_FOUND 11
#define CTP_BROKER_NOT_FOUND 12
#define CTP_INVALID_INVESTORIDORPASSWORD 48
#define CTP_INVALID_LOGIN_IPADDRESS 49
#define CTP_LOGIN_FORBIDDEN 75

namespace xueqiao {
namespace quotation {
namespace access {

CTPAccessProcessor::CTPAccessProcessor(const std::shared_ptr<AccountConfig>& account_config
        , const std::shared_ptr<GroupChooser>& group_chooser
        , const std::string& flow_path_trader
        , const std::string& flow_path_md)
    : account_config_(account_config)
      , attr_reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs()) {
    CHECK(account_config);
    CHECK(account_config->getPlatform() == ::xueqiao::quotation::g_quotation_item_constants.PLATFORM_CTP
            || account_config->getPlatform() == ::xueqiao::quotation::g_quotation_item_constants.PLATFORM_SIMU_CTP);
    CHECK(!account_config->getUserName().empty());
    CHECK(!account_config->getUserPasswd().empty());
    CHECK(!account_config->getProperty("tradeFrontend").empty());
    CHECK(!account_config->getProperty("mdFrontend").empty());
    CHECK(!account_config->getProperty("brokerId").empty());
    CHECK(!account_config->getProperty("stsAppId").empty());
    CHECK(!account_config->getProperty("stsAuthCode").empty());

    failed_call_key_ = attr_reporter_.requireKey("quotation.access.call.failed"
            , {{"platform", account_config->getPlatform()}});

    trader_proxy_.reset(new CTPTraderProxy(account_config->getProperty("tradeFrontend"), flow_path_trader));
    md_proxy_.reset(new CTPMDProxy(account_config->getProperty("mdFrontend")
            , flow_path_md, group_chooser, account_config->getPlatform()));

    work_thread_.reset(new soldier::base::TaskThread);
}

void CTPAccessProcessor::start() {
    is_onstart_successful_ = false;
    work_thread_->postTask(&CTPAccessProcessor::OnStart, this);
}

void CTPAccessProcessor::sendUpsideHeartBeat() {
    if (!is_onstart_successful_) {
        return ;
    }
    work_thread_->postTask(&CTPAccessProcessor::OnHeartBeat, this);
}

void CTPAccessProcessor::OnStart() {
    while(!trader_proxy_->isConnected()) {
        APPLOG_INFO("waiting trader connected, trader fronted is {}"
                , account_config_->getProperty("tradeFrontend"));
        QuotationAccessStateHolder::Global().setAccessStateInvalid("连接券商中");
        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }

    CTPTraderLoginReq trader_login_req;
    CTPTraderLoginResp trader_login_resp;
    trader_login_req.userName = account_config_->getUserName();
    trader_login_req.userPasswd = account_config_->getUserPasswd();
    trader_login_req.brokerId = account_config_->getProperty("brokerId");
    trader_login_req.appId = account_config_->getProperty("stsAppId");
    trader_login_req.authCode = account_config_->getProperty("stsAuthCode");

    while(true) {
        int result = trader_proxy_->login(trader_login_req, trader_login_resp);
        if (result != 0 || trader_login_resp.errorCode != 0) {
            attr_reporter_.inc(failed_call_key_, 1);
            std::this_thread::sleep_for(std::chrono::seconds(1));
            APPLOG_INFO("login failed, result={}, errorCode={}, retry login trader..."
                    , result, trader_login_resp.errorCode);

            if (trader_login_resp.errorCode == CTP_INVALID_LOGIN || trader_login_resp.errorCode == CTP_INVALID_INVESTORIDORPASSWORD) {
                QuotationAccessStateHolder::Global().setAccessStateInvalid("用户名密码错误");
                EffectiveReporter::Global().setAccountInfoInvalid(trader_login_resp.errorCode);
            } else if (trader_login_resp.errorCode == CTP_USER_NOT_FOUND){
                QuotationAccessStateHolder::Global().setAccessStateInvalid("登陆用户不存在");
                EffectiveReporter::Global().setAccountInfoInvalid(trader_login_resp.errorCode );
            } else if (trader_login_resp.errorCode == CTP_LOGIN_FORBIDDEN) {
                QuotationAccessStateHolder::Global().setAccessStateInvalid("登陆被禁用");
            } else if (trader_login_resp.errorCode == CTP_USER_NOT_ACTIVE) {
                QuotationAccessStateHolder::Global().setAccessStateInvalid("登陆用户不活跃");
            } else if (trader_login_resp.errorCode == CTP_INVALID_LOGIN_IPADDRESS) {
                QuotationAccessStateHolder::Global().setAccessStateInvalid("不合法的登陆地址");
            } else {
                QuotationAccessStateHolder::Global().setAccessStateInvalid("登陆异常, 错误码为"
                        + boost::lexical_cast<std::string>(trader_login_resp.errorCode));
            }

            trader_proxy_.reset();
            return ;
        } else {
            break;
        }
    }

    QueryAllContractsResp all_contract_resp;
    while(true) {
        int result = trader_proxy_->queryAllContracts(all_contract_resp);
        if (result != 0 || all_contract_resp.errorCode != 0) {
            attr_reporter_.inc(failed_call_key_, 1);
            std::this_thread::sleep_for(std::chrono::seconds(1));
            APPLOG_INFO("retry query contract...");

            QuotationAccessStateHolder::Global().setAccessStateInvalid("查询合约失败，重试中...");
        } else {
            md_proxy_->setAllInstruments(all_contract_resp.instruments);
            all_instruments_ = all_contract_resp.instruments;
            break;
        }
    }

    while(!md_proxy_->isConnected()) {
        APPLOG_INFO("waiting md connected, md frontend is {}", account_config_->getProperty("mdFrontend"));
        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }

    CTPMDLoginReq md_login_req;
    CTPMDLoginResp md_login_resp;
    md_login_req.userName = account_config_->getUserName();
    md_login_req.userPasswd =  account_config_->getUserPasswd();;
    md_login_req.brokerId = account_config_->getProperty("brokerId");;
    while(true) {
        int result = md_proxy_->login(md_login_req, md_login_resp);
        if (result != 0 || trader_login_resp.errorCode != 0) {
            attr_reporter_.inc(failed_call_key_, 1);
            std::this_thread::sleep_for(std::chrono::seconds(1));
            APPLOG_INFO("retry login md...");

            QuotationAccessStateHolder::Global().setAccessStateInvalid("登录行情服务器中...");
        } else {
            break;
        }
    }

    CTPMDSubscribeContractsReq md_subscribe_req;
    for (auto& instrument : all_contract_resp.instruments) {
        md_subscribe_req.contracts.push_back(instrument.InstrumentID);
    }

    while(true) {
        QuotationAccessStateHolder::Global().setAccessStateInvalid("订阅合约中...");

        CTPMDSubscribeContractsResp md_subscribe_resp;
        APPLOG_INFO("addSubscribe size={}, contracts={}", md_subscribe_req.contracts.size()
                , soldier::base::StringUtil::join(md_subscribe_req.contracts, ","));
        if (md_subscribe_req.contracts.empty()) {
            break;
        }

        int result = md_proxy_->subscribeContracts(md_subscribe_req, md_subscribe_resp);
        if (result != 0) {
            attr_reporter_.inc(failed_call_key_, 1);
            std::this_thread::sleep_for(std::chrono::seconds(1));
            APPLOG_INFO("retry subscribeContracts...");
        } else {
            APPLOG_INFO("subscribe success contracts size={}, contracts={}"
                    , md_subscribe_resp.successContracts.size()
                    , soldier::base::StringUtil::join(md_subscribe_resp.successContracts, ","));
            for (auto& contract : md_subscribe_resp.successContracts) {
                auto it = md_subscribe_req.contracts.begin();
                while(it != md_subscribe_req.contracts.end()) {
                    if ((*it) == contract) {
                        it = md_subscribe_req.contracts.erase(it);
                    } else {
                        ++it;
                    }
                }
            }

            for (int index = 0; index < (int)md_subscribe_resp.failedContracts.size(); ++index) {
                int errorCode = md_subscribe_resp.failedErrorCodes[index];
                // INSTRUMENT_NOT_FOUND
                if (errorCode == 16) {
                    APPLOG_INFO("remove not found instrument {}", md_subscribe_resp.failedContracts[index]);
                    auto it = md_subscribe_req.contracts.begin();
                    while(it != md_subscribe_req.contracts.end()) {
                        if ((*it) == md_subscribe_resp.failedContracts[index]) {
                            it = md_subscribe_req.contracts.erase(it);
                        } else {
                            ++it;
                        }
                    }
                }
            }
            APPLOG_INFO("after remove instrument, req contracts size={}"
                    , md_subscribe_req.contracts.size());

            std::this_thread::sleep_for(std::chrono::seconds(1));
        }
    }

    trader_proxy_.reset();

    is_onstart_successful_ = true;
    QuotationAccessStateHolder::Global().setAccessStateValid();
}

void CTPAccessProcessor::OnHeartBeat() {
    if (all_instruments_.empty()) {
        return ;
    }

    CTPMDSubscribeContractsReq md_subscribe_req;
    md_subscribe_req.contracts.push_back(all_instruments_[0].InstrumentID);
    CTPMDSubscribeContractsResp md_subscribe_resp;

    md_proxy_->subscribeContracts(md_subscribe_req, md_subscribe_resp);

}


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao


