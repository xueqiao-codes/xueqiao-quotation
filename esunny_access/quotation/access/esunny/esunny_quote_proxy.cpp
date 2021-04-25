/*
 * esunny_quote_proxy.cpp
 *
 *  Created on: 2017年8月28日
 *      Author: wileywang
 */
#include "quotation/access/esunny/esunny_quote_proxy.h"

#include <limits.h>
#include <boost/lexical_cast.hpp>
#include "base/code_defense.h"
#include "base/string_util.h"
#include "base/time_helper.h"

#include "TapAPIError.h"
#include "effective_reporter.h"

namespace xueqiao {
namespace quotation {
namespace access {

ESunnyQuoteProxy::ESunnyQuoteProxy(const std::string& logPath
        , const std::string& quoteAddr
        , const std::string& authCode
        , const std::shared_ptr<IESunnyQuoteHandler>& quote_handler)
    : quote_handler_(quote_handler) {
    TAPIINT32 result = 0;
    TapAPIApplicationInfo applicationInfo;
    strcpy(applicationInfo.AuthCode, authCode.c_str());
    strcpy(applicationInfo.KeyOperationLogPath, logPath.c_str());

    proxy_api_ = CreateTapQuoteAPI(&applicationInfo, result);
    CHECK(proxy_api_);

    proxy_api_->SetAPINotify(this);

    std::vector<std::string> addr_port;
    soldier::base::StringUtil::tokenize(quoteAddr, addr_port, ":", true);
    CHECK(addr_port.size() >= 2);

    uint16_t port = boost::lexical_cast<uint16_t>(addr_port[1]);
    proxy_api_->SetHostAddress(addr_port[0].c_str(), port);

    create_timestamp_ = soldier::base::NowInSeconds();
    APPLOG_INFO("ESunnyQuoteProxy Created, host addr={}, port={}", addr_port[0], port);
}

ESunnyQuoteProxy::~ESunnyQuoteProxy() {
    if (proxy_api_ != NULL) {
        FreeTapQuoteAPI(proxy_api_);
        proxy_api_ = NULL;
    }
}

int ESunnyQuoteProxy::loginReady(const ESunnyLoginReq& req
            , ESunnyLoginResp& resp) {
    int request_id = -1;

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    TapAPIQuoteLoginAuth loginAuth;
    strcpy(loginAuth.UserNo, req.userName.c_str());
    strcpy(loginAuth.Password, req.userPasswd.c_str());
    loginAuth.ISModifyPassword = APIYNFLAG_NO;
    loginAuth.ISDDA = APIYNFLAG_NO;

    last_login_request_id_ = request_id;
    TAPIINT32 ret = proxy_api_->Login(&loginAuth);
    if (ret != TAPIERROR_SUCCEED) {
        removeSyncCall(request_id);
        return ret;
    }

    CHECK(sync_call->waitFor(10000));
    removeSyncCall(request_id);
    return ret;
}

void ESunnyQuoteProxy::OnRspLogin(
        TAPIINT32 errorCode
        , const TapAPIQuotLoginRspInfo *info) {
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(last_login_request_id_);
    if (!sync_call) {
        APPLOG_FATAL("OnRspLogin , not found call for {}",  last_login_request_id_);
        return ;
    }

    ESunnyLoginResp* resp = sync_call->at<ESunnyLoginResp>(0);
    if (errorCode != 0) {
        resp->errorCode = errorCode;
        sync_call->notify();
    } else {
        EffectiveReporter::Global().setEffective();

        memcpy(&(resp->rspInfo), info, sizeof(TapAPIQuotLoginRspInfo));
    }
}

void ESunnyQuoteProxy::OnAPIReady() {
    APPLOG_INFO("OnAPIReady...");
    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(last_login_request_id_);
    if (!sync_call) {
        APPLOG_FATAL("OnAPIReady, not found call for {}", last_login_request_id_);
        return ;
    }

    sync_call->notify();
}

void ESunnyQuoteProxy::OnDisconnect(TAPIINT32 reasonCode) {
    if (EffectiveReporter::Global().isAccountInfoInvalid()) {
        APPLOG_WARN("OnDisconnect reasonCode={}, but account info is invalid", reasonCode);
        return ;
    }

    APPLOG_FATAL("OnDisconnect reasonCode={}, terminate to reconnect...", reasonCode);
}

int ESunnyQuoteProxy::queryAllCommodities(ESunnyQueryAllCommoditiesResp& resp) {
    int request_id = 0;

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    TAPIUINT32 sessionID = 0;
    TAPIINT32 ret = startCallSession(
            std::bind(&ITapQuoteAPI::QryCommodity, proxy_api_, std::placeholders::_1)
            , sessionID
            , request_id);
    if (ret != TAPIERROR_SUCCEED) {
        return ret;
    }

    CHECK(sync_call->waitFor(30000));
    removeSyncCall(request_id, sessionID);
    return ret;
}

void ESunnyQuoteProxy::OnRspQryCommodity(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIQuoteCommodityInfo *info) {
    int requestId= fromSessionID(sessionID);

    APPLOG_INFO("OnRspQryCommodity sessionID={}, requestId={}, errorCode={}, isLast={}"
            , sessionID, requestId, errorCode, isLast);

    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(requestId);
    if (!sync_call) {
        APPLOG_WARN("OnRspQryCommodity can not find call for sessionID={}", sessionID);
        return ;
    }

    ESunnyQueryAllCommoditiesResp* resp = sync_call->at<ESunnyQueryAllCommoditiesResp>(0);
    if (info != NULL) {
        resp->rspCommodities.push_back(*info);
    }

    if (isLast == APIYNFLAG_YES) {
        sync_call->notify();
    }
}

int ESunnyQuoteProxy::queryContracts(const ESunnyQueryContractReq& req, ESunnyQueryContractResp& resp) {
    int request_id = 0;

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    TAPIUINT32 sessionID = 0;
    TAPIINT32 ret = startCallSession(
            std::bind(&ITapQuoteAPI::QryContract, proxy_api_, std::placeholders::_1, req.commodity.get())
            , sessionID
            , request_id);
    if (ret != TAPIERROR_SUCCEED) {
        return ret;
    }

    CHECK(sync_call->waitFor(30000));
    removeSyncCall(request_id, sessionID);
    return ret;
}

void ESunnyQuoteProxy::OnRspQryContract(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIQuoteContractInfo *info) {
    int requestId= fromSessionID(sessionID);

    APPLOG_DEBUG("OnRspQryContract sessionID={}, requestId={}, errorCode={}, isLast={}"
            , sessionID, requestId, errorCode, isLast);

    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(requestId);
    if (!sync_call) {
        APPLOG_WARN("OnRspQryContract can not find call for sessionID={}", sessionID);
        return ;
    }

    ESunnyQueryContractResp* resp = sync_call->at<ESunnyQueryContractResp>(0);
    if (errorCode != 0) {
        resp->errorCode = errorCode;
    } else {
        if (info) {
            resp->rspContracts.push_back(*info);
            APPLOG_DEBUG("OnRspQryContract contract, ContractType={}, ContractName={}, ExchangeNo={}"
                ", CommodityType={}, CommodityNo={}, ContractNo={}, ContractNo2={}"
                , info->ContractType, info->ContractName, info->Contract.Commodity.ExchangeNo
                , info->Contract.Commodity.CommodityType, info->Contract.Commodity.CommodityNo
                , info->Contract.ContractNo1, info->Contract.ContractNo2);
        }
    }

    if (isLast == APIYNFLAG_YES) {
        sync_call->notify();
    }
}

int ESunnyQuoteProxy::subscribeQuote(const ESunnySubscribeQuoteReq& req, ESunnySubscribeQuoteResp& resp) {
    int request_id = 0;

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    TAPIUINT32 sessionID = 0;
    TAPIINT32 ret = startCallSession(
            std::bind(&ITapQuoteAPI::SubscribeQuote, proxy_api_, std::placeholders::_1, &req.contract)
            , sessionID
            , request_id);
    if (ret != TAPIERROR_SUCCEED) {
        return ret;
    }

    if (!sync_call->waitFor(5000)) {
        removeSyncCall(request_id, sessionID);
        return INT_MIN;
    }
    removeSyncCall(request_id, sessionID);
    return ret;
}

int ESunnyQuoteProxy::unsubscribeQuote(const ESunnyUnSubscribeQuoteReq& req, ESunnyUnSubscribeQuoteResp& resp) {
    int request_id = 0;

    std::shared_ptr<soldier::base::SyncCall> sync_call = acquireSyncCall(request_id);
    sync_call->push(&resp);

    TAPIUINT32 sessionID = 0;
    TAPIINT32 ret = startCallSession(
            std::bind(&ITapQuoteAPI::UnSubscribeQuote, proxy_api_, std::placeholders::_1, &req.contract)
            , sessionID
            , request_id);
    if (ret != TAPIERROR_SUCCEED) {
        return ret;
    }

    if (!sync_call->waitFor(5000)) {
        removeSyncCall(request_id, sessionID);
        return INT_MIN;
    }
    removeSyncCall(request_id, sessionID);
    return ret;
}

void ESunnyQuoteProxy::OnRspSubscribeQuote(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIQuoteWhole *info) {
    int requestId= fromSessionID(sessionID);

    APPLOG_INFO("OnRspSubscribeQuote sessionID={}, requestId={}, errorCode={}, isLast={}"
               , sessionID, requestId, errorCode, isLast);

    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(requestId);
    if (!sync_call) {
        APPLOG_WARN("OnRspSubscribeQuote can not find call for sessionID={}", sessionID);
        return ;
    }

    ESunnySubscribeQuoteResp* resp = sync_call->at<ESunnySubscribeQuoteResp>(0);
    if (errorCode != 0) {
        resp->errorCode = errorCode;
    } else {
        EffectiveReporter::Global().setEffective();
        if (info) {
            resp->rspQuoteWhole = *info;
        }
    }

    CHECK(isLast == APIYNFLAG_YES);
    sync_call->notify();
}

void ESunnyQuoteProxy::OnRspUnSubscribeQuote(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIContract *info) {
    int requestId= fromSessionID(sessionID);

    APPLOG_INFO("OnRspUnSubscribeQuote sessionID={}, requestId={}, errorCode={}, isLast={}"
            , sessionID, requestId, errorCode, isLast);

    std::shared_ptr<soldier::base::SyncCall> sync_call = findSyncCall(requestId);
    if (!sync_call) {
        APPLOG_WARN("OnRspUnSubscribeQuote can not find call for sessionID={}", sessionID);
        return ;
    }

    ESunnyUnSubscribeQuoteResp* resp = sync_call->at<ESunnyUnSubscribeQuoteResp>(0);
    if (errorCode != 0) {
        resp->errorCode = errorCode;
    } else {
        EffectiveReporter::Global().setEffective();
        if (info) {
            resp->rspContract = *info;
        }
    }

    CHECK(isLast == APIYNFLAG_YES);
    sync_call->notify();
}

void  ESunnyQuoteProxy::OnRtnQuote(const TapAPIQuoteWhole *info) {
    if (info == NULL) {
        return ;
    }

    EffectiveReporter::Global().setEffective();
    if (quote_handler_) {
        quote_handler_->onReceivedQuoteItem(*info);
    }

}

TAPIINT32 ESunnyQuoteProxy::startCallSession(
        std::function<TAPIINT32 (TAPIUINT32*)> call
        , TAPIUINT32& sessionID
        , int requestId) {
    std::unique_lock<std::mutex> auto_lock(lock_);
    TAPIINT32 ret = call(&sessionID);
    if (ret != TAPIERROR_SUCCEED) {
        return ret;
    }

    session_id_to_request_id_[sessionID] = requestId;
    return ret;
}


std::shared_ptr<soldier::base::SyncCall> ESunnyQuoteProxy::acquireSyncCall(int& request_id){
    request_id = last_request_id_.fetch_add(1);
    if (last_request_id_ >= INT_MAX/2) {
        last_request_id_ = 1;
    }
    std::shared_ptr<soldier::base::SyncCall> sync_call(new soldier::base::SyncCall());

    lock_.lock();
    CHECK(running_call_.find(request_id) == running_call_.end());
    running_call_[request_id] = sync_call;
    lock_.unlock();
    return sync_call;
}

std::shared_ptr<soldier::base::SyncCall> ESunnyQuoteProxy::findSyncCall(int request_id) {
    std::shared_ptr<soldier::base::SyncCall> sync_call;
    lock_.lock();
    auto it = running_call_.find(request_id);
    if (it != running_call_.end()) {
        sync_call = it->second;
    }
    lock_.unlock();
    return sync_call;
}


void ESunnyQuoteProxy::removeSyncCall(int request_id
        , TAPIUINT32 sessionID) {
    lock_.lock();
    running_call_.erase(request_id);
    if (sessionID != 0) {
        session_id_to_request_id_.erase(sessionID);
    }
    lock_.unlock();
}

int ESunnyQuoteProxy::fromSessionID(TAPIUINT32 sessionID) {
    int requestId = -1;
    lock_.lock();
    auto it = session_id_to_request_id_.find(sessionID);
    if (it != session_id_to_request_id_.end()) {
        requestId = it->second;
    }
    lock_.unlock();
    return requestId;
}

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao

