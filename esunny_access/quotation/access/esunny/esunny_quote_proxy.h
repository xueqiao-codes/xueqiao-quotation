/*
 * esunny_quote_proxy.h
 *
 *  Created on: 2017年8月28日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_PROXY_H_
#define QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_PROXY_H_

#include <atomic>
#include <functional>
#include <map>
#include <memory>
#include <mutex>

#include "base/sync_call.h"
#include "quotation/access/common/base_resp.h"
#include "quotation/access/esunny/esunny_quote_handler.h"
#include "TapQuoteAPI.h"

namespace xueqiao {
namespace quotation {
namespace access {

struct ESunnyLoginReq {
    std::string userName;
    std::string userPasswd;
};

struct ESunnyLoginResp : public BaseResp {
    TapAPIQuotLoginRspInfo rspInfo;
};

struct ESunnyQueryAllCommoditiesResp : public BaseResp {
    std::vector<TapAPIQuoteCommodityInfo> rspCommodities;
};

struct ESunnyQueryContractReq {
    std::unique_ptr<TapAPICommodity> commodity;
};

struct ESunnyQueryContractResp : public BaseResp {
    std::vector<TapAPIQuoteContractInfo> rspContracts;
};

struct ESunnySubscribeQuoteReq {
    TapAPIContract contract;
};

struct ESunnySubscribeQuoteResp : public BaseResp {
    TapAPIQuoteWhole rspQuoteWhole;
};

struct ESunnyUnSubscribeQuoteReq {
    TapAPIContract contract;
};

struct ESunnyUnSubscribeQuoteResp : public BaseResp {
    TapAPIContract rspContract;
};

class ESunnyQuoteProxy : public ITapQuoteAPINotify {
public:
    ESunnyQuoteProxy(
            const std::string& logPath
            , const std::string& quoteAddr
            , const std::string& authCode
            , const std::shared_ptr<IESunnyQuoteHandler>& quote_handler);
    virtual ~ESunnyQuoteProxy();

    int loginReady(const ESunnyLoginReq& req
            , ESunnyLoginResp& resp);

    int queryAllCommodities(ESunnyQueryAllCommoditiesResp& resp);
    int queryContracts(const ESunnyQueryContractReq& req, ESunnyQueryContractResp& resp);
    int subscribeQuote(const ESunnySubscribeQuoteReq& req, ESunnySubscribeQuoteResp& resp);
    int unsubscribeQuote(const ESunnyUnSubscribeQuoteReq& req, ESunnyUnSubscribeQuoteResp& resp);

    virtual void TAP_CDECL OnRspLogin(TAPIINT32 errorCode, const TapAPIQuotLoginRspInfo *info);
    virtual void TAP_CDECL OnAPIReady();

    virtual void TAP_CDECL OnDisconnect(TAPIINT32 reasonCode);

    virtual void TAP_CDECL OnRspQryCommodity(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIQuoteCommodityInfo *info);

    virtual void TAP_CDECL OnRspQryContract(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIQuoteContractInfo *info);

    virtual void TAP_CDECL OnRspSubscribeQuote(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIQuoteWhole *info);

    virtual void TAP_CDECL OnRspUnSubscribeQuote(TAPIUINT32 sessionID
            , TAPIINT32 errorCode
            , TAPIYNFLAG isLast
            , const TapAPIContract *info);

    virtual void TAP_CDECL OnRtnQuote(const TapAPIQuoteWhole *info);

private:
    TAPIINT32 startCallSession(
            std::function<TAPIINT32 (TAPIUINT32*)> call
            , TAPIUINT32& sessionID
            , int requestId);
    std::shared_ptr<soldier::base::SyncCall> acquireSyncCall(int& request_id);
    std::shared_ptr<soldier::base::SyncCall> findSyncCall(int request_id);
    void removeSyncCall(int request_id, TAPIUINT32 sessionID = 0);
    int fromSessionID(TAPIUINT32 sessionID);

    ITapQuoteAPI* proxy_api_;
    std::mutex lock_;
    std::map<int, std::shared_ptr<soldier::base::SyncCall>> running_call_;
    std::map<TAPIUINT32, int> session_id_to_request_id_;
    std::atomic_int last_request_id_ = {1};
    std::atomic_int last_login_request_id_ = {-1};

    std::shared_ptr<IESunnyQuoteHandler> quote_handler_;

    int64_t create_timestamp_ = 0;
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_PROXY_H_ */
