#include "quotation_plan_bo_stub.h"
#include "QuotationPlanBo.h"
#include "QuotationPlanBo_variables.h"
using namespace soldier::svr_platform;

namespace xueqiao { namespace quotation { namespace plan { namespace bo {
QuotationPlanBoStub::QuotationPlanBoStub()
  :TStubBase(QuotationPlanBo_service_key) {
}
void QuotationPlanBoStub::startGenPreviewSCClasses(
  const TPrepareSyncCallArgs& platformCallArgs
  , GenPreviewState& _return
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationPlanBoClient client(callResp.protocol);
  client.startGenPreviewSCClasses(_return, callResp.platform_args);
}

void QuotationPlanBoStub::getGenPreviewState(
  const TPrepareSyncCallArgs& platformCallArgs
  , GenPreviewState& _return
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationPlanBoClient client(callResp.protocol);
  client.getGenPreviewState(_return, callResp.platform_args);
}

void QuotationPlanBoStub::commitPreviewSCClasses(
  const TPrepareSyncCallArgs& platformCallArgs
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationPlanBoClient client(callResp.protocol);
  client.commitPreviewSCClasses(callResp.platform_args);
}

void QuotationPlanBoStub::querySubscribeContractItemPage(
  const TPrepareSyncCallArgs& platformCallArgs
  , SubscribeContractItemPage& _return
  , const QuerySubscribeContractItemOption& queryOption, const  ::platform::page::IndexedPageOption& pageOption
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationPlanBoClient client(callResp.protocol);
  client.querySubscribeContractItemPage(_return, callResp.platform_args, queryOption, pageOption);
}

void QuotationPlanBoStub::getCurrentSCClasses(
  const TPrepareSyncCallArgs& platformCallArgs
  , std::vector<SubscribeCommodityClass> & _return
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationPlanBoClient client(callResp.protocol);
  client.getCurrentSCClasses(_return, callResp.platform_args);
}

void QuotationPlanBoStub::getPreviewSCClasses(
  const TPrepareSyncCallArgs& platformCallArgs
  , std::vector<SubscribeCommodityClass> & _return
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationPlanBoClient client(callResp.protocol);
  client.getPreviewSCClasses(_return, callResp.platform_args);
}


}}}} // namespace
