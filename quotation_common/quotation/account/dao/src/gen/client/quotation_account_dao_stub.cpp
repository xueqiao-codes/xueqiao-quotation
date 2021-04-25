#include "quotation_account_dao_stub.h"
#include "QuotationAccountDao.h"
#include "QuotationAccountDao_variables.h"
using namespace soldier::svr_platform;


QuotationAccountDaoStub::QuotationAccountDaoStub()
  :TStubBase(QuotationAccountDao_service_key) {
}
void QuotationAccountDaoStub::reqContractActiveRule(
  const TPrepareSyncCallArgs& platformCallArgs
  ,  ::ContractActiveRulePage& _return
  , const  ::ReqContractActiveRuleOption& option, const  ::platform::page::IndexedPageOption& pageOption
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.reqContractActiveRule(_return, callResp.platform_args, option, pageOption);
}

void QuotationAccountDaoStub::reqContractRegisterRule(
  const TPrepareSyncCallArgs& platformCallArgs
  ,  ::ContractRegisterRulePage& _return
  , const  ::ReqContractRegisterRuleOption& option, const  ::platform::page::IndexedPageOption& pageOption
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.reqContractRegisterRule(_return, callResp.platform_args, option, pageOption);
}

void QuotationAccountDaoStub::reqQuotationAccount(
  const TPrepareSyncCallArgs& platformCallArgs
  ,  ::QuotationAccountPage& _return
  , const  ::ReqQuotationAccountOption& option, const  ::platform::page::IndexedPageOption& pageOption
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.reqQuotationAccount(_return, callResp.platform_args, option, pageOption);
}

void QuotationAccountDaoStub::addContractActiveRule(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::ContractActiveRule& rule
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.addContractActiveRule(callResp.platform_args, rule);
}

void QuotationAccountDaoStub::addContractRegisterRule(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::ContractRegisterRule& rule
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.addContractRegisterRule(callResp.platform_args, rule);
}

int64_t QuotationAccountDaoStub::addQuotationAccount(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::QuotationAccount& account
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  return client.addQuotationAccount(callResp.platform_args, account);
}

void QuotationAccountDaoStub::updateContractActiveRule(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::ContractActiveRule& rule
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.updateContractActiveRule(callResp.platform_args, rule);
}

void QuotationAccountDaoStub::updateContractRegisterRule(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::ContractRegisterRule& rule
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.updateContractRegisterRule(callResp.platform_args, rule);
}

void QuotationAccountDaoStub::updateQuotationAccount(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::QuotationAccount& account
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.updateQuotationAccount(callResp.platform_args, account);
}

void QuotationAccountDaoStub::removeContractActiveRule(
  const TPrepareSyncCallArgs& platformCallArgs
  , const int32_t sledCommodityId
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.removeContractActiveRule(callResp.platform_args, sledCommodityId);
}

void QuotationAccountDaoStub::removeContractRegisterRule(
  const TPrepareSyncCallArgs& platformCallArgs
  , const int32_t sledCommodityId, const  ::QuotationPlatformEnv::type platformEnv
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.removeContractRegisterRule(callResp.platform_args, sledCommodityId, platformEnv);
}

void QuotationAccountDaoStub::removeQuotationAccount(
  const TPrepareSyncCallArgs& platformCallArgs
  , const int64_t accountId
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.removeQuotationAccount(callResp.platform_args, accountId);
}

void QuotationAccountDaoStub::reqAccountCommodityRegisterAbility(
  const TPrepareSyncCallArgs& platformCallArgs
  ,  ::AccountCommodityRegisterAbilityPage& _return
  , const  ::ReqAccountCommodityRegisterAbilityOption& option, const  ::platform::page::IndexedPageOption& pageOption
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.reqAccountCommodityRegisterAbility(_return, callResp.platform_args, option, pageOption);
}

void QuotationAccountDaoStub::addAccountCommodityRegisterAbility(
  const TPrepareSyncCallArgs& platformCallArgs
  , const  ::AccountCommodityRegisterAbility& ability
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.addAccountCommodityRegisterAbility(callResp.platform_args, ability);
}

void QuotationAccountDaoStub::removeAccountCommodityRegisterAbility(
  const TPrepareSyncCallArgs& platformCallArgs
  , const int64_t supportAbilityId
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.removeAccountCommodityRegisterAbility(callResp.platform_args, supportAbilityId);
}

void QuotationAccountDaoStub::reqQuotationAccountSupport(
  const TPrepareSyncCallArgs& platformCallArgs
  ,  ::QuotationAccountSupportPage& _return
  , const  ::ReqQuotationAccountSupportOption& option, const  ::platform::page::IndexedPageOption& pageOption
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.reqQuotationAccountSupport(_return, callResp.platform_args, option, pageOption);
}

void QuotationAccountDaoStub::setCommodityRegisterOrderIndex(
  const TPrepareSyncCallArgs& platformCallArgs
  , const int32_t sledCommodityId, const  ::QuotationPlatformEnv::type platformEnv, const int32_t orderIndex
  ) throw(::platform::comm::ErrorInfo, ::apache::thrift::TException) {
  TPrepareSyncCallResp callResp;
  prepareSyncCall(platformCallArgs, callResp);
  QuotationAccountDaoClient client(callResp.protocol);
  client.setCommodityRegisterOrderIndex(callResp.platform_args, sledCommodityId, platformEnv, orderIndex);
}



