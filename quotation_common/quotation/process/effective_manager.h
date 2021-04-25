/*
 * upside_effective_manager.h
 *
 *  Created on: 2018年2月26日
 *      Author: wileywang
 */

#ifndef QUOTATION_PROCESS_EFFECTIVE_MANAGER_H_
#define QUOTATION_PROCESS_EFFECTIVE_MANAGER_H_

#include <atomic>
#include <memory>
#include <mutex>
#include <set>
#include <thread>

namespace xueqiao {
namespace quotation {
namespace process {

class IRestartWorker {
public:
    virtual ~IRestartWorker() = default;

    virtual void onRestartProcess(int64_t quotation_account_id) = 0;

protected:
    IRestartWorker() = default;
};


class EffectiveManager {
public:
    static EffectiveManager& Global();
    ~EffectiveManager();

    void clearAll();
    void addQuotationAccount(int64_t quotation_account_id);
    void removeQuotationAccount(int64_t quotation_account_id);

    void setRestartWorker(const std::shared_ptr<IRestartWorker>& restart_worker) {
        restart_worker_ = restart_worker;
    }

private:
    void onWorking();

    EffectiveManager();

    std::atomic_bool stopped_ = {false};

    std::mutex lock_;
    std::set<int64_t> quotation_accounts_;

    std::unique_ptr<std::thread> work_thread_;

    std::shared_ptr<IRestartWorker> restart_worker_;
};



} // end namespace process
} // end namespace quotation
} // end namespace xueqiao

#endif /* QUOTATION_PROCESS_EFFECTIVE_MANAGER_H_ */
