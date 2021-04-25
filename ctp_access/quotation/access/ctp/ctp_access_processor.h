/*
 * ctp_access_processor.h
 *
 *  Created on: 2017年4月23日
 *      Author: 44385
 */

#ifndef QUOTATION_ACCESS_CTP_CTP_ACCESS_PROCESSOR_H_
#define QUOTATION_ACCESS_CTP_CTP_ACCESS_PROCESSOR_H_

#include "base/thread_pool.h"
#include "attr/attr_reporter_factory.h"
#include "quotation/access/ctp/ctp_trader_proxy.h"
#include "quotation/access/ctp/ctp_md_proxy.h"
#include "account_config.h"
#include "accessservice_impl.h"

namespace xueqiao {
namespace quotation {
namespace access {

class CTPAccessProcessor : public IHeartBeatRunner {
public:
    CTPAccessProcessor(const std::shared_ptr<AccountConfig>& account_config
            , const std::shared_ptr<GroupChooser>& group_chooser
            , const std::string& flow_path_trader
            , const std::string& flow_path_md);

    void start();

    virtual void sendUpsideHeartBeat();

private:
    void OnStart();
    void OnHeartBeat();

    std::unique_ptr<CTPTraderProxy> trader_proxy_;
    std::unique_ptr<CTPMDProxy> md_proxy_;
    std::shared_ptr<AccountConfig> account_config_;

    std::atomic_bool is_onstart_successful_;
    std::vector<CThostFtdcInstrumentField> all_instruments_;

    soldier::attr::IAttrReporter& attr_reporter_;
    int64_t failed_call_key_ = -1;

    std::unique_ptr<soldier::base::TaskThread> work_thread_;
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao




#endif /* QUOTATION_ACCESS_CTP_CTP_ACCESS_PROCESSOR_H_ */
