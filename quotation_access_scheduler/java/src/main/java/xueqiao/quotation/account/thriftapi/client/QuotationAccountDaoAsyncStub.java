package xueqiao.quotation.account.thriftapi.client;

import xueqiao.quotation.account.thriftapi.QuotationAccountDao;
import xueqiao.quotation.account.thriftapi.QuotationAccountDaoVariable;
import org.apache.thrift.TException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.client.AsyncCallRunner;
import org.soldier.platform.svr_platform.client.IMethodCallback;
import org.soldier.platform.svr_platform.client.SvrContainer;
import org.soldier.platform.svr_platform.client.TRequestOption;
import org.soldier.platform.svr_platform.client.TServiceCall;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.client.BaseStub;
import java.util.List;

public class QuotationAccountDaoAsyncStub extends BaseStub { 
  public QuotationAccountDaoAsyncStub() {
    super(QuotationAccountDaoVariable.serviceKey);
  }
  public void send_reqContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqContractActiveRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqContractActiveRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqContractActiveRule_args, QuotationAccountDao.reqContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqContractActiveRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqContractActiveRuleCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqContractActiveRule_args, QuotationAccountDao.reqContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqContractActiveRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqContractActiveRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.reqContractActiveRule_args request = new QuotationAccountDao.reqContractActiveRule_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqContractActiveRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.reqContractActiveRule_result.class);
    return serviceCall;
  }

  public void send_reqContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqContractRegisterRule_args, QuotationAccountDao.reqContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqContractRegisterRuleCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqContractRegisterRule_args, QuotationAccountDao.reqContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqContractRegisterRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.reqContractRegisterRule_args request = new QuotationAccountDao.reqContractRegisterRule_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqContractRegisterRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.reqContractRegisterRule_result.class);
    return serviceCall;
  }

  public void send_reqQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqQuotationAccountServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqQuotationAccountServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqQuotationAccount_args, QuotationAccountDao.reqQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqQuotationAccountServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqQuotationAccountCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqQuotationAccount_args, QuotationAccountDao.reqQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqQuotationAccountServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqQuotationAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.reqQuotationAccount_args request = new QuotationAccountDao.reqQuotationAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqQuotationAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.reqQuotationAccount_result.class);
    return serviceCall;
  }

  public void send_addContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), new TRequestOption());
  }

  public void send_addContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), requestOption);
  }

  public long addContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule, IMethodCallback<QuotationAccountDao.addContractActiveRule_args, QuotationAccountDao.addContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  public long add_addContractActiveRuleCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule, IMethodCallback<QuotationAccountDao.addContractActiveRule_args, QuotationAccountDao.addContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  protected TServiceCall create_addContractActiveRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractActiveRule rule){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.addContractActiveRule_args request = new QuotationAccountDao.addContractActiveRule_args();
    request.setPlatformArgs(platformArgs);
    request.setRule(rule);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addContractActiveRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.addContractActiveRule_result.class);
    return serviceCall;
  }

  public void send_addContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), new TRequestOption());
  }

  public void send_addContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), requestOption);
  }

  public long addContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule, IMethodCallback<QuotationAccountDao.addContractRegisterRule_args, QuotationAccountDao.addContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  public long add_addContractRegisterRuleCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule, IMethodCallback<QuotationAccountDao.addContractRegisterRule_args, QuotationAccountDao.addContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  protected TServiceCall create_addContractRegisterRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.addContractRegisterRule_args request = new QuotationAccountDao.addContractRegisterRule_args();
    request.setPlatformArgs(platformArgs);
    request.setRule(rule);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addContractRegisterRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.addContractRegisterRule_result.class);
    return serviceCall;
  }

  public void send_addQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), new TRequestOption());
  }

  public void send_addQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), requestOption);
  }

  public long addQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account, IMethodCallback<QuotationAccountDao.addQuotationAccount_args, QuotationAccountDao.addQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), callback);
  }

  public long add_addQuotationAccountCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account, IMethodCallback<QuotationAccountDao.addQuotationAccount_args, QuotationAccountDao.addQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), callback);
  }

  protected TServiceCall create_addQuotationAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.QuotationAccount account){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.addQuotationAccount_args request = new QuotationAccountDao.addQuotationAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setAccount(account);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addQuotationAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.addQuotationAccount_result.class);
    return serviceCall;
  }

  public void send_updateContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), new TRequestOption());
  }

  public void send_updateContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), requestOption);
  }

  public long updateContractActiveRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule, IMethodCallback<QuotationAccountDao.updateContractActiveRule_args, QuotationAccountDao.updateContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_updateContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  public long add_updateContractActiveRuleCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractActiveRule rule, IMethodCallback<QuotationAccountDao.updateContractActiveRule_args, QuotationAccountDao.updateContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_updateContractActiveRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  protected TServiceCall create_updateContractActiveRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractActiveRule rule){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.updateContractActiveRule_args request = new QuotationAccountDao.updateContractActiveRule_args();
    request.setPlatformArgs(platformArgs);
    request.setRule(rule);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateContractActiveRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.updateContractActiveRule_result.class);
    return serviceCall;
  }

  public void send_updateContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), new TRequestOption());
  }

  public void send_updateContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), requestOption);
  }

  public long updateContractRegisterRule(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule, IMethodCallback<QuotationAccountDao.updateContractRegisterRule_args, QuotationAccountDao.updateContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_updateContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  public long add_updateContractRegisterRuleCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule, IMethodCallback<QuotationAccountDao.updateContractRegisterRule_args, QuotationAccountDao.updateContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_updateContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, rule), callback);
  }

  protected TServiceCall create_updateContractRegisterRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ContractRegisterRule rule){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.updateContractRegisterRule_args request = new QuotationAccountDao.updateContractRegisterRule_args();
    request.setPlatformArgs(platformArgs);
    request.setRule(rule);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateContractRegisterRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.updateContractRegisterRule_result.class);
    return serviceCall;
  }

  public void send_updateQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), new TRequestOption());
  }

  public void send_updateQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_updateQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), requestOption);
  }

  public long updateQuotationAccount(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account, IMethodCallback<QuotationAccountDao.updateQuotationAccount_args, QuotationAccountDao.updateQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_updateQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), callback);
  }

  public long add_updateQuotationAccountCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.QuotationAccount account, IMethodCallback<QuotationAccountDao.updateQuotationAccount_args, QuotationAccountDao.updateQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_updateQuotationAccountServiceCall(routeKey, timeout, platformArgs, account), callback);
  }

  protected TServiceCall create_updateQuotationAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.QuotationAccount account){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.updateQuotationAccount_args request = new QuotationAccountDao.updateQuotationAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setAccount(account);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateQuotationAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.updateQuotationAccount_result.class);
    return serviceCall;
  }

  public void send_removeContractActiveRule(int routeKey, int timeout, int sledCommodityId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeContractActiveRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId), new TRequestOption());
  }

  public void send_removeContractActiveRule(int routeKey, int timeout, int sledCommodityId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeContractActiveRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId), requestOption);
  }

  public long removeContractActiveRule(int routeKey, int timeout, int sledCommodityId, IMethodCallback<QuotationAccountDao.removeContractActiveRule_args, QuotationAccountDao.removeContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_removeContractActiveRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId), callback);
  }

  public long add_removeContractActiveRuleCall(AsyncCallRunner runner, int routeKey, int timeout, int sledCommodityId, IMethodCallback<QuotationAccountDao.removeContractActiveRule_args, QuotationAccountDao.removeContractActiveRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_removeContractActiveRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId), callback);
  }

  protected TServiceCall create_removeContractActiveRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int sledCommodityId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.removeContractActiveRule_args request = new QuotationAccountDao.removeContractActiveRule_args();
    request.setPlatformArgs(platformArgs);
    request.setSledCommodityId(sledCommodityId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeContractActiveRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.removeContractActiveRule_result.class);
    return serviceCall;
  }

  public void send_removeContractRegisterRule(int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv), new TRequestOption());
  }

  public void send_removeContractRegisterRule(int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv), requestOption);
  }

  public long removeContractRegisterRule(int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, IMethodCallback<QuotationAccountDao.removeContractRegisterRule_args, QuotationAccountDao.removeContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_removeContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv), callback);
  }

  public long add_removeContractRegisterRuleCall(AsyncCallRunner runner, int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, IMethodCallback<QuotationAccountDao.removeContractRegisterRule_args, QuotationAccountDao.removeContractRegisterRule_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_removeContractRegisterRuleServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv), callback);
  }

  protected TServiceCall create_removeContractRegisterRuleServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.removeContractRegisterRule_args request = new QuotationAccountDao.removeContractRegisterRule_args();
    request.setPlatformArgs(platformArgs);
    request.setSledCommodityId(sledCommodityId);
    request.setPlatformEnv(platformEnv);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeContractRegisterRule");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.removeContractRegisterRule_result.class);
    return serviceCall;
  }

  public void send_removeQuotationAccount(int routeKey, int timeout, long accountId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeQuotationAccountServiceCall(routeKey, timeout, platformArgs, accountId), new TRequestOption());
  }

  public void send_removeQuotationAccount(int routeKey, int timeout, long accountId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeQuotationAccountServiceCall(routeKey, timeout, platformArgs, accountId), requestOption);
  }

  public long removeQuotationAccount(int routeKey, int timeout, long accountId, IMethodCallback<QuotationAccountDao.removeQuotationAccount_args, QuotationAccountDao.removeQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_removeQuotationAccountServiceCall(routeKey, timeout, platformArgs, accountId), callback);
  }

  public long add_removeQuotationAccountCall(AsyncCallRunner runner, int routeKey, int timeout, long accountId, IMethodCallback<QuotationAccountDao.removeQuotationAccount_args, QuotationAccountDao.removeQuotationAccount_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_removeQuotationAccountServiceCall(routeKey, timeout, platformArgs, accountId), callback);
  }

  protected TServiceCall create_removeQuotationAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long accountId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.removeQuotationAccount_args request = new QuotationAccountDao.removeQuotationAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setAccountId(accountId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeQuotationAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.removeQuotationAccount_result.class);
    return serviceCall;
  }

  public void send_reqAccountCommodityRegisterAbility(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqAccountCommodityRegisterAbility(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqAccountCommodityRegisterAbility(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqAccountCommodityRegisterAbility_args, QuotationAccountDao.reqAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqAccountCommodityRegisterAbilityCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqAccountCommodityRegisterAbility_args, QuotationAccountDao.reqAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqAccountCommodityRegisterAbilityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqAccountCommodityRegisterAbilityOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.reqAccountCommodityRegisterAbility_args request = new QuotationAccountDao.reqAccountCommodityRegisterAbility_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqAccountCommodityRegisterAbility");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.reqAccountCommodityRegisterAbility_result.class);
    return serviceCall;
  }

  public void send_addAccountCommodityRegisterAbility(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, ability), new TRequestOption());
  }

  public void send_addAccountCommodityRegisterAbility(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_addAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, ability), requestOption);
  }

  public long addAccountCommodityRegisterAbility(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability, IMethodCallback<QuotationAccountDao.addAccountCommodityRegisterAbility_args, QuotationAccountDao.addAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_addAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, ability), callback);
  }

  public long add_addAccountCommodityRegisterAbilityCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability, IMethodCallback<QuotationAccountDao.addAccountCommodityRegisterAbility_args, QuotationAccountDao.addAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_addAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, ability), callback);
  }

  protected TServiceCall create_addAccountCommodityRegisterAbilityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility ability){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.addAccountCommodityRegisterAbility_args request = new QuotationAccountDao.addAccountCommodityRegisterAbility_args();
    request.setPlatformArgs(platformArgs);
    request.setAbility(ability);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addAccountCommodityRegisterAbility");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.addAccountCommodityRegisterAbility_result.class);
    return serviceCall;
  }

  public void send_removeAccountCommodityRegisterAbility(int routeKey, int timeout, long supportAbilityId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, supportAbilityId), new TRequestOption());
  }

  public void send_removeAccountCommodityRegisterAbility(int routeKey, int timeout, long supportAbilityId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, supportAbilityId), requestOption);
  }

  public long removeAccountCommodityRegisterAbility(int routeKey, int timeout, long supportAbilityId, IMethodCallback<QuotationAccountDao.removeAccountCommodityRegisterAbility_args, QuotationAccountDao.removeAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_removeAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, supportAbilityId), callback);
  }

  public long add_removeAccountCommodityRegisterAbilityCall(AsyncCallRunner runner, int routeKey, int timeout, long supportAbilityId, IMethodCallback<QuotationAccountDao.removeAccountCommodityRegisterAbility_args, QuotationAccountDao.removeAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_removeAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, supportAbilityId), callback);
  }

  protected TServiceCall create_removeAccountCommodityRegisterAbilityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long supportAbilityId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.removeAccountCommodityRegisterAbility_args request = new QuotationAccountDao.removeAccountCommodityRegisterAbility_args();
    request.setPlatformArgs(platformArgs);
    request.setSupportAbilityId(supportAbilityId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeAccountCommodityRegisterAbility");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.removeAccountCommodityRegisterAbility_result.class);
    return serviceCall;
  }

  public void send_batAddAccountCommodityRegisterAbility(int routeKey, int timeout, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_batAddAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, abilities), new TRequestOption());
  }

  public void send_batAddAccountCommodityRegisterAbility(int routeKey, int timeout, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_batAddAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, abilities), requestOption);
  }

  public long batAddAccountCommodityRegisterAbility(int routeKey, int timeout, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities, IMethodCallback<QuotationAccountDao.batAddAccountCommodityRegisterAbility_args, QuotationAccountDao.batAddAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_batAddAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, abilities), callback);
  }

  public long add_batAddAccountCommodityRegisterAbilityCall(AsyncCallRunner runner, int routeKey, int timeout, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities, IMethodCallback<QuotationAccountDao.batAddAccountCommodityRegisterAbility_args, QuotationAccountDao.batAddAccountCommodityRegisterAbility_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_batAddAccountCommodityRegisterAbilityServiceCall(routeKey, timeout, platformArgs, abilities), callback);
  }

  protected TServiceCall create_batAddAccountCommodityRegisterAbilityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<xueqiao.quotation.account.thriftapi.AccountCommodityRegisterAbility> abilities){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.batAddAccountCommodityRegisterAbility_args request = new QuotationAccountDao.batAddAccountCommodityRegisterAbility_args();
    request.setPlatformArgs(platformArgs);
    request.setAbilities(abilities);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("batAddAccountCommodityRegisterAbility");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.batAddAccountCommodityRegisterAbility_result.class);
    return serviceCall;
  }

  public void send_removeAccountCommodityRegisterAbilities(int routeKey, int timeout, long accountId, int sledExchangeId) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeAccountCommodityRegisterAbilitiesServiceCall(routeKey, timeout, platformArgs, accountId, sledExchangeId), new TRequestOption());
  }

  public void send_removeAccountCommodityRegisterAbilities(int routeKey, int timeout, long accountId, int sledExchangeId,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_removeAccountCommodityRegisterAbilitiesServiceCall(routeKey, timeout, platformArgs, accountId, sledExchangeId), requestOption);
  }

  public long removeAccountCommodityRegisterAbilities(int routeKey, int timeout, long accountId, int sledExchangeId, IMethodCallback<QuotationAccountDao.removeAccountCommodityRegisterAbilities_args, QuotationAccountDao.removeAccountCommodityRegisterAbilities_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_removeAccountCommodityRegisterAbilitiesServiceCall(routeKey, timeout, platformArgs, accountId, sledExchangeId), callback);
  }

  public long add_removeAccountCommodityRegisterAbilitiesCall(AsyncCallRunner runner, int routeKey, int timeout, long accountId, int sledExchangeId, IMethodCallback<QuotationAccountDao.removeAccountCommodityRegisterAbilities_args, QuotationAccountDao.removeAccountCommodityRegisterAbilities_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_removeAccountCommodityRegisterAbilitiesServiceCall(routeKey, timeout, platformArgs, accountId, sledExchangeId), callback);
  }

  protected TServiceCall create_removeAccountCommodityRegisterAbilitiesServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long accountId, int sledExchangeId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.removeAccountCommodityRegisterAbilities_args request = new QuotationAccountDao.removeAccountCommodityRegisterAbilities_args();
    request.setPlatformArgs(platformArgs);
    request.setAccountId(accountId);
    request.setSledExchangeId(sledExchangeId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeAccountCommodityRegisterAbilities");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.removeAccountCommodityRegisterAbilities_result.class);
    return serviceCall;
  }

  public void send_reqQuotationAccountSupport(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqQuotationAccountSupportServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqQuotationAccountSupport(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_reqQuotationAccountSupportServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqQuotationAccountSupport(int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqQuotationAccountSupport_args, QuotationAccountDao.reqQuotationAccountSupport_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_reqQuotationAccountSupportServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqQuotationAccountSupportCall(AsyncCallRunner runner, int routeKey, int timeout, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<QuotationAccountDao.reqQuotationAccountSupport_args, QuotationAccountDao.reqQuotationAccountSupport_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_reqQuotationAccountSupportServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqQuotationAccountSupportServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.reqQuotationAccountSupport_args request = new QuotationAccountDao.reqQuotationAccountSupport_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqQuotationAccountSupport");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.reqQuotationAccountSupport_result.class);
    return serviceCall;
  }

  public void send_setCommodityRegisterOrderIndex(int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex) throws TException {
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_setCommodityRegisterOrderIndexServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv, orderIndex), new TRequestOption());
  }

  public void send_setCommodityRegisterOrderIndex(int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex,TRequestOption requestOption) throws TException { 
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    SvrContainer.getInstance().sendRequest(
        create_setCommodityRegisterOrderIndexServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv, orderIndex), requestOption);
  }

  public long setCommodityRegisterOrderIndex(int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, IMethodCallback<QuotationAccountDao.setCommodityRegisterOrderIndex_args, QuotationAccountDao.setCommodityRegisterOrderIndex_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":"
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return SvrContainer.getInstance().sendRequest(
            create_setCommodityRegisterOrderIndexServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv, orderIndex), callback);
  }

  public long add_setCommodityRegisterOrderIndexCall(AsyncCallRunner runner, int routeKey, int timeout, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex, IMethodCallback<QuotationAccountDao.setCommodityRegisterOrderIndex_args, QuotationAccountDao.setCommodityRegisterOrderIndex_result> callback) throws TException{
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);
    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
        callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
        + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    return runner.addAsyncCall(
            create_setCommodityRegisterOrderIndexServiceCall(routeKey, timeout, platformArgs, sledCommodityId, platformEnv, orderIndex), callback);
  }

  protected TServiceCall create_setCommodityRegisterOrderIndexServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int sledCommodityId, xueqiao.quotation.account.thriftapi.QuotationPlatformEnv platformEnv, int orderIndex){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(QuotationAccountDaoVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    QuotationAccountDao.setCommodityRegisterOrderIndex_args request = new QuotationAccountDao.setCommodityRegisterOrderIndex_args();
    request.setPlatformArgs(platformArgs);
    request.setSledCommodityId(sledCommodityId);
    request.setPlatformEnv(platformEnv);
    request.setOrderIndex(orderIndex);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("setCommodityRegisterOrderIndex");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(QuotationAccountDao.setCommodityRegisterOrderIndex_result.class);
    return serviceCall;
  }

}
