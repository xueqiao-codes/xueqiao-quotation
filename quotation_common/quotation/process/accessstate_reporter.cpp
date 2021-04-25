/*
 * accessstate_reporter.cpp
 *
 *  Created on: 2018年12月23日
 *      Author: 44385
 */
#include "accessstate_reporter.h"

#include <chrono>
#include "accessservice_impl.h"
#include "base/app_log.h"
#include "quotation_account_dao_stub.h"

using namespace xueqiao::quotation::access;
using namespace xueqiao::quotation::process;

AccessStateReporter::AccessStateReporter(int64_t quotation_account_id)
    : quotation_account_id_(quotation_account_id) {
    timer_thread_.reset(new std::thread(&AccessStateReporter::onTimer, this));
    upload_thread_.reset(new soldier::base::TaskThread());
}

void AccessStateReporter::onStartSubProcessBegin(const std::string& sub_process) {
    std::shared_ptr<QuotationAccessState> state(new QuotationAccessState());
    state->__set_state(QuotationAccountAccessState::ACCOUNT_INVALID);
    state->__set_stateMsg("进程正在启动");
    upload_thread_->postTask(&AccessStateReporter::onUploadState, this, state);
}

void AccessStateReporter::onStartSubProcessFinished(const std::string& sub_process, int pid) {
}

void AccessStateReporter::onStopSubProcessBegin(const std::string& sub_process, int pid) {
}

void AccessStateReporter::onStopSubProcessFinished(const std::string& sub_process) {
    std::shared_ptr<QuotationAccessState> state(new QuotationAccessState());
    state->__set_state(QuotationAccountAccessState::ACCOUNT_INVALID);
    state->__set_stateMsg("进程停止运行");
    upload_thread_->postTask(&AccessStateReporter::onUploadState, this, state);
}

void AccessStateReporter::onRestartSubProcessBegin(const std::string& sub_process) {
}

void AccessStateReporter::onRestartSubProcessFinished(const std::string& sub_process, int pid) {
}

void AccessStateReporter::onSubProcessExited(const std::string& sub_process) {
    std::shared_ptr<QuotationAccessState> state(new QuotationAccessState());
    state->__set_state(QuotationAccountAccessState::ACCOUNT_INVALID);
    state->__set_stateMsg("进程停止运行");
    upload_thread_->postTask(&AccessStateReporter::onUploadState, this, state);
}


void AccessStateReporter::onTimer() {
    while(true) {
        std::this_thread::sleep_for(std::chrono::seconds(3));

        std::shared_ptr<QuotationAccessServiceStub> stub = QuotationAccessServiceImpl::getStub(quotation_account_id_);

        std::shared_ptr<QuotationAccessState> process_state(new QuotationAccessState());
        try {
            ::soldier::svr_platform::TPrepareSyncCallArgs platformCallArgs;
            platformCallArgs.file_name = __FILE__;
            platformCallArgs.line = __LINE__;
            platformCallArgs.function_name = __FUNCTION__;

            stub->getAccessState(platformCallArgs, *process_state);
        } catch (::apache::thrift::TException& e) {
            APPLOG_ERROR("getAccessState failed, {}", e.what());
            continue;
        }

        upload_thread_->postTask(&AccessStateReporter::onUploadState, this, process_state);
    }
}

void AccessStateReporter::onUploadState(const std::shared_ptr<QuotationAccessState>& state) {
    if (lastupload_success_state_ && (*lastupload_success_state_) == (*state)) {
        // 提交的状态和成功的状态一致
        return ;
    }

    QuotationAccount account;
    account.__set_accountId(quotation_account_id_);
    account.__set_accessState(state->state);
    account.__set_invalidReason(state->stateMsg);

    try {
        QuotationAccountDaoStub stub;
        ::soldier::svr_platform::TPrepareSyncCallArgs platformCallArgs;
        platformCallArgs.file_name = __FILE__;
        platformCallArgs.line = __LINE__;
        platformCallArgs.function_name = __FUNCTION__;

        APPLOG_INFO("updateQuotationAccount quotation_account_id={}, state={}, stateMsg={}"
                , quotation_account_id_, account.accessState, account.invalidReason);

        stub.updateQuotationAccount(platformCallArgs, account);

        lastupload_success_state_.reset(new QuotationAccessState(*state));
    }catch (::platform::comm::ErrorInfo& ei) {
        APPLOG_ERROR("updateQuotationAccount failed, quotation_account_id={}, errorCode={}, errorMsg={}"
                , quotation_account_id_, ei.errorCode, ei.errorMsg);
    } catch (::apache::thrift::TException& e) {
        APPLOG_ERROR("updateQuotationAccount failed, {}", e.what());
    }

}


