package xueqiao.quotation.account.thriftapi.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import xueqiao.quotation.account.thriftapi.QuotationAccountDao;
import xueqiao.quotation.account.thriftapi.QuotationAccountDaoVariable;


public abstract class QuotationAccountDaoAdaptor implements QuotationAccountDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public xueqiao.quotation.account.thriftapi.ContractActiveRulePage reqContractActiveRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"reqContractActiveRule",platformArgs);
    return reqContractActiveRule(oCntl, option, pageOption);
  }

  protected abstract xueqiao.quotation.account.thriftapi.ContractActiveRulePage reqContractActiveRule(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.quotation.account.thriftapi.ContractRegisterRulePage reqContractRegisterRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"reqContractRegisterRule",platformArgs);
    return reqContractRegisterRule(oCntl, option, pageOption);
  }

  protected abstract xueqiao.quotation.account.thriftapi.ContractRegisterRulePage reqContractRegisterRule(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.quotation.account.thriftapi.QuotationAccountPage reqQuotationAccount(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"reqQuotationAccount",platformArgs);
    return reqQuotationAccount(oCntl, option, pageOption);
  }

  protected abstract xueqiao.quotation.account.thriftapi.QuotationAccountPage reqQuotationAccount(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addContractActiveRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"addContractActiveRule",platformArgs);
addContractActiveRule(oCntl, rule);
  }

  protected abstract void addContractActiveRule(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addContractRegisterRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"addContractRegisterRule",platformArgs);
addContractRegisterRule(oCntl, rule);
  }

  protected abstract void addContractRegisterRule(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public long addQuotationAccount(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"addQuotationAccount",platformArgs);
    return addQuotationAccount(oCntl, account);
  }

  protected abstract long addQuotationAccount(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateContractActiveRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"updateContractActiveRule",platformArgs);
updateContractActiveRule(oCntl, rule);
  }

  protected abstract void updateContractActiveRule(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateContractRegisterRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"updateContractRegisterRule",platformArgs);
updateContractRegisterRule(oCntl, rule);
  }

  protected abstract void updateContractRegisterRule(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void updateQuotationAccount(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"updateQuotationAccount",platformArgs);
updateQuotationAccount(oCntl, account);
  }

  protected abstract void updateQuotationAccount(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeContractActiveRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int sledCommodityId, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"removeContractActiveRule",platformArgs);
