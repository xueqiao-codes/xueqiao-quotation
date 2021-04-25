/*
 * ctp_trader_proxy.h
 *
 *  Created on: 2017年4月14日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_CTP_CTP_TRADER_PROXY_H_
#define QUOTATION_ACCESS_CTP_CTP_TRADER_PROXY_H_

#include <atomic>
#include <memory>
#include <map>
#include <string>
#include <thread>
#include "ThostFtdcUserApiDataType.h"
#include "ThostFtdcUserApiStruct.h"
#include "ThostFtdcTraderApi.h"

#include "base/sync_call.h"
#include "base_resp.h"
#include "group_chooser.h"

namespace xueqiao {
namespace quotation {
namespace access {

struct CTPTraderLoginReq {
    std::string userName;
    std::string userPasswd;
    std::string brokerId;
    std::string appId;
    std::string authCode;
};

struct CTPTraderLoginResp : public BaseResp {
};

struct QueryAllContractsResp : public BaseResp {
    std::vector<CThostFtdcInstrumentField> instruments;
};

class CTPTraderProxy : public CThostFtdcTraderSpi {
public:
    CTPTraderProxy(
            const std::string& front_address
            , const std::string flow_path);

    virtual ~CTPTraderProxy();

    int login(const CTPTraderLoginReq& req, CTPTraderLoginResp& resp);
    int queryAllContracts(QueryAllContractsResp& resp);

    bool isConnected() { return connected_; }

    virtual void OnFrontConnected() override;
    virtual void OnFrontDisconnected(int nReason) override;
    virtual void OnHeartBeatWarning(int nTimeLapse) override;

    ///客户端认证响应
    virtual void OnRspAuthenticate(CThostFtdcRspAuthenticateField *pRspAuthenticateField
                , CThostFtdcRspInfoField *pRspInfo
                , int nRequestID
                , bool bIsLast) override;

    virtual void OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin
                , CThostFtdcRspInfoField *pRspInfo
                , int nRequestID
                , bool bIsLast) override;
    virtual void OnRspQryInstrument(CThostFtdcInstrumentField *pInstrument
                , CThostFtdcRspInfoField *pRspInfo
                , int nRequestID
                , bool bIsLast) override;

private:
    void onProxyWork();
    std::shared_ptr<soldier::base::SyncCall> acquireSyncCall(int& request_id);
    std::shared_ptr<soldier::base::SyncCall> findSyncCall(int request_id);
    void removeSyncCall(int request_id);

    const std::string front_address_;
    const std::string flow_path_;

    std::unique_ptr<std::thread> proxy_thread_;

    CThostFtdcTraderApi* trader_api_ = nullptr;
    std::mutex lock_;
    std::map<int, std::shared_ptr<soldier::base::SyncCall>> running_call_;
    std::atomic_int last_request_id_ = {0};
    std::atomic_bool connected_ = {false};
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_CTP_CTP_TRADER_PROXY_H_ */
