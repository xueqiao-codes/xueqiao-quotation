/*
 * accessservice_impl.h
 *
 *  Created on: 2018年12月23日
 *      Author: 44385
 */

#ifndef QUOTATION_ACCESS_COMMON_ACCESSSERVICE_IMPL_H_
#define QUOTATION_ACCESS_COMMON_ACCESSSERVICE_IMPL_H_

#include "QuotationAccessService.h"
#include "quotation_access_service_stub.h"

namespace xueqiao {
namespace quotation {
namespace access {

class IHeartBeatRunner {
public:
    virtual ~IHeartBeatRunner() = default;

    virtual void sendUpsideHeartBeat() = 0;
};

class QuotationAccessServiceHandler : public QuotationAccessServiceIf {
public:
    QuotationAccessServiceHandler(const std::shared_ptr<IHeartBeatRunner>& runner);

    virtual int64_t getLastUpsideEffectiveTimestamp(const  ::platform::comm::PlatformArgs& platformArgs);
    virtual void sendUpsideHeartBeat(const  ::platform::comm::PlatformArgs& platformArgs);
    virtual void getAccessState(QuotationAccessState& _return, const  ::platform::comm::PlatformArgs& platformArgs);

private:
    std::shared_ptr<IHeartBeatRunner> runner_;
};

class QuotationAccessServiceImpl {
public:
    static std::shared_ptr<QuotationAccessServiceStub> getStub(const int64_t& quotation_account_id);

    static void runLoop(
            const int64_t& quotation_account_id
            , const std::shared_ptr<IHeartBeatRunner>& runner);
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_ACCESSSERVICE_IMPL_H_ */
