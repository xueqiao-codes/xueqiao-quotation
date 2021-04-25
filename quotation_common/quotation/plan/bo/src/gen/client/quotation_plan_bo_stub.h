#ifndef QuotationPlanBo_STUB_H
#define QuotationPlanBo_STUB_H
#include "stub_base.h"
#include "quotation_plan_bo_types.h"
namespace xueqiao { namespace quotation { namespace plan { namespace bo {
class QuotationPlanBoStub : public ::soldier::svr_platform::TStubBase {
public:
  QuotationPlanBoStub();
  virtual ~QuotationPlanBoStub() = default;

  void startGenPreviewSCClasses(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , GenPreviewState& _return
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void getGenPreviewState(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , GenPreviewState& _return
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void commitPreviewSCClasses(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void querySubscribeContractItemPage(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , SubscribeContractItemPage& _return
    , const QuerySubscribeContractItemOption& queryOption, const  ::platform::page::IndexedPageOption& pageOption
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void getCurrentSCClasses(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , std::vector<SubscribeCommodityClass> & _return
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void getPreviewSCClasses(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , std::vector<SubscribeCommodityClass> & _return
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
};

}}}} // namespace
#endif
