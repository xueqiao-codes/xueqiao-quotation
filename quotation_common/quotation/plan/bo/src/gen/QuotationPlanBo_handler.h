#ifndef QuotationPlanBo_HANDLER_H
#define QuotationPlanBo_HANDLER_H
#include "QuotationPlanBo.h"
namespace xueqiao { namespace quotation { namespace plan { namespace bo {
class QuotationPlanBoHandler : public QuotationPlanBoIf {
public:
  QuotationPlanBoHandler();
  virtual ~QuotationPlanBoHandler();
  virtual void startGenPreviewSCClasses(GenPreviewState& _return, const  ::platform::comm::PlatformArgs& platformArgs);
  virtual void getGenPreviewState(GenPreviewState& _return, const  ::platform::comm::PlatformArgs& platformArgs);
  virtual void commitPreviewSCClasses(const  ::platform::comm::PlatformArgs& platformArgs);
  virtual void querySubscribeContractItemPage(SubscribeContractItemPage& _return, const  ::platform::comm::PlatformArgs& platformArgs, const QuerySubscribeContractItemOption& queryOption, const  ::platform::page::IndexedPageOption& pageOption);
  virtual void getCurrentSCClasses(std::vector<SubscribeCommodityClass> & _return, const  ::platform::comm::PlatformArgs& platformArgs);
  virtual void getPreviewSCClasses(std::vector<SubscribeCommodityClass> & _return, const  ::platform::comm::PlatformArgs& platformArgs);
};

}}}} // namespace
#endif
