#ifndef QuotationAccessService_HANDLER_H
#define QuotationAccessService_HANDLER_H
#include "QuotationAccessService.h"
namespace xueqiao { namespace quotation { namespace access {
class QuotationAccessServiceHandler : public QuotationAccessServiceIf {
public:
  QuotationAccessServiceHandler();
  virtual ~QuotationAccessServiceHandler();
  virtual int64_t getLastUpsideEffectiveTimestamp(const  ::platform::comm::PlatformArgs& platformArgs);
  virtual void sendUpsideHeartBeat(const  ::platform::comm::PlatformArgs& platformArgs);
  virtual void getAccessState(QuotationAccessState& _return, const  ::platform::comm::PlatformArgs& platformArgs);
};

}}} // namespace
#endif