removeContractActiveRule(oCntl, sledCommodityId, activeType, fixedCode);
  }

  protected abstract void removeContractActiveRule(TServiceCntl oCntl, int sledCommodityId, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeContractRegisterRule(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"removeContractRegisterRule",platformArgs);
removeContractRegisterRule(oCntl, sledCommodityId, platformEnv, activeType, fixedCode);
  }

  protected abstract void removeContractRegisterRule(TServiceCntl oCntl, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeQuotationAccount(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long accountId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"removeQuotationAccount",platformArgs);
removeQuotationAccount(oCntl, accountId);
  }

  protected abstract void removeQuotationAccount(TServiceCntl oCntl, long accountId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage reqAccountCommodityRegisterAbility(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"reqAccountCommodityRegisterAbility",platformArgs);
    return reqAccountCommodityRegisterAbility(oCntl, option, pageOption);
  }

  protected abstract xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage reqAccountCommodityRegisterAbility(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void addAccountCommodityRegisterAbility(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"addAccountCommodityRegisterAbility",platformArgs);
addAccountCommodityRegisterAbility(oCntl, ability);
  }

  protected abstract void addAccountCommodityRegisterAbility(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeAccountCommodityRegisterAbility(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long supportAbilityId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"removeAccountCommodityRegisterAbility",platformArgs);
removeAccountCommodityRegisterAbility(oCntl, supportAbilityId);
  }

  protected abstract void removeAccountCommodityRegisterAbility(TServiceCntl oCntl, long supportAbilityId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void batAddAccountCommodityRegisterAbility(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"batAddAccountCommodityRegisterAbility",platformArgs);
batAddAccountCommodityRegisterAbility(oCntl, abilities);
  }

  protected abstract void batAddAccountCommodityRegisterAbility(TServiceCntl oCntl, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeAccountCommodityRegisterAbilities(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long accountId, int sledExchangeId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"removeAccountCommodityRegisterAbilities",platformArgs);
removeAccountCommodityRegisterAbilities(oCntl, accountId, sledExchangeId);
  }

  protected abstract void removeAccountCommodityRegisterAbilities(TServiceCntl oCntl, long accountId, int sledExchangeId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage reqQuotationAccountSupport(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"reqQuotationAccountSupport",platformArgs);
    return reqQuotationAccountSupport(oCntl, option, pageOption);
  }

  protected abstract xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage reqQuotationAccountSupport(TServiceCntl oCntl, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void setCommodityRegisterOrderIndex(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"setCommodityRegisterOrderIndex",platformArgs);
setCommodityRegisterOrderIndex(oCntl, sledCommodityId, platformEnv, orderIndex, activeType, fixedCode);
  }

  protected abstract void setCommodityRegisterOrderIndex(TServiceCntl oCntl, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void notifySubscribeQuoteStateChange(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"notifySubscribeQuoteStateChange",platformArgs);
notifySubscribeQuoteStateChange(oCntl);
  }

  protected abstract void notifySubscribeQuoteStateChange(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask reqLatestSQSTask(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"reqLatestSQSTask",platformArgs);
    return reqLatestSQSTask(oCntl);
  }

  protected abstract xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask reqLatestSQSTask(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void removeEarlySQSTask(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey,"removeEarlySQSTask",platformArgs);
removeEarlySQSTask(oCntl, taskId);
  }

  protected abstract void removeEarlySQSTask(TServiceCntl oCntl, long taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected QuotationAccountDaoAdaptor(){
    methodParameterNameMap.put("reqContractActiveRule",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("reqContractRegisterRule",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("reqQuotationAccount",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("addContractActiveRule",new String[]{"platformArgs", "rule"});
    methodParameterNameMap.put("addContractRegisterRule",new String[]{"platformArgs", "rule"});
    methodParameterNameMap.put("addQuotationAccount",new String[]{"platformArgs", "account"});
    methodParameterNameMap.put("updateContractActiveRule",new String[]{"platformArgs", "rule"});
    methodParameterNameMap.put("updateContractRegisterRule",new String[]{"platformArgs", "rule"});
    methodParameterNameMap.put("updateQuotationAccount",new String[]{"platformArgs", "account"});
    methodParameterNameMap.put("removeContractActiveRule",new String[]{"platformArgs", "sledCommodityId", "activeType", "fixedCode"});
    methodParameterNameMap.put("removeContractRegisterRule",new String[]{"platformArgs", "sledCommodityId", "platformEnv", "activeType", "fixedCode"});
    methodParameterNameMap.put("removeQuotationAccount",new String[]{"platformArgs", "accountId"});
    methodParameterNameMap.put("reqAccountCommodityRegisterAbility",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("addAccountCommodityRegisterAbility",new String[]{"platformArgs", "ability"});
    methodParameterNameMap.put("removeAccountCommodityRegisterAbility",new String[]{"platformArgs", "supportAbilityId"});
    methodParameterNameMap.put("batAddAccountCommodityRegisterAbility",new String[]{"platformArgs", "abilities"});
    methodParameterNameMap.put("removeAccountCommodityRegisterAbilities",new String[]{"platformArgs", "accountId", "sledExchangeId"});
    methodParameterNameMap.put("reqQuotationAccountSupport",new String[]{"platformArgs", "option", "pageOption"});
    methodParameterNameMap.put("setCommodityRegisterOrderIndex",new String[]{"platformArgs", "sledCommodityId", "platformEnv", "orderIndex", "activeType", "fixedCode"});
    methodParameterNameMap.put("notifySubscribeQuoteStateChange",new String[]{"platformArgs"});
    methodParameterNameMap.put("reqLatestSQSTask",new String[]{"platformArgs"});
    methodParameterNameMap.put("removeEarlySQSTask",new String[]{"platformArgs", "taskId"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
