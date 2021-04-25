/*
 * ctp_md_proxy.h
 *
 *  Created on: 2017年4月14日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_CTP_CTP_MD_PROXY_H_
#define QUOTATION_ACCESS_CTP_CTP_MD_PROXY_H_

#include <atomic>
#include <memory>
#include <map>
#include <string>
#include <thread>
#include <vector>
#include "ThostFtdcUserApiDataType.h"
#include "ThostFtdcUserApiStruct.h"
#include "ThostFtdcMdApi.h"

#include "base/sync_call.h"
#include "attr/attr_reporter_factory.h"
#include "group_chooser.h"
#include "base_resp.h"

namespace xueqiao {
namespace quotation {
namespace access {

struct CTPMDLoginReq {
    std::string userName;
    std::string userPasswd;
    std::string brokerId;
};

struct CTPMDLoginResp : public BaseResp {
};

struct CTPMDSubscribeContractsReq {
    std::vector<std::string> contracts;
};

struct CTPMDSubscribeContractsResp {
    std::vector<std::string> failedContracts;
    std::vector<int> failedErrorCodes;
    std::vector<std::string> failedErrorMsgs;
    std::vector<std::string> successContracts;
};

struct CTPMDUnsubscribeContract {
    std::vector<std::string> contracts;
};

struct CTPMDUnsubscribeContractResp {
    std::vector<std::string>& failedContracts;
    std::vector<int> failedErrorCodes;
    std::vector<std::string> failedErrorMsgs;
};

class CTPMDProxy : public CThostFtdcMdSpi {
public:
    CTPMDProxy(const std::string& front_address
            , const std::string& flow_path
            , const std::shared_ptr<GroupChooser>& group_chooser
            , const std::string& platform);
    virtual ~CTPMDProxy();

    bool isConnected() { return connected_; }

    void setAllInstruments(const std::vector<CThostFtdcInstrumentField>& instruments);

    int login(const CTPMDLoginReq& req, CTPMDLoginResp& resp);
    int subscribeContracts(const CTPMDSubscribeContractsReq& req
            , CTPMDSubscribeContractsResp& resp);
    int unSubscribeContracts(const CTPMDUnsubscribeContract& contracts
            , CTPMDUnsubscribeContractResp& resp);

    virtual void OnFrontConnected() override;
    virtual void OnFrontDisconnected(int nReason) override;
    virtual void OnHeartBeatWarning(int nTimeLapse) override;

    virtual void OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin
            , CThostFtdcRspInfoField *pRspInfo
            , int nRequestID
            , bool bIsLast) override;
    virtual void OnRspSubMarketData(CThostFtdcSpecificInstrumentField *pSpecificInstrument
            , CThostFtdcRspInfoField *pRspInfo
            , int nRequestID
            , bool bIsLast) override;
    virtual void OnRspError(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

    virtual void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField *pDepthMarketData);

private:
    void onProxyWork();
    std::shared_ptr<soldier::base::SyncCall> acquireSyncCall(int& request_id);
    std::shared_ptr<soldier::base::SyncCall> findSyncCall(int request_id);
    void removeSyncCall(int request_id);

    const std::string front_address_;
    const std::string flow_path_;

    std::map<std::string, CThostFtdcInstrumentField> instrument_fields_;

    std::unique_ptr<std::thread> proxy_thread_;
    CThostFtdcMdApi* md_api_ = nullptr;

    std::mutex lock_;
    std::map<int, std::shared_ptr<soldier::base::SyncCall>> running_call_;
    std::atomic_int last_request_id_ = {1};

    std::atomic_bool connected_;

    std::shared_ptr<GroupChooser> group_chooser_;

    std::string platform_;  // 发送的行情所属行情平台

    soldier::attr::IAttrReporter& attr_reporter_;
    int64_t quotations_received_key_ = -1;
    int64_t quotations_dispatched_failed_key_ = -1;
    int16_t pid_ = -1;

    std::atomic_int last_login_request_id_ = {-1};
    std::atomic_int last_subscribe_request_id_ = {-1};
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao

#endif /* QUOTATION_ACCESS_CTP_CTP_MD_PROXY_H_ */
