#include "quotation_access_service_stub.h"
#include "QuotationAccessService.h"
#include "QuotationAccessService_variables.h"
using namespace soldier::svr_platform;

namespace xueqiao { namespace quotation { namespace access {
QuotationAccessServiceStub::QuotationAccessServiceStub()
  :TStubBase(QuotationAccessService_service_key) {
}
int64_t QuotationAccessServiceStub::getLastUpsideEffectiveTimestamp(
  const TPrepareSyncCallArgs& platformCallArgs
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccessServiceClient client(callResp.protocol);
  return client.getLastUpsideEffectiveTimestamp(callResp.platform_args);
}

void QuotationAccessServiceStub::sendUpsideHeartBeat(
  const TPrepareSyncCallArgs& platformCallArgs
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccessServiceClient client(callResp.protocol);
  client.sendUpsideHeartBeat(callResp.platform_args);
}

void QuotationAccessServiceStub::getAccessState(
  const TPrepareSyncCallArgs& platformCallArgs
  , QuotationAccessState& _return
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccessServiceClient client(callResp.protocol);
  client.getAccessState(_return, callResp.platform_args);
}


}}} // namespace
