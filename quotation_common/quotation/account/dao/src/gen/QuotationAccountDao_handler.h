#ifndef QuotationAccountDao_HANDLER_H
#define QuotationAccountDao_HANDLER_H
#include "QuotationAccountDao.h"

class QuotationAccountDaoHandler : public QuotationAccountDaoIf {
public:
  QuotationAccountDaoHandler();
  virtual ~QuotationAccountDaoHandler();
  virtual void reqContractActiveRule( ::ContractActiveRulePage& _return, const  ::platform::comm::PlatformArgs& platformArgs, const  ::ReqContractActiveRuleOption& option, const  ::platform::page::IndexedPageOption& pageOption);
  virtual void reqContractRegisterRule( ::ContractRegisterRulePage& _return, const  ::platform::comm::PlatformArgs& platformArgs, const  ::ReqContractRegisterRuleOption& option, const  ::platform::page::IndexedPageOption& pageOption);
  virtual void reqQuotationAccount( ::QuotationAccountPage& _return, const  ::platform::comm::PlatformArgs& platformArgs, const  ::ReqQuotationAccountOption& option, const  ::platform::page::IndexedPageOption& pageOption);
  virtual void addContractActiveRule(const  ::platform::comm::PlatformArgs& platformArgs, const  ::ContractActiveRule& rule);
  virtual void addContractRegisterRule(const  ::platform::comm::PlatformArgs& platformArgs, const  ::ContractRegisterRule& rule);
  virtual int64_t addQuotationAccount(const  ::platform::comm::PlatformArgs& platformArgs, const  ::QuotationAccount& account);
  virtual void updateContractActiveRule(const  ::platform::comm::PlatformArgs& platformArgs, const  ::ContractActiveRule& rule);
  virtual void updateContractRegisterRule(const  ::platform::comm::PlatformArgs& platformArgs, const  ::ContractRegisterRule& rule);
  virtual void updateQuotationAccount(const  ::platform::comm::PlatformArgs& platformArgs, const  ::QuotationAccount& account);
  virtual void removeContractActiveRule(const  ::platform::comm::PlatformArgs& platformArgs, const int32_t sledCommodityId);
  virtual void removeContractRegisterRule(const  ::platform::comm::PlatformArgs& platformArgs, const int32_t sledCommodityId, const  ::QuotationPlatformEnv::type platformEnv);
  virtual void removeQuotationAccount(const  ::platform::comm::PlatformArgs& platformArgs, const int64_t accountId);
  virtual void reqAccountCommodityRegisterAbility( ::AccountCommodityRegisterAbilityPage& _return, const  ::platform::comm::PlatformArgs& platformArgs, const  ::ReqAccountCommodityRegisterAbilityOption& option, const  ::platform::page::IndexedPageOption& pageOption);
  virtual void addAccountCommodityRegisterAbility(const  ::platform::comm::PlatformArgs& platformArgs, const  ::AccountCommodityRegisterAbility& ability);
  virtual void removeAccountCommodityRegisterAbility(const  ::platform::comm::PlatformArgs& platformArgs, const int64_t supportAbilityId);
  virtual void reqQuotationAccountSupport( ::QuotationAccountSupportPage& _return, const  ::platform::comm::PlatformArgs& platformArgs, const  ::ReqQuotationAccountSupportOption& option, const  ::platform::page::IndexedPageOption& pageOption);
  virtual void setCommodityRegisterOrderIndex(const  ::platform::comm::PlatformArgs& platformArgs, const int32_t sledCommodityId, const  ::QuotationPlatformEnv::type platformEnv, const int32_t orderIndex);
};


#endif
