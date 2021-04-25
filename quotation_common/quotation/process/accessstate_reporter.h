/*
 * accessstate_reporter.h
 *
 *  Created on: 2018年12月23日
 *      Author: 44385
 */

#ifndef QUOTATION_PROCESS_ACCESSSTATE_REPORTER_H_
#define QUOTATION_PROCESS_ACCESSSTATE_REPORTER_H_

#include "base/thread_pool.h"
#include "subprocess_manager.h"
#include "quotation_access_service_types.h"

namespace xueqiao {
namespace quotation {
namespace process {

class AccessStateReporter : public ISubProcessListener {
public:
    AccessStateReporter(int64_t quotation_account_id);
    virtual ~AccessStateReporter() = default;

    virtual void onStartSubProcessBegin(const std::string& sub_process);
    virtual void onStartSubProcessFinished(const std::string& sub_process, int pid);

    virtual void onStopSubProcessBegin(const std::string& sub_process, int pid);
    virtual void onStopSubProcessFinished(const std::string& sub_process);

    virtual void onRestartSubProcessBegin(const std::string& sub_process);
    virtual void onRestartSubProcessFinished(const std::string& sub_process, int pid);

    virtual void onSubProcessExited(const std::string& sub_process);

private:
    void onTimer();
    void onUploadState(const std::shared_ptr<::xueqiao::quotation::access::QuotationAccessState>& state);

    int64_t quotation_account_id_;

    std::unique_ptr<std::thread> timer_thread_;

    std::unique_ptr<::xueqiao::quotation::access::QuotationAccessState> lastupload_success_state_;
    std::unique_ptr<soldier::base::TaskThread> upload_thread_;
};


} // end namespace process
} // end namespace quotation
} // end namespace xueqiao




#endif /* QUOTATION_PROCESS_ACCESSSTATE_REPORTER_H_ */
