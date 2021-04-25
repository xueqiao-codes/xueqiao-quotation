#ifndef QuotationAccountDao_STUB_H
#define QuotationAccountDao_STUB_H
#include "stub_base.h"
#include "quotation_account_dao_types.h"

class QuotationAccountDaoStub : public ::soldier::svr_platform::TStubBase {
public:
  QuotationAccountDaoStub();
  virtual ~QuotationAccountDaoStub() = default;

  void reqContractActiveRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ,  ::ContractActiveRulePage& _return
    , const  ::ReqContractActiveRuleOption& option, const  ::platform::page::IndexedPageOption& pageOption
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void reqContractRegisterRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ,  ::ContractRegisterRulePage& _return
    , const  ::ReqContractRegisterRuleOption& option, const  ::platform::page::IndexedPageOption& pageOption
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void reqQuotationAccount(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ,  ::QuotationAccountPage& _return
    , const  ::ReqQuotationAccountOption& option, const  ::platform::page::IndexedPageOption& pageOption
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void addContractActiveRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::ContractActiveRule& rule
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void addContractRegisterRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::ContractRegisterRule& rule
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  int64_t addQuotationAccount(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::QuotationAccount& account
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void updateContractActiveRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::ContractActiveRule& rule
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void updateContractRegisterRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::ContractRegisterRule& rule
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void updateQuotationAccount(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::QuotationAccount& account
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void removeContractActiveRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const int32_t sledCommodityId
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void removeContractRegisterRule(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const int32_t sledCommodityId, const  ::QuotationPlatformEnv::type platformEnv
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void removeQuotationAccount(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const int64_t accountId
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void reqAccountCommodityRegisterAbility(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ,  ::AccountCommodityRegisterAbilityPage& _return
    , const  ::ReqAccountCommodityRegisterAbilityOption& option, const  ::platform::page::IndexedPageOption& pageOption
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void addAccountCommodityRegisterAbility(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const  ::AccountCommodityRegisterAbility& ability
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void removeAccountCommodityRegisterAbility(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const int64_t supportAbilityId
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void reqQuotationAccountSupport(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    ,  ::QuotationAccountSupportPage& _return
    , const  ::ReqQuotationAccountSupportOption& option, const  ::platform::page::IndexedPageOption& pageOption
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
  void setCommodityRegisterOrderIndex(
    const ::soldier::svr_platform::TPrepareSyncCallArgs& platformCallArgs
    , const int32_t sledCommodityId, const  ::QuotationPlatformEnv::type platformEnv, const int32_t orderIndex
    ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException);
};


#endif
