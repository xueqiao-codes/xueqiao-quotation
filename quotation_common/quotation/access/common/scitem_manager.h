/*
 * scitem_manager.h
 *
 *  Created on: 2018年12月20日
 *      Author: 44385
 */

#ifndef QUOTATION_ACCESS_COMMON_SCITEM_MANAGER_H_
#define QUOTATION_ACCESS_COMMON_SCITEM_MANAGER_H_

#include <memory>
#include <thread>
#include <stdint.h>

#include "base/thread_pool.h"
#include "quotation_plan_bo_types.h"

namespace xueqiao {
namespace quotation {
namespace access {

class ISCItemListener {
public:
    virtual ~ISCItemListener() = default;

    /**
     *  首次条目初始化
     */
    virtual void onItemsInited(
            const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& items) noexcept = 0;

    /**
     *  条目变化
     */
    virtual void onItemsChanged(
            const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& removed_items
            , const std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& added_items) noexcept = 0;

protected:
    ISCItemListener() = default;
};

class SCItemManager {
public:
    SCItemManager(const std::shared_ptr<ISCItemListener>& listener
            , const int64_t& quotation_account_id);
    ~SCItemManager();

    void start();

private:
    void onStart();
    void onCheckOnce();

    std::string getKey(const xueqiao::quotation::plan::bo::SubscribeContractItem& subscribe_item);
    void getAllSCItems(std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem>& result_items);

    std::shared_ptr<ISCItemListener> listener_;
    int64_t quotation_account_id_;

    std::unique_ptr<soldier::base::TaskThread> work_thread_;
    std::map<std::string, xueqiao::quotation::plan::bo::SubscribeContractItem> current_items_;
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_SCITEM_MANAGER_H_ */
