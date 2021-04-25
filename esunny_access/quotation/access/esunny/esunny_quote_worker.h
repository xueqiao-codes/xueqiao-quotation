/*
 * esunny_quote_worker.h
 *
 *  Created on: 2017年8月31日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_WORKER_H_
#define QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_WORKER_H_

#include "base/thread_pool.h"

#include "scitem_manager.h"
#include "quotation/access/esunny/esunny_quote_proxy.h"
#include "quotation/access/esunny/esunny_quote_handler.h"
#include "accessservice_impl.h"

namespace xueqiao {
namespace quotation {
namespace access {

class IESunnySCItemOpCallback {
public:
    IESunnySCItemOpCallback() = default;
    virtual ~IESunnySCItemOpCallback() = default;

    virtual void onSubscribeContractInited(const std::vector<TapAPIContract>& subscribe_contracts) = 0;
    virtual void onSubscribeContractChanged(const std::vector<TapAPIContract>& removed_contracts
                , const std::vector<TapAPIContract>& added_contracts) = 0;

};

class ESunnySCItemListener : public ISCItemListener {
public:
    ESunnySCItemListener(IESunnySCItemOpCallback* op_callback);
    virtual ~ESunnySCItemListener() = default;

    virtual void onItemsInited(
           const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& items) noexcept;

    virtual void onItemsChanged(
            const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& removed_items
            , const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& added_items) noexcept;

private:
    void scitems2Contracts(const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& items
            , std::vector<TapAPIContract>& contracts);

    bool convertContract(const xueqiao::quotation::plan::bo::SubscribeContractItem& scitem
            , TapAPIContract& contract);

    IESunnySCItemOpCallback* op_callback_;
};

class ESunnyQuoteWorker : public IESunnySCItemOpCallback, public IHeartBeatRunner{
public:
    ESunnyQuoteWorker(
            const int64_t& quotation_account_id
            , const std::string& base_logdir
            , const std::string& quote_addr
            , const std::string& user_name
            , const std::string& user_password
            , const std::string& auth_code
            , const std::shared_ptr<IESunnyQuoteHandler>& quote_handler);
    ~ESunnyQuoteWorker();

    void start();

    virtual void onSubscribeContractInited(const std::vector<TapAPIContract>& subscribe_contracts);
    virtual void onSubscribeContractChanged(const std::vector<TapAPIContract>& removed_contracts
            , const std::vector<TapAPIContract>& added_contracts);

    virtual void sendUpsideHeartBeat();

private:
    void onStart();
    void onHandleSubscribe(const std::vector<TapAPIContract>& removed_contracts
            , const std::vector<TapAPIContract>& added_contracts);
    void onHandleHeartBeat();

    /**
     *  返回true， 表示合约能够订阅和处理
     */
    enum FillContractResult {
        CONTRACT_EXIST = 0,     // 合约存在
        CONTRACT_NOT_SURE = 1,  // 不确定合约的存在性
        CONTRACT_NOT_EXIST = 2, // 合约不存在
        CONTRACT_SELF_FILL = 3, // 自己填充合约信息
    };

    FillContractResult fillContract(TapAPIContract& input);

    void subscribeContract(const TapAPIContract& contract);
    void unsubscribeContract(const TapAPIContract& contract);

    std::string getKey(const TapAPIContract& contract);
    void addSubscribeRetry(const TapAPIContract& contract);
    void removeSubscribeRetry(const TapAPIContract& contract);
    bool containsSubscribeRetry(const TapAPIContract& contract);
    void onHandleRetry();

    std::unique_ptr<soldier::base::TaskThread> worker_thread_;

    int64_t quotation_account_id_;
    std::string base_logdir_;
    std::string quote_addr_;
    std::string user_name_;
    std::string user_password_;
    std::string auth_code_;

    std::unique_ptr<ESunnyQuoteProxy> api_proxy_;

    std::atomic_bool is_start_successful_;

    std::map<std::string, TapAPIContract> full_contract_cache_;  // 带有完全合约信息的缓存

    std::map<std::string, TapAPIContract> subscribed_contracts_;  // 已经成功订阅的合约

    std::map<std::string, TapAPIContract> retry_subscribe_contracts_; // 尝试重新订阅的合约

    std::shared_ptr<ESunnySCItemListener> scmanager_listener_;
    std::shared_ptr<SCItemManager> scmanager_;
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao


#endif /* QUOTATION_ACCESS_ESUNNY_ESUNNY_QUOTE_WORKER_H_ */
