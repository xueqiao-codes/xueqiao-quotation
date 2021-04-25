/*
 * ctp_trader_proxy.cpp
 *
 *  Created on: 2017年4月14日
 *      Author: wileywang
 */
#include "base/code_defense.h"
#include "ctp_trader_proxy.h"
#include "base/string_util.h"
#include "effective_reporter.h"

namespace xueqiao {
namespace quotation {
namespace access {

CTPTraderProxy::CTPTraderProxy(
        const std::string& front_address
        , const std::string flow_path)
    : front_address_(front_address)
      , flow_path_(flow_path) {
    trader_api_ = CThostFtdcTraderApi::CreateFtdcTraderApi(flow_path_.c_str());
    CHECK(trader_api_);

    proxy_thread_.reset(new std::thread(&CTPTraderProxy::onProxyWork, this));
}

CTPTraderProxy::~CTPTraderProxy() {
    if (trader_api_) {
        trader_api_->Release();
        trader_api_ = nullptr;
    }

    proxy_thread_->join();
}

void CTPTraderProxy::OnFrontConnected() {
    APPLOG_INFO("OnFrontConnected {}", front_address_);

    connected_ = true;
}

void CTPTraderProxy::OnFrontDisconnected(int nReason) {
    APPLOG_INFO("OnFrontDisconnected {}, nReason={}", front_address_, nReason);

    connected_ = false;
}

void CTPTraderProxy::OnHeartBeatWarning(int nTimeLapse) {
    APPLOG_INFO("OnHeartBeatWarning {}, nTimeLapse={}", front_address_, nTimeLapse);
}

int CTPTraderProxy::login(const CTPTraderLoginReq& req, CTPTraderLoginResp& resp) {
    /*
    int result = 0;
    int request_id = -1;

    resp.errorCode = 0;
    resp.errorMsg = "";

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    CThostFtdcReqUserLoginField login_field;
    strncpy(login_field.BrokerID, req.brokerId.c_str(), sizeof(TThostFtdcBrokerIDType) - 1);
    login_field.BrokerID[sizeof(TThostFtdcBrokerIDType) - 1] = 0;
    strncpy(login_field.UserID, req.userName.c_str(), sizeof(TThostFtdcUserIDType) - 1);
    login_field.UserID[sizeof(TThostFtdcUserIDType) - 1] = 0;
    strncpy(login_field.Password, req.userPasswd.c_str(), sizeof(TThostFtdcPasswordType) - 1);
    login_field.Password[sizeof(TThostFtdcPasswordType) - 1] = 0;

    result = trader_api_->ReqUserLogin(&login_field, request_id);
    if (result != 0) {
        removeSyncCall(request_id);
        return result;
    }
    sync_call->wait();
    removeSyncCall(request_id);
    return result;
    */

    //先认证，再登录
    int result = 0;
    int request_id = -1;

    resp.errorCode = 0;
    resp.errorMsg = "";

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    CThostFtdcReqAuthenticateField auth_field;
    strncpy(auth_field.BrokerID, req.brokerId.c_str(), sizeof(TThostFtdcBrokerIDType) - 1);
    auth_field.BrokerID[sizeof(TThostFtdcBrokerIDType) - 1] = 0;
    strncpy(auth_field.UserID, req.userName.c_str(), sizeof(TThostFtdcUserIDType) - 1);
    auth_field.UserID[sizeof(TThostFtdcUserIDType) - 1] = 0;
    strncpy(auth_field.AppID, req.appId.c_str(), sizeof(TThostFtdcAppIDType) - 1);
    auth_field.AppID[sizeof(TThostFtdcAppIDType) - 1] = 0;
    strncpy(auth_field.AuthCode, req.authCode.c_str(), sizeof(TThostFtdcAuthCodeType) - 1);
    auth_field.AuthCode[sizeof(TThostFtdcAuthCodeType) - 1] = 0;

    result = trader_api_->ReqAuthenticate(&auth_field, request_id);
    if (result != 0) {
        removeSyncCall(request_id);
        return result;
    }
    sync_call->wait();
    removeSyncCall(request_id);

    //认证失败，不用登录了，直接返回
    if(resp.errorCode != 0)
        return result;

    //认证成功，登录
    result = 0;
    int request_id_login = -1;

    resp.errorCode = 0;
    resp.errorMsg = "";

    std::shared_ptr<soldier::base::SyncCall> sync_call_login = acquireSyncCall(request_id_login);
    sync_call_login->push(&resp);

    CThostFtdcReqUserLoginField login_field;
    strncpy(login_field.BrokerID, req.brokerId.c_str(), sizeof(TThostFtdcBrokerIDType) - 1);
    login_field.BrokerID[sizeof(TThostFtdcBrokerIDType) - 1] = 0;
    strncpy(login_field.UserID, req.userName.c_str(), sizeof(TThostFtdcUserIDType) - 1);
    login_field.UserID[sizeof(TThostFtdcUserIDType) - 1] = 0;
    strncpy(login_field.Password, req.userPasswd.c_str(), sizeof(TThostFtdcPasswordType) - 1);
    login_field.Password[sizeof(TThostFtdcPasswordType) - 1] = 0;

    result = trader_api_->ReqUserLogin(&login_field, request_id_login);
    if (result != 0) {
        removeSyncCall(request_id_login);
        return result;
    }
    sync_call_login->wait();
    removeSyncCall(request_id_login);

    return result;
}

///客户端认证响应
void CTPTraderProxy::OnRspAuthenticate(CThostFtdcRspAuthenticateField *pRspAuthenticateField
            , CThostFtdcRspInfoField *pRspInfo
            , int nRequestID
            , bool bIsLast)
{
    CHECK(bIsLast);
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(nRequestID);
    if (!sync_call) {
        return ;
    }

    CTPTraderLoginResp* resp = sync_call->at<CTPTraderLoginResp>(0);
    if (pRspInfo) {
        if (pRspInfo->ErrorID == 0) {
            EffectiveReporter::Global().setEffective();
        }

        resp->errorCode = pRspInfo->ErrorID;
        resp->errorMsg = pRspInfo->ErrorMsg;
    } else {
        resp->errorCode = -1002;
        resp->errorMsg = "OnRspAuthenticate no rsp info";
    }
    sync_call->notify();
}

void CTPTraderProxy::OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin
                , CThostFtdcRspInfoField *pRspInfo
                , int nRequestID
                , bool bIsLast) {
    CHECK(bIsLast);
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(nRequestID);
    if (!sync_call) {
        return ;
    }

    CTPTraderLoginResp* resp = sync_call->at<CTPTraderLoginResp>(0);
    if (pRspInfo) {
        if (pRspInfo->ErrorID == 0) {
            EffectiveReporter::Global().setEffective();
        }

        resp->errorCode = pRspInfo->ErrorID;
        resp->errorMsg = pRspInfo->ErrorMsg;
    } else {
        resp->errorCode = -1001;
        resp->errorMsg = "OnRspUserLogin no rsp info";
    }
    sync_call->notify();
}

int CTPTraderProxy::queryAllContracts(QueryAllContractsResp& resp) {
    int result = 0;
    int request_id = -1;
    resp.errorCode = 0;
    resp.errorMsg = "";

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    CThostFtdcQryInstrumentField field;
    memset(&field, 0, sizeof(CThostFtdcQryInstrumentField));

    result = trader_api_->ReqQryInstrument(&field, request_id);
    if (result != 0) {
        removeSyncCall(request_id);
        return result;
    }
    sync_call->wait();
    removeSyncCall(request_id);
    return result;
}

void CTPTraderProxy::OnRspQryInstrument(CThostFtdcInstrumentField *pInstrument
        , CThostFtdcRspInfoField *pRspInfo
        , int nRequestID
        , bool bIsLast) {
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(nRequestID);
    if (!sync_call) {
        return ;
    }

    QueryAllContractsResp* resp = sync_call->at<QueryAllContractsResp>(0);
    if (pInstrument) {
        APPLOG_DEBUG("Get Instrument {}, isLast={}", pInstrument->InstrumentID, bIsLast);
        EffectiveReporter::Global().setEffective();
        resp->instruments.push_back(*pInstrument);
    }

    if (!pRspInfo) {
        if (bIsLast) {
            sync_call->notify();
        }
        return ;
    }

    EffectiveReporter::Global().setEffective();

    if (pRspInfo->ErrorID != 0) {
        APPLOG_ERROR("Partical Error! nRequestID={}, ErrorID={}", nRequestID
                , pRspInfo ? pRspInfo->ErrorID : -1000);
        resp->errorCode = -1000;
        resp->errorMsg = "partical error!";
    }

    if (bIsLast) {
        sync_call->notify();
    }
}


std::shared_ptr<soldier::base::SyncCall> CTPTraderProxy::acquireSyncCall(int& request_id){
    request_id = last_request_id_.fetch_add(1);
    if (last_request_id_ >= INT_MAX/2) {
        last_request_id_ = 0;
    }
    std::shared_ptr<soldier::base::SyncCall> sync_call(new soldier::base::SyncCall());

    lock_.lock();
    CHECK(running_call_.find(request_id) == running_call_.end());
    running_call_[request_id] = sync_call;
    lock_.unlock();
    return sync_call;
}

std::shared_ptr<soldier::base::SyncCall> CTPTraderProxy::findSyncCall(int request_id) {
    std::shared_ptr<soldier::base::SyncCall> sync_call;
    lock_.lock();
    auto it = running_call_.find(request_id);
    if (it != running_call_.end()) {
        sync_call = it->second;
    }
    lock_.unlock();
    return sync_call;
}

void CTPTraderProxy::removeSyncCall(int request_id) {
    lock_.lock();
    running_call_.erase(request_id);
    lock_.unlock();
}

void CTPTraderProxy::onProxyWork() {
    std::vector<std::string> fronts;
    soldier::base::StringUtil::tokenize(front_address_, fronts, ";", true);
    if (fronts.empty()) {
        APPLOG_FATAL("front_address_ {} empty!", front_address_);
    }

    for(auto& front : fronts) {
        trader_api_->RegisterFront((char*)front.c_str());
        APPLOG_INFO("trader add front {}", front);
    }
    trader_api_->RegisterSpi(this);

    APPLOG_INFO("trader api work flow_path={}, front_address={}", flow_path_, front_address_);

    trader_api_->Init();
    trader_api_->Join();

    APPLOG_INFO("trader api exited, flow_path={}, front_address={}", flow_path_, front_address_);
}


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



