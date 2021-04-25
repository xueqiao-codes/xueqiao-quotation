#ifndef QuotationAccessService_STUB_H
#define QuotationAccessService_STUB_H
#include "stub_base.h"
#include "quotation_access_service_types.h"
namespace xueqiao { namespace quotation { namespace access {
class QuotationAccessServiceStub : public ::soldier::svr_platform::TStubBase {
public:
  QuotationAccessServiceStub();
  virtual ~QuotationAccessServiceStub() = default;

  int64_t getLastUpsideEffectiveTimestamp(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void sendUpsideHeartBeat(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void getAccessState(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , QuotationAccessState& _return
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
};

}}} // namespace
#endif
