package xueqiao.quotation.account.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import java.util.List;
import xueqiao.quotation.account.thriftapi.QuotationAccountDao;
import xueqiao.quotation.account.thriftapi.QuotationAccountDaoVariable;

public class QuotationAccountDaoStub extends BaseStub {

  public QuotationAccountDaoStub() {
    super(QuotationAccountDaoVariable.serviceKey);
  }

  public xueqiao.quotation.account.thriftapi.ContractActiveRulePage  reqContractActiveRule(xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqContractActiveRule(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.ContractActiveRulePage  reqContractActiveRule(xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqContractActiveRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<xueqiao.quotation.account.thriftapi.ContractActiveRulePage>(){
    @Override
    public xueqiao.quotation.account.thriftapi.ContractActiveRulePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).reqContractActiveRule(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public xueqiao.quotation.account.thriftapi.ContractActiveRulePage  reqContractActiveRule(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqContractActiveRule(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.ContractRegisterRulePage  reqContractRegisterRule(xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqContractRegisterRule(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.ContractRegisterRulePage  reqContractRegisterRule(xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqContractRegisterRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<xueqiao.quotation.account.thriftapi.ContractRegisterRulePage>(){
    @Override
    public xueqiao.quotation.account.thriftapi.ContractRegisterRulePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).reqContractRegisterRule(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public xueqiao.quotation.account.thriftapi.ContractRegisterRulePage  reqContractRegisterRule(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqContractRegisterRule(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.QuotationAccountPage  reqQuotationAccount(xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqQuotationAccount(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.QuotationAccountPage  reqQuotationAccount(xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqQuotationAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<xueqiao.quotation.account.thriftapi.QuotationAccountPage>(){
    @Override
    public xueqiao.quotation.account.thriftapi.QuotationAccountPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).reqQuotationAccount(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public xueqiao.quotation.account.thriftapi.QuotationAccountPage  reqQuotationAccount(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqQuotationAccount(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addContractActiveRule(xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addContractActiveRule(rule, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addContractActiveRule(xueqiao.quotation.account.thriftapi.ContractActiveRule rule,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addContractActiveRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).addContractActiveRule(platformArgs, rule);
      return null;
      }
    }, invokeInfo);
  }

  public void  addContractActiveRule(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ContractActiveRule rule)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addContractActiveRule(rule, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addContractRegisterRule(xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addContractRegisterRule(rule, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addContractRegisterRule(xueqiao.quotation.account.thriftapi.ContractRegisterRule rule,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addContractRegisterRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).addContractRegisterRule(platformArgs, rule);
      return null;
      }
    }, invokeInfo);
  }

  public void  addContractRegisterRule(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ContractRegisterRule rule)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addContractRegisterRule(rule, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public long  addQuotationAccount(xueqiao.quotation.account.thriftapi.QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addQuotationAccount(account, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public long  addQuotationAccount(xueqiao.quotation.account.thriftapi.QuotationAccount account,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addQuotationAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Long>(){
    @Override
    public Long call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).addQuotationAccount(platformArgs, account);
      }
    }, invokeInfo);
  }

  public long  addQuotationAccount(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.QuotationAccount account)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addQuotationAccount(account, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateContractActiveRule(xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateContractActiveRule(rule, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateContractActiveRule(xueqiao.quotation.account.thriftapi.ContractActiveRule rule,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateContractActiveRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).updateContractActiveRule(platformArgs, rule);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateContractActiveRule(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ContractActiveRule rule)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateContractActiveRule(rule, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateContractRegisterRule(xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateContractRegisterRule(rule, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateContractRegisterRule(xueqiao.quotation.account.thriftapi.ContractRegisterRule rule,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateContractRegisterRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).updateContractRegisterRule(platformArgs, rule);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateContractRegisterRule(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ContractRegisterRule rule)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateContractRegisterRule(rule, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateQuotationAccount(xueqiao.quotation.account.thriftapi.QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateQuotationAccount(account, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateQuotationAccount(xueqiao.quotation.account.thriftapi.QuotationAccount account,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateQuotationAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).updateQuotationAccount(platformArgs, account);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateQuotationAccount(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.QuotationAccount account)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateQuotationAccount(account, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeContractActiveRule(int sledCommodityId, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeContractActiveRule(sledCommodityId, activeType, fixedCode, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeContractActiveRule(int sledCommodityId, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeContractActiveRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).removeContractActiveRule(platformArgs, sledCommodityId, activeType, fixedCode);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeContractActiveRule(int routeKey, int timeout,int sledCommodityId, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeContractActiveRule(sledCommodityId, activeType, fixedCode, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeContractRegisterRule(int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeContractRegisterRule(sledCommodityId, platformEnv, activeType, fixedCode, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeContractRegisterRule(int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeContractRegisterRule").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).removeContractRegisterRule(platformArgs, sledCommodityId, platformEnv, activeType, fixedCode);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeContractRegisterRule(int routeKey, int timeout,int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeContractRegisterRule(sledCommodityId, platformEnv, activeType, fixedCode, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeQuotationAccount(long accountId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeQuotationAccount(accountId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeQuotationAccount(long accountId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeQuotationAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).removeQuotationAccount(platformArgs, accountId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeQuotationAccount(int routeKey, int timeout,long accountId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeQuotationAccount(accountId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage  reqAccountCommodityRegisterAbility(xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqAccountCommodityRegisterAbility(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage  reqAccountCommodityRegisterAbility(xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqAccountCommodityRegisterAbility").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage>(){
    @Override
    public xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).reqAccountCommodityRegisterAbility(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbilityPage  reqAccountCommodityRegisterAbility(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqAccountCommodityRegisterAbility(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addAccountCommodityRegisterAbility(xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addAccountCommodityRegisterAbility(ability, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addAccountCommodityRegisterAbility(xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addAccountCommodityRegisterAbility").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).addAccountCommodityRegisterAbility(platformArgs, ability);
      return null;
      }
    }, invokeInfo);
  }

  public void  addAccountCommodityRegisterAbility(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addAccountCommodityRegisterAbility(ability, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeAccountCommodityRegisterAbility(long supportAbilityId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeAccountCommodityRegisterAbility(supportAbilityId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeAccountCommodityRegisterAbility(long supportAbilityId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeAccountCommodityRegisterAbility").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).removeAccountCommodityRegisterAbility(platformArgs, supportAbilityId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeAccountCommodityRegisterAbility(int routeKey, int timeout,long supportAbilityId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeAccountCommodityRegisterAbility(supportAbilityId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  batAddAccountCommodityRegisterAbility(List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    batAddAccountCommodityRegisterAbility(abilities, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  batAddAccountCommodityRegisterAbility(List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("batAddAccountCommodityRegisterAbility").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).batAddAccountCommodityRegisterAbility(platformArgs, abilities);
      return null;
      }
    }, invokeInfo);
  }

  public void  batAddAccountCommodityRegisterAbility(int routeKey, int timeout,List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    batAddAccountCommodityRegisterAbility(abilities, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeAccountCommodityRegisterAbilities(long accountId, int sledExchangeId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeAccountCommodityRegisterAbilities(accountId, sledExchangeId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeAccountCommodityRegisterAbilities(long accountId, int sledExchangeId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeAccountCommodityRegisterAbilities").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).removeAccountCommodityRegisterAbilities(platformArgs, accountId, sledExchangeId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeAccountCommodityRegisterAbilities(int routeKey, int timeout,long accountId, int sledExchangeId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeAccountCommodityRegisterAbilities(accountId, sledExchangeId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage  reqQuotationAccountSupport(xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqQuotationAccountSupport(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage  reqQuotationAccountSupport(xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqQuotationAccountSupport").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage>(){
    @Override
    public xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).reqQuotationAccountSupport(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage  reqQuotationAccountSupport(int routeKey, int timeout,xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqQuotationAccountSupport(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  setCommodityRegisterOrderIndex(int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    setCommodityRegisterOrderIndex(sledCommodityId, platformEnv, orderIndex, activeType, fixedCode, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  setCommodityRegisterOrderIndex(int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("setCommodityRegisterOrderIndex").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).setCommodityRegisterOrderIndex(platformArgs, sledCommodityId, platformEnv, orderIndex, activeType, fixedCode);
      return null;
      }
    }, invokeInfo);
  }

  public void  setCommodityRegisterOrderIndex(int routeKey, int timeout,int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, xueqiao.quotation.account.thriftapi.ContractActiveType activeType, String fixedCode)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    setCommodityRegisterOrderIndex(sledCommodityId, platformEnv, orderIndex, activeType, fixedCode, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  notifySubscribeQuoteStateChange() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    notifySubscribeQuoteStateChange(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  notifySubscribeQuoteStateChange(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("notifySubscribeQuoteStateChange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).notifySubscribeQuoteStateChange(platformArgs);
      return null;
      }
    }, invokeInfo);
  }

  public void  notifySubscribeQuoteStateChange(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    notifySubscribeQuoteStateChange(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask  reqLatestSQSTask() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqLatestSQSTask(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask  reqLatestSQSTask(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqLatestSQSTask").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask>(){
    @Override
    public xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationAccountDao.Client(protocol).reqLatestSQSTask(platformArgs);
      }
    }, invokeInfo);
  }

  public xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask  reqLatestSQSTask(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqLatestSQSTask(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeEarlySQSTask(long taskId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeEarlySQSTask(taskId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeEarlySQSTask(long taskId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeEarlySQSTask").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationAccountDao.Client(protocol).removeEarlySQSTask(platformArgs, taskId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeEarlySQSTask(int routeKey, int timeout,long taskId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeEarlySQSTask(taskId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
