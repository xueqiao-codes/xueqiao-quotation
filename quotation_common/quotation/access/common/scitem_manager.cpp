/*
 * scitem_manager.cpp
 *
 *  Created on: 2018年12月20日
 *      Author: 44385
 */
#include "scitem_manager.h"

#include <chrono>
#include <sstream>
#include "base/code_defense.h"
#include "quotation_plan_bo_stub.h"

using namespace xueqiao::quotation::plan::bo;

namespace xueqiao {
namespace quotation {
namespace access {

SCItemManager::SCItemManager(const std::shared_ptr<ISCItemListener>& listener
        , const int64_t& quotation_account_id)
    : listener_(listener), quotation_account_id_(quotation_account_id) {
    CHECK(listener);
    CHECK(quotation_account_id_ > 0);
}


SCItemManager::~SCItemManager() {
}

void SCItemManager::start() {
    if (work_thread_) {
        return ;
    }

    work_thread_.reset(new soldier::base::TaskThread());
    work_thread_->postTask(&SCItemManager::onStart, this);
}

void SCItemManager::onStart() {
    getAllSCItems(current_items_);
    listener_->onItemsInited(current_items_);

    work_thread_->postTask(&SCItemManager::onCheckOnce, this);
}

void SCItemManager::onCheckOnce() {
    // 每隔15s检测一次
    std::this_thread::sleep_for(std::chrono::seconds(15));

    std::map<std::string, SubscribeContractItem> should_items;
    getAllSCItems(should_items);

    std::map<std::string, SubscribeContractItem> removed_items;
    std::map<std::string, SubscribeContractItem> added_items;

    for (const auto& should_it : should_items) {
        if (current_items_.end() == current_items_.find(should_it.first)) {
            added_items.insert(should_it);
        }
    }

    for (const auto& current_it : current_items_) {
        if (should_items.end() == should_items.find(current_it.first)) {
            removed_items.insert(current_it);
        }
    }

    if (removed_items.empty() && added_items.empty()) {
        work_thread_->postTask(&SCItemManager::onCheckOnce, this);
        return ;
    }

    listener_->onItemsChanged(removed_items, added_items);
    current_items_ = should_items;

    work_thread_->postTask(&SCItemManager::onCheckOnce, this);
}

std::string SCItemManager::getKey(const SubscribeContractItem& scitem) {
    std::stringstream ss;
    ss << scitem.sledExchangeMic
       << "|" << scitem.sledCommodityType
       << "|" << scitem.sledCommodityCode
       << "|" << scitem.sledContractCode;
    return ss.str();
}


void SCItemManager::getAllSCItems(std::map<std::string, SubscribeContractItem>& result_items) {

    ::soldier::svr_platform::TPrepareSyncCallArgs platformCallArgs;
    platformCallArgs.file_name = __FILE__;
    platformCallArgs.line = __LINE__;
    platformCallArgs.function_name = __FUNCTION__;

    // 一次性查出所有订阅项目，才能保证对比的正确性
    QuotationPlanBoStub stub;
    stub.setTimeoutMs(15000);

    QuerySubscribeContractItemOption qry_option;
    qry_option.quotationAccountIds.insert((int32_t)quotation_account_id_);
    qry_option.__isset.quotationAccountIds = true;

    ::platform::page::IndexedPageOption page_option;
    page_option.__set_pageIndex(0);
    page_option.__set_pageSize(INT_MAX);

    SubscribeContractItemPage result_page;
    while(true) {
        try {
            stub.querySubscribeContractItemPage(platformCallArgs, result_page, qry_option, page_option);
            break;
        } catch (::apache::thrift::TException& et) {
            APPLOG_ERROR("query subscribe contract failed, {}", et.what());
        }
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }

    result_items.clear();
    for (const auto& scitem : result_page.resultList) {
        result_items[getKey(scitem)] = scitem;
    }

    APPLOG_INFO("getAllSCItems finished! result_items.size()={}", result_items.size());
}

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao

