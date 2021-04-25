package com.longsheng.xueqiao.contract.dao.thriftapi.client;

import com.longsheng.xueqiao.contract.dao.thriftapi.ContractDaoService;
import com.longsheng.xueqiao.contract.dao.thriftapi.ContractDaoServiceVariable;
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
import com.longsheng.xueqiao.contract.dao.thriftapi.RemoveSledCommodityOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.RemoveSledExchangeOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.ReqTCommodityMapOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.ReqTSledCommodityChangeOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.ReqTSledCommodityOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.ReqTSledContractOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.ReqTSledExchangeOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.TCommodityMap;
import com.longsheng.xueqiao.contract.dao.thriftapi.TCommodityMapPage;
import com.longsheng.xueqiao.contract.dao.thriftapi.TCommodityPage;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledCommodity;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledCommodityChange;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledCommodityChangePage;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledContract;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledContractPage;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledExchange;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledExchangePage;

public class ContractDaoServiceAsyncStub extends BaseStub { 
  public ContractDaoServiceAsyncStub() {
    super(ContractDaoServiceVariable.serviceKey);
  }
  public void send_addTSledCommodity(int routeKey, int timeout, TSledCommodity tSledCommodity) throws TException {
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
        create_addTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), new TRequestOption());
  }

  public void send_addTSledCommodity(int routeKey, int timeout, TSledCommodity tSledCommodity,TRequestOption requestOption) throws TException { 
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
        create_addTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), requestOption);
  }

  public long addTSledCommodity(int routeKey, int timeout, TSledCommodity tSledCommodity, IMethodCallback<ContractDaoService.addTSledCommodity_args, ContractDaoService.addTSledCommodity_result> callback) throws TException{
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
            create_addTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), callback);
  }

  public long add_addTSledCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, TSledCommodity tSledCommodity, IMethodCallback<ContractDaoService.addTSledCommodity_args, ContractDaoService.addTSledCommodity_result> callback) throws TException{
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
            create_addTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), callback);
  }

  protected TServiceCall create_addTSledCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledCommodity tSledCommodity){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addTSledCommodity_args request = new ContractDaoService.addTSledCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setTSledCommodity(tSledCommodity);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addTSledCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addTSledCommodity_result.class);
    return serviceCall;
  }

  public void send_updateTSledCommodity(int routeKey, int timeout, TSledCommodity tSledCommodity) throws TException {
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
        create_updateTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), new TRequestOption());
  }

  public void send_updateTSledCommodity(int routeKey, int timeout, TSledCommodity tSledCommodity,TRequestOption requestOption) throws TException { 
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
        create_updateTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), requestOption);
  }

  public long updateTSledCommodity(int routeKey, int timeout, TSledCommodity tSledCommodity, IMethodCallback<ContractDaoService.updateTSledCommodity_args, ContractDaoService.updateTSledCommodity_result> callback) throws TException{
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
            create_updateTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), callback);
  }

  public long add_updateTSledCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, TSledCommodity tSledCommodity, IMethodCallback<ContractDaoService.updateTSledCommodity_args, ContractDaoService.updateTSledCommodity_result> callback) throws TException{
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
            create_updateTSledCommodityServiceCall(routeKey, timeout, platformArgs, tSledCommodity), callback);
  }

  protected TServiceCall create_updateTSledCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledCommodity tSledCommodity){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateTSledCommodity_args request = new ContractDaoService.updateTSledCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setTSledCommodity(tSledCommodity);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateTSledCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateTSledCommodity_result.class);
    return serviceCall;
  }

  public void send_reqTSledCommodity(int routeKey, int timeout, ReqTSledCommodityOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqTSledCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqTSledCommodity(int routeKey, int timeout, ReqTSledCommodityOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqTSledCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqTSledCommodity(int routeKey, int timeout, ReqTSledCommodityOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledCommodity_args, ContractDaoService.reqTSledCommodity_result> callback) throws TException{
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
            create_reqTSledCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqTSledCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, ReqTSledCommodityOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledCommodity_args, ContractDaoService.reqTSledCommodity_result> callback) throws TException{
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
            create_reqTSledCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqTSledCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqTSledCommodityOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqTSledCommodity_args request = new ContractDaoService.reqTSledCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqTSledCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqTSledCommodity_result.class);
    return serviceCall;
  }

  public void send_addTSledContract(int routeKey, int timeout, TSledContract tSledContract) throws TException {
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
        create_addTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), new TRequestOption());
  }

  public void send_addTSledContract(int routeKey, int timeout, TSledContract tSledContract,TRequestOption requestOption) throws TException { 
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
        create_addTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), requestOption);
  }

  public long addTSledContract(int routeKey, int timeout, TSledContract tSledContract, IMethodCallback<ContractDaoService.addTSledContract_args, ContractDaoService.addTSledContract_result> callback) throws TException{
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
            create_addTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), callback);
  }

  public long add_addTSledContractCall(AsyncCallRunner runner, int routeKey, int timeout, TSledContract tSledContract, IMethodCallback<ContractDaoService.addTSledContract_args, ContractDaoService.addTSledContract_result> callback) throws TException{
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
            create_addTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), callback);
  }

  protected TServiceCall create_addTSledContractServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledContract tSledContract){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addTSledContract_args request = new ContractDaoService.addTSledContract_args();
    request.setPlatformArgs(platformArgs);
    request.setTSledContract(tSledContract);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addTSledContract");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addTSledContract_result.class);
    return serviceCall;
  }

  public void send_updateTSledContract(int routeKey, int timeout, TSledContract tSledContract) throws TException {
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
        create_updateTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), new TRequestOption());
  }

  public void send_updateTSledContract(int routeKey, int timeout, TSledContract tSledContract,TRequestOption requestOption) throws TException { 
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
        create_updateTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), requestOption);
  }

  public long updateTSledContract(int routeKey, int timeout, TSledContract tSledContract, IMethodCallback<ContractDaoService.updateTSledContract_args, ContractDaoService.updateTSledContract_result> callback) throws TException{
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
            create_updateTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), callback);
  }

  public long add_updateTSledContractCall(AsyncCallRunner runner, int routeKey, int timeout, TSledContract tSledContract, IMethodCallback<ContractDaoService.updateTSledContract_args, ContractDaoService.updateTSledContract_result> callback) throws TException{
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
            create_updateTSledContractServiceCall(routeKey, timeout, platformArgs, tSledContract), callback);
  }

  protected TServiceCall create_updateTSledContractServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledContract tSledContract){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateTSledContract_args request = new ContractDaoService.updateTSledContract_args();
    request.setPlatformArgs(platformArgs);
    request.setTSledContract(tSledContract);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateTSledContract");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateTSledContract_result.class);
    return serviceCall;
  }

  public void send_reqTSledContract(int routeKey, int timeout, ReqTSledContractOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqTSledContractServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqTSledContract(int routeKey, int timeout, ReqTSledContractOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqTSledContractServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqTSledContract(int routeKey, int timeout, ReqTSledContractOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledContract_args, ContractDaoService.reqTSledContract_result> callback) throws TException{
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
            create_reqTSledContractServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqTSledContractCall(AsyncCallRunner runner, int routeKey, int timeout, ReqTSledContractOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledContract_args, ContractDaoService.reqTSledContract_result> callback) throws TException{
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
            create_reqTSledContractServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqTSledContractServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqTSledContractOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqTSledContract_args request = new ContractDaoService.reqTSledContract_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqTSledContract");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqTSledContract_result.class);
    return serviceCall;
  }

  public void send_addTSledExchange(int routeKey, int timeout, TSledExchange tSledExchange) throws TException {
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
        create_addTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), new TRequestOption());
  }

  public void send_addTSledExchange(int routeKey, int timeout, TSledExchange tSledExchange,TRequestOption requestOption) throws TException { 
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
        create_addTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), requestOption);
  }

  public long addTSledExchange(int routeKey, int timeout, TSledExchange tSledExchange, IMethodCallback<ContractDaoService.addTSledExchange_args, ContractDaoService.addTSledExchange_result> callback) throws TException{
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
            create_addTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), callback);
  }

  public long add_addTSledExchangeCall(AsyncCallRunner runner, int routeKey, int timeout, TSledExchange tSledExchange, IMethodCallback<ContractDaoService.addTSledExchange_args, ContractDaoService.addTSledExchange_result> callback) throws TException{
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
            create_addTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), callback);
  }

  protected TServiceCall create_addTSledExchangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledExchange tSledExchange){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addTSledExchange_args request = new ContractDaoService.addTSledExchange_args();
    request.setPlatformArgs(platformArgs);
    request.setTSledExchange(tSledExchange);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addTSledExchange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addTSledExchange_result.class);
    return serviceCall;
  }

  public void send_updateTSledExchange(int routeKey, int timeout, TSledExchange tSledExchange) throws TException {
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
        create_updateTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), new TRequestOption());
  }

  public void send_updateTSledExchange(int routeKey, int timeout, TSledExchange tSledExchange,TRequestOption requestOption) throws TException { 
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
        create_updateTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), requestOption);
  }

  public long updateTSledExchange(int routeKey, int timeout, TSledExchange tSledExchange, IMethodCallback<ContractDaoService.updateTSledExchange_args, ContractDaoService.updateTSledExchange_result> callback) throws TException{
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
            create_updateTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), callback);
  }

  public long add_updateTSledExchangeCall(AsyncCallRunner runner, int routeKey, int timeout, TSledExchange tSledExchange, IMethodCallback<ContractDaoService.updateTSledExchange_args, ContractDaoService.updateTSledExchange_result> callback) throws TException{
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
            create_updateTSledExchangeServiceCall(routeKey, timeout, platformArgs, tSledExchange), callback);
  }

  protected TServiceCall create_updateTSledExchangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledExchange tSledExchange){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateTSledExchange_args request = new ContractDaoService.updateTSledExchange_args();
    request.setPlatformArgs(platformArgs);
    request.setTSledExchange(tSledExchange);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateTSledExchange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateTSledExchange_result.class);
    return serviceCall;
  }

  public void send_reqTSledExchange(int routeKey, int timeout, ReqTSledExchangeOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqTSledExchangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqTSledExchange(int routeKey, int timeout, ReqTSledExchangeOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqTSledExchangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqTSledExchange(int routeKey, int timeout, ReqTSledExchangeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledExchange_args, ContractDaoService.reqTSledExchange_result> callback) throws TException{
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
            create_reqTSledExchangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqTSledExchangeCall(AsyncCallRunner runner, int routeKey, int timeout, ReqTSledExchangeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledExchange_args, ContractDaoService.reqTSledExchange_result> callback) throws TException{
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
            create_reqTSledExchangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqTSledExchangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqTSledExchangeOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqTSledExchange_args request = new ContractDaoService.reqTSledExchange_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqTSledExchange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqTSledExchange_result.class);
    return serviceCall;
  }

  public void send_reqTCommodityMap(int routeKey, int timeout, ReqTCommodityMapOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqTCommodityMapServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqTCommodityMap(int routeKey, int timeout, ReqTCommodityMapOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqTCommodityMapServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqTCommodityMap(int routeKey, int timeout, ReqTCommodityMapOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTCommodityMap_args, ContractDaoService.reqTCommodityMap_result> callback) throws TException{
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
            create_reqTCommodityMapServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqTCommodityMapCall(AsyncCallRunner runner, int routeKey, int timeout, ReqTCommodityMapOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTCommodityMap_args, ContractDaoService.reqTCommodityMap_result> callback) throws TException{
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
            create_reqTCommodityMapServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqTCommodityMapServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqTCommodityMapOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqTCommodityMap_args request = new ContractDaoService.reqTCommodityMap_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqTCommodityMap");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqTCommodityMap_result.class);
    return serviceCall;
  }

  public void send_addTCommodityMap(int routeKey, int timeout, TCommodityMap tCommodityMap) throws TException {
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
        create_addTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), new TRequestOption());
  }

  public void send_addTCommodityMap(int routeKey, int timeout, TCommodityMap tCommodityMap,TRequestOption requestOption) throws TException { 
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
        create_addTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), requestOption);
  }

  public long addTCommodityMap(int routeKey, int timeout, TCommodityMap tCommodityMap, IMethodCallback<ContractDaoService.addTCommodityMap_args, ContractDaoService.addTCommodityMap_result> callback) throws TException{
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
            create_addTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), callback);
  }

  public long add_addTCommodityMapCall(AsyncCallRunner runner, int routeKey, int timeout, TCommodityMap tCommodityMap, IMethodCallback<ContractDaoService.addTCommodityMap_args, ContractDaoService.addTCommodityMap_result> callback) throws TException{
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
            create_addTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), callback);
  }

  protected TServiceCall create_addTCommodityMapServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TCommodityMap tCommodityMap){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addTCommodityMap_args request = new ContractDaoService.addTCommodityMap_args();
    request.setPlatformArgs(platformArgs);
    request.setTCommodityMap(tCommodityMap);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addTCommodityMap");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addTCommodityMap_result.class);
    return serviceCall;
  }

  public void send_addSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping) throws TException {
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
        create_addSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), new TRequestOption());
  }

  public void send_addSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping,TRequestOption requestOption) throws TException { 
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
        create_addSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), requestOption);
  }

  public long addSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping, IMethodCallback<ContractDaoService.addSledExchangeMapping_args, ContractDaoService.addSledExchangeMapping_result> callback) throws TException{
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
            create_addSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), callback);
  }

  public long add_addSledExchangeMappingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping, IMethodCallback<ContractDaoService.addSledExchangeMapping_args, ContractDaoService.addSledExchangeMapping_result> callback) throws TException{
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
            create_addSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), callback);
  }

  protected TServiceCall create_addSledExchangeMappingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addSledExchangeMapping_args request = new ContractDaoService.addSledExchangeMapping_args();
    request.setPlatformArgs(platformArgs);
    request.setSledExchangeMapping(sledExchangeMapping);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addSledExchangeMapping");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addSledExchangeMapping_result.class);
    return serviceCall;
  }

  public void send_updateSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping) throws TException {
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
        create_updateSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), new TRequestOption());
  }

  public void send_updateSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping,TRequestOption requestOption) throws TException { 
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
        create_updateSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), requestOption);
  }

  public long updateSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping, IMethodCallback<ContractDaoService.updateSledExchangeMapping_args, ContractDaoService.updateSledExchangeMapping_result> callback) throws TException{
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
            create_updateSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), callback);
  }

  public long add_updateSledExchangeMappingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping, IMethodCallback<ContractDaoService.updateSledExchangeMapping_args, ContractDaoService.updateSledExchangeMapping_result> callback) throws TException{
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
            create_updateSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, sledExchangeMapping), callback);
  }

  protected TServiceCall create_updateSledExchangeMappingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateSledExchangeMapping_args request = new ContractDaoService.updateSledExchangeMapping_args();
    request.setPlatformArgs(platformArgs);
    request.setSledExchangeMapping(sledExchangeMapping);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateSledExchangeMapping");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateSledExchangeMapping_result.class);
    return serviceCall;
  }

  public void send_reqSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option) throws TException {
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
        create_reqSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option,TRequestOption requestOption) throws TException { 
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
        create_reqSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqSledExchangeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option, IMethodCallback<ContractDaoService.reqSledExchangeMapping_args, ContractDaoService.reqSledExchangeMapping_result> callback) throws TException{
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
            create_reqSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqSledExchangeMappingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option, IMethodCallback<ContractDaoService.reqSledExchangeMapping_args, ContractDaoService.reqSledExchangeMapping_result> callback) throws TException{
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
            create_reqSledExchangeMappingServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqSledExchangeMappingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSledExchangeMapping_args request = new ContractDaoService.reqSledExchangeMapping_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSledExchangeMapping");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSledExchangeMapping_result.class);
    return serviceCall;
  }

  public void send_addSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping) throws TException {
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
        create_addSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), new TRequestOption());
  }

  public void send_addSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping,TRequestOption requestOption) throws TException { 
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
        create_addSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), requestOption);
  }

  public long addSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping, IMethodCallback<ContractDaoService.addSledCommodityTypeMapping_args, ContractDaoService.addSledCommodityTypeMapping_result> callback) throws TException{
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
            create_addSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), callback);
  }

  public long add_addSledCommodityTypeMappingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping, IMethodCallback<ContractDaoService.addSledCommodityTypeMapping_args, ContractDaoService.addSledCommodityTypeMapping_result> callback) throws TException{
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
            create_addSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), callback);
  }

  protected TServiceCall create_addSledCommodityTypeMappingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addSledCommodityTypeMapping_args request = new ContractDaoService.addSledCommodityTypeMapping_args();
    request.setPlatformArgs(platformArgs);
    request.setSledCommodityTypeMapping(sledCommodityTypeMapping);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addSledCommodityTypeMapping");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addSledCommodityTypeMapping_result.class);
    return serviceCall;
  }

  public void send_updateSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping) throws TException {
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
        create_updateSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), new TRequestOption());
  }

  public void send_updateSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping,TRequestOption requestOption) throws TException { 
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
        create_updateSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), requestOption);
  }

  public long updateSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping, IMethodCallback<ContractDaoService.updateSledCommodityTypeMapping_args, ContractDaoService.updateSledCommodityTypeMapping_result> callback) throws TException{
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
            create_updateSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), callback);
  }

  public long add_updateSledCommodityTypeMappingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping, IMethodCallback<ContractDaoService.updateSledCommodityTypeMapping_args, ContractDaoService.updateSledCommodityTypeMapping_result> callback) throws TException{
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
            create_updateSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, sledCommodityTypeMapping), callback);
  }

  protected TServiceCall create_updateSledCommodityTypeMappingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateSledCommodityTypeMapping_args request = new ContractDaoService.updateSledCommodityTypeMapping_args();
    request.setPlatformArgs(platformArgs);
    request.setSledCommodityTypeMapping(sledCommodityTypeMapping);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateSledCommodityTypeMapping");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateSledCommodityTypeMapping_result.class);
    return serviceCall;
  }

  public void send_reqSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option) throws TException {
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
        create_reqSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option,TRequestOption requestOption) throws TException { 
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
        create_reqSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqSledCommodityTypeMapping(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option, IMethodCallback<ContractDaoService.reqSledCommodityTypeMapping_args, ContractDaoService.reqSledCommodityTypeMapping_result> callback) throws TException{
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
            create_reqSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqSledCommodityTypeMappingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option, IMethodCallback<ContractDaoService.reqSledCommodityTypeMapping_args, ContractDaoService.reqSledCommodityTypeMapping_result> callback) throws TException{
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
            create_reqSledCommodityTypeMappingServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqSledCommodityTypeMappingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSledCommodityTypeMapping_args request = new ContractDaoService.reqSledCommodityTypeMapping_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSledCommodityTypeMapping");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSledCommodityTypeMapping_result.class);
    return serviceCall;
  }

  public void send_updateTCommodityMap(int routeKey, int timeout, TCommodityMap tCommodityMap) throws TException {
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
        create_updateTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), new TRequestOption());
  }

  public void send_updateTCommodityMap(int routeKey, int timeout, TCommodityMap tCommodityMap,TRequestOption requestOption) throws TException { 
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
        create_updateTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), requestOption);
  }

  public long updateTCommodityMap(int routeKey, int timeout, TCommodityMap tCommodityMap, IMethodCallback<ContractDaoService.updateTCommodityMap_args, ContractDaoService.updateTCommodityMap_result> callback) throws TException{
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
            create_updateTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), callback);
  }

  public long add_updateTCommodityMapCall(AsyncCallRunner runner, int routeKey, int timeout, TCommodityMap tCommodityMap, IMethodCallback<ContractDaoService.updateTCommodityMap_args, ContractDaoService.updateTCommodityMap_result> callback) throws TException{
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
            create_updateTCommodityMapServiceCall(routeKey, timeout, platformArgs, tCommodityMap), callback);
  }

  protected TServiceCall create_updateTCommodityMapServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TCommodityMap tCommodityMap){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateTCommodityMap_args request = new ContractDaoService.updateTCommodityMap_args();
    request.setPlatformArgs(platformArgs);
    request.setTCommodityMap(tCommodityMap);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateTCommodityMap");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateTCommodityMap_result.class);
    return serviceCall;
  }

  public void send_inactiveExpiredSledContract(int routeKey, int timeout, long expiredTimestamp) throws TException {
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
        create_inactiveExpiredSledContractServiceCall(routeKey, timeout, platformArgs, expiredTimestamp), new TRequestOption());
  }

  public void send_inactiveExpiredSledContract(int routeKey, int timeout, long expiredTimestamp,TRequestOption requestOption) throws TException { 
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
        create_inactiveExpiredSledContractServiceCall(routeKey, timeout, platformArgs, expiredTimestamp), requestOption);
  }

  public long inactiveExpiredSledContract(int routeKey, int timeout, long expiredTimestamp, IMethodCallback<ContractDaoService.inactiveExpiredSledContract_args, ContractDaoService.inactiveExpiredSledContract_result> callback) throws TException{
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
            create_inactiveExpiredSledContractServiceCall(routeKey, timeout, platformArgs, expiredTimestamp), callback);
  }

  public long add_inactiveExpiredSledContractCall(AsyncCallRunner runner, int routeKey, int timeout, long expiredTimestamp, IMethodCallback<ContractDaoService.inactiveExpiredSledContract_args, ContractDaoService.inactiveExpiredSledContract_result> callback) throws TException{
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
            create_inactiveExpiredSledContractServiceCall(routeKey, timeout, platformArgs, expiredTimestamp), callback);
  }

  protected TServiceCall create_inactiveExpiredSledContractServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long expiredTimestamp){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.inactiveExpiredSledContract_args request = new ContractDaoService.inactiveExpiredSledContract_args();
    request.setPlatformArgs(platformArgs);
    request.setExpiredTimestamp(expiredTimestamp);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("inactiveExpiredSledContract");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.inactiveExpiredSledContract_result.class);
    return serviceCall;
  }

  public void send_reqTSledCommodityChange(int routeKey, int timeout, ReqTSledCommodityChangeOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqTSledCommodityChange(int routeKey, int timeout, ReqTSledCommodityChangeOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqTSledCommodityChange(int routeKey, int timeout, ReqTSledCommodityChangeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledCommodityChange_args, ContractDaoService.reqTSledCommodityChange_result> callback) throws TException{
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
            create_reqTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqTSledCommodityChangeCall(AsyncCallRunner runner, int routeKey, int timeout, ReqTSledCommodityChangeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTSledCommodityChange_args, ContractDaoService.reqTSledCommodityChange_result> callback) throws TException{
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
            create_reqTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqTSledCommodityChangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, ReqTSledCommodityChangeOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqTSledCommodityChange_args request = new ContractDaoService.reqTSledCommodityChange_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqTSledCommodityChange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqTSledCommodityChange_result.class);
    return serviceCall;
  }

  public void send_addTSledCommodityChange(int routeKey, int timeout, TSledCommodityChange tCommodityChange) throws TException {
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
        create_addTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), new TRequestOption());
  }

  public void send_addTSledCommodityChange(int routeKey, int timeout, TSledCommodityChange tCommodityChange,TRequestOption requestOption) throws TException { 
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
        create_addTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), requestOption);
  }

  public long addTSledCommodityChange(int routeKey, int timeout, TSledCommodityChange tCommodityChange, IMethodCallback<ContractDaoService.addTSledCommodityChange_args, ContractDaoService.addTSledCommodityChange_result> callback) throws TException{
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
            create_addTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), callback);
  }

  public long add_addTSledCommodityChangeCall(AsyncCallRunner runner, int routeKey, int timeout, TSledCommodityChange tCommodityChange, IMethodCallback<ContractDaoService.addTSledCommodityChange_args, ContractDaoService.addTSledCommodityChange_result> callback) throws TException{
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
            create_addTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), callback);
  }

  protected TServiceCall create_addTSledCommodityChangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledCommodityChange tCommodityChange){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addTSledCommodityChange_args request = new ContractDaoService.addTSledCommodityChange_args();
    request.setPlatformArgs(platformArgs);
    request.setTCommodityChange(tCommodityChange);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addTSledCommodityChange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addTSledCommodityChange_result.class);
    return serviceCall;
  }

  public void send_removeTSledCommodityChange(int routeKey, int timeout, TSledCommodityChange tCommodityChange) throws TException {
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
        create_removeTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), new TRequestOption());
  }

  public void send_removeTSledCommodityChange(int routeKey, int timeout, TSledCommodityChange tCommodityChange,TRequestOption requestOption) throws TException { 
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
        create_removeTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), requestOption);
  }

  public long removeTSledCommodityChange(int routeKey, int timeout, TSledCommodityChange tCommodityChange, IMethodCallback<ContractDaoService.removeTSledCommodityChange_args, ContractDaoService.removeTSledCommodityChange_result> callback) throws TException{
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
            create_removeTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), callback);
  }

  public long add_removeTSledCommodityChangeCall(AsyncCallRunner runner, int routeKey, int timeout, TSledCommodityChange tCommodityChange, IMethodCallback<ContractDaoService.removeTSledCommodityChange_args, ContractDaoService.removeTSledCommodityChange_result> callback) throws TException{
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
            create_removeTSledCommodityChangeServiceCall(routeKey, timeout, platformArgs, tCommodityChange), callback);
  }

  protected TServiceCall create_removeTSledCommodityChangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, TSledCommodityChange tCommodityChange){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeTSledCommodityChange_args request = new ContractDaoService.removeTSledCommodityChange_args();
    request.setPlatformArgs(platformArgs);
    request.setTCommodityChange(tCommodityChange);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeTSledCommodityChange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeTSledCommodityChange_result.class);
    return serviceCall;
  }

  public void send_addCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo) throws TException {
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
        create_addCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), new TRequestOption());
  }

  public void send_addCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo,TRequestOption requestOption) throws TException { 
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
        create_addCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), requestOption);
  }

  public long addCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo, IMethodCallback<ContractDaoService.addCommodityMapFileInfo_args, ContractDaoService.addCommodityMapFileInfo_result> callback) throws TException{
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
            create_addCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), callback);
  }

  public long add_addCommodityMapFileInfoCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo, IMethodCallback<ContractDaoService.addCommodityMapFileInfo_args, ContractDaoService.addCommodityMapFileInfo_result> callback) throws TException{
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
            create_addCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), callback);
  }

  protected TServiceCall create_addCommodityMapFileInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addCommodityMapFileInfo_args request = new ContractDaoService.addCommodityMapFileInfo_args();
    request.setPlatformArgs(platformArgs);
    request.setMapFileInfo(mapFileInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addCommodityMapFileInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addCommodityMapFileInfo_result.class);
    return serviceCall;
  }

  public void send_updateCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo) throws TException {
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
        create_updateCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), new TRequestOption());
  }

  public void send_updateCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo,TRequestOption requestOption) throws TException { 
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
        create_updateCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), requestOption);
  }

  public long updateCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo, IMethodCallback<ContractDaoService.updateCommodityMapFileInfo_args, ContractDaoService.updateCommodityMapFileInfo_result> callback) throws TException{
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
            create_updateCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), callback);
  }

  public long add_updateCommodityMapFileInfoCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo, IMethodCallback<ContractDaoService.updateCommodityMapFileInfo_args, ContractDaoService.updateCommodityMapFileInfo_result> callback) throws TException{
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
            create_updateCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, mapFileInfo), callback);
  }

  protected TServiceCall create_updateCommodityMapFileInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateCommodityMapFileInfo_args request = new ContractDaoService.updateCommodityMapFileInfo_args();
    request.setPlatformArgs(platformArgs);
    request.setMapFileInfo(mapFileInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateCommodityMapFileInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateCommodityMapFileInfo_result.class);
    return serviceCall;
  }

  public void send_reqCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqCommodityMapFileInfo(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqCommodityMapFileInfo_args, ContractDaoService.reqCommodityMapFileInfo_result> callback) throws TException{
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
            create_reqCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqCommodityMapFileInfoCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqCommodityMapFileInfo_args, ContractDaoService.reqCommodityMapFileInfo_result> callback) throws TException{
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
            create_reqCommodityMapFileInfoServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqCommodityMapFileInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqCommodityMapFileInfo_args request = new ContractDaoService.reqCommodityMapFileInfo_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCommodityMapFileInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqCommodityMapFileInfo_result.class);
    return serviceCall;
  }

  public void send_reqSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSyncMappingTask_args, ContractDaoService.reqSyncMappingTask_result> callback) throws TException{
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
            create_reqSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqSyncMappingTaskCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSyncMappingTask_args, ContractDaoService.reqSyncMappingTask_result> callback) throws TException{
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
            create_reqSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqSyncMappingTaskServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSyncMappingTask_args request = new ContractDaoService.reqSyncMappingTask_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSyncMappingTask");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSyncMappingTask_result.class);
    return serviceCall;
  }

  public void send_addSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask) throws TException {
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
        create_addSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, tTask), new TRequestOption());
  }

  public void send_addSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask,TRequestOption requestOption) throws TException { 
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
        create_addSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, tTask), requestOption);
  }

  public long addSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask, IMethodCallback<ContractDaoService.addSyncMappingTask_args, ContractDaoService.addSyncMappingTask_result> callback) throws TException{
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
            create_addSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, tTask), callback);
  }

  public long add_addSyncMappingTaskCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask, IMethodCallback<ContractDaoService.addSyncMappingTask_args, ContractDaoService.addSyncMappingTask_result> callback) throws TException{
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
            create_addSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, tTask), callback);
  }

  protected TServiceCall create_addSyncMappingTaskServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addSyncMappingTask_args request = new ContractDaoService.addSyncMappingTask_args();
    request.setPlatformArgs(platformArgs);
    request.setTTask(tTask);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addSyncMappingTask");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addSyncMappingTask_result.class);
    return serviceCall;
  }

  public void send_removeSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option) throws TException {
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
        create_removeSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_removeSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option,TRequestOption requestOption) throws TException { 
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
        create_removeSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long removeSyncMappingTask(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, IMethodCallback<ContractDaoService.removeSyncMappingTask_args, ContractDaoService.removeSyncMappingTask_result> callback) throws TException{
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
            create_removeSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_removeSyncMappingTaskCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, IMethodCallback<ContractDaoService.removeSyncMappingTask_args, ContractDaoService.removeSyncMappingTask_result> callback) throws TException{
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
            create_removeSyncMappingTaskServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_removeSyncMappingTaskServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeSyncMappingTask_args request = new ContractDaoService.removeSyncMappingTask_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeSyncMappingTask");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeSyncMappingTask_result.class);
    return serviceCall;
  }

  public void send_addTechPlatformCommodity(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity) throws TException {
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
        create_addTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformCommodity), new TRequestOption());
  }

  public void send_addTechPlatformCommodity(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity,TRequestOption requestOption) throws TException { 
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
        create_addTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformCommodity), requestOption);
  }

  public long addTechPlatformCommodity(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity, IMethodCallback<ContractDaoService.addTechPlatformCommodity_args, ContractDaoService.addTechPlatformCommodity_result> callback) throws TException{
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
            create_addTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformCommodity), callback);
  }

  public long add_addTechPlatformCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity, IMethodCallback<ContractDaoService.addTechPlatformCommodity_args, ContractDaoService.addTechPlatformCommodity_result> callback) throws TException{
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
            create_addTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformCommodity), callback);
  }

  protected TServiceCall create_addTechPlatformCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addTechPlatformCommodity_args request = new ContractDaoService.addTechPlatformCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setTechPlatformCommodity(techPlatformCommodity);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addTechPlatformCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addTechPlatformCommodity_result.class);
    return serviceCall;
  }

  public void send_reqTechPlatformCommodity(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqTechPlatformCommodity(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqTechPlatformCommodity(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTechPlatformCommodity_args, ContractDaoService.reqTechPlatformCommodity_result> callback) throws TException{
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
            create_reqTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqTechPlatformCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqTechPlatformCommodity_args, ContractDaoService.reqTechPlatformCommodity_result> callback) throws TException{
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
            create_reqTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqTechPlatformCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqTechPlatformCommodity_args request = new ContractDaoService.reqTechPlatformCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqTechPlatformCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqTechPlatformCommodity_result.class);
    return serviceCall;
  }

  public void send_removeSledCommodity(int routeKey, int timeout, RemoveSledCommodityOption removeOption) throws TException {
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
        create_removeSledCommodityServiceCall(routeKey, timeout, platformArgs, removeOption), new TRequestOption());
  }

  public void send_removeSledCommodity(int routeKey, int timeout, RemoveSledCommodityOption removeOption,TRequestOption requestOption) throws TException { 
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
        create_removeSledCommodityServiceCall(routeKey, timeout, platformArgs, removeOption), requestOption);
  }

  public long removeSledCommodity(int routeKey, int timeout, RemoveSledCommodityOption removeOption, IMethodCallback<ContractDaoService.removeSledCommodity_args, ContractDaoService.removeSledCommodity_result> callback) throws TException{
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
            create_removeSledCommodityServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  public long add_removeSledCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, RemoveSledCommodityOption removeOption, IMethodCallback<ContractDaoService.removeSledCommodity_args, ContractDaoService.removeSledCommodity_result> callback) throws TException{
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
            create_removeSledCommodityServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  protected TServiceCall create_removeSledCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, RemoveSledCommodityOption removeOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeSledCommodity_args request = new ContractDaoService.removeSledCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setRemoveOption(removeOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeSledCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeSledCommodity_result.class);
    return serviceCall;
  }

  public void send_removeSledExchange(int routeKey, int timeout, RemoveSledExchangeOption removeOption) throws TException {
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
        create_removeSledExchangeServiceCall(routeKey, timeout, platformArgs, removeOption), new TRequestOption());
  }

  public void send_removeSledExchange(int routeKey, int timeout, RemoveSledExchangeOption removeOption,TRequestOption requestOption) throws TException { 
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
        create_removeSledExchangeServiceCall(routeKey, timeout, platformArgs, removeOption), requestOption);
  }

  public long removeSledExchange(int routeKey, int timeout, RemoveSledExchangeOption removeOption, IMethodCallback<ContractDaoService.removeSledExchange_args, ContractDaoService.removeSledExchange_result> callback) throws TException{
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
            create_removeSledExchangeServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  public long add_removeSledExchangeCall(AsyncCallRunner runner, int routeKey, int timeout, RemoveSledExchangeOption removeOption, IMethodCallback<ContractDaoService.removeSledExchange_args, ContractDaoService.removeSledExchange_result> callback) throws TException{
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
            create_removeSledExchangeServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  protected TServiceCall create_removeSledExchangeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, RemoveSledExchangeOption removeOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeSledExchange_args request = new ContractDaoService.removeSledExchange_args();
    request.setPlatformArgs(platformArgs);
    request.setRemoveOption(removeOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeSledExchange");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeSledExchange_result.class);
    return serviceCall;
  }

  public void send_addContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion) throws TException {
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
        create_addContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), new TRequestOption());
  }

  public void send_addContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion,TRequestOption requestOption) throws TException { 
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
        create_addContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), requestOption);
  }

  public long addContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion, IMethodCallback<ContractDaoService.addContractVersion_args, ContractDaoService.addContractVersion_result> callback) throws TException{
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
            create_addContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), callback);
  }

  public long add_addContractVersionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion, IMethodCallback<ContractDaoService.addContractVersion_args, ContractDaoService.addContractVersion_result> callback) throws TException{
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
            create_addContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), callback);
  }

  protected TServiceCall create_addContractVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addContractVersion_args request = new ContractDaoService.addContractVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setContractVersion(contractVersion);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addContractVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addContractVersion_result.class);
    return serviceCall;
  }

  public void send_removeContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption) throws TException {
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
        create_removeContractVersionServiceCall(routeKey, timeout, platformArgs, removeOption), new TRequestOption());
  }

  public void send_removeContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption,TRequestOption requestOption) throws TException { 
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
        create_removeContractVersionServiceCall(routeKey, timeout, platformArgs, removeOption), requestOption);
  }

  public long removeContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption, IMethodCallback<ContractDaoService.removeContractVersion_args, ContractDaoService.removeContractVersion_result> callback) throws TException{
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
            create_removeContractVersionServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  public long add_removeContractVersionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption, IMethodCallback<ContractDaoService.removeContractVersion_args, ContractDaoService.removeContractVersion_result> callback) throws TException{
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
            create_removeContractVersionServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  protected TServiceCall create_removeContractVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeContractVersion_args request = new ContractDaoService.removeContractVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setRemoveOption(removeOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeContractVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeContractVersion_result.class);
    return serviceCall;
  }

  public void send_reqContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqContractVersionServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqContractVersionServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqContractVersion_args, ContractDaoService.reqContractVersion_result> callback) throws TException{
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
            create_reqContractVersionServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqContractVersionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqContractVersion_args, ContractDaoService.reqContractVersion_result> callback) throws TException{
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
            create_reqContractVersionServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqContractVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqContractVersion_args request = new ContractDaoService.reqContractVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqContractVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqContractVersion_result.class);
    return serviceCall;
  }

  public void send_updateContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion) throws TException {
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
        create_updateContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), new TRequestOption());
  }

  public void send_updateContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion,TRequestOption requestOption) throws TException { 
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
        create_updateContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), requestOption);
  }

  public long updateContractVersion(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion, IMethodCallback<ContractDaoService.updateContractVersion_args, ContractDaoService.updateContractVersion_result> callback) throws TException{
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
            create_updateContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), callback);
  }

  public long add_updateContractVersionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion, IMethodCallback<ContractDaoService.updateContractVersion_args, ContractDaoService.updateContractVersion_result> callback) throws TException{
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
            create_updateContractVersionServiceCall(routeKey, timeout, platformArgs, contractVersion), callback);
  }

  protected TServiceCall create_updateContractVersionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateContractVersion_args request = new ContractDaoService.updateContractVersion_args();
    request.setPlatformArgs(platformArgs);
    request.setContractVersion(contractVersion);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateContractVersion");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateContractVersion_result.class);
    return serviceCall;
  }

  public void send_addDbLocking(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo) throws TException {
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
        create_addDbLockingServiceCall(routeKey, timeout, platformArgs, dbLockingInfo), new TRequestOption());
  }

  public void send_addDbLocking(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo,TRequestOption requestOption) throws TException { 
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
        create_addDbLockingServiceCall(routeKey, timeout, platformArgs, dbLockingInfo), requestOption);
  }

  public long addDbLocking(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo, IMethodCallback<ContractDaoService.addDbLocking_args, ContractDaoService.addDbLocking_result> callback) throws TException{
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
            create_addDbLockingServiceCall(routeKey, timeout, platformArgs, dbLockingInfo), callback);
  }

  public long add_addDbLockingCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo, IMethodCallback<ContractDaoService.addDbLocking_args, ContractDaoService.addDbLocking_result> callback) throws TException{
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
            create_addDbLockingServiceCall(routeKey, timeout, platformArgs, dbLockingInfo), callback);
  }

  protected TServiceCall create_addDbLockingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addDbLocking_args request = new ContractDaoService.addDbLocking_args();
    request.setPlatformArgs(platformArgs);
    request.setDbLockingInfo(dbLockingInfo);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addDbLocking");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addDbLocking_result.class);
    return serviceCall;
  }

  public void send_removeDbLocking(int routeKey, int timeout, String lockedBy) throws TException {
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
        create_removeDbLockingServiceCall(routeKey, timeout, platformArgs, lockedBy), new TRequestOption());
  }

  public void send_removeDbLocking(int routeKey, int timeout, String lockedBy,TRequestOption requestOption) throws TException { 
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
        create_removeDbLockingServiceCall(routeKey, timeout, platformArgs, lockedBy), requestOption);
  }

  public long removeDbLocking(int routeKey, int timeout, String lockedBy, IMethodCallback<ContractDaoService.removeDbLocking_args, ContractDaoService.removeDbLocking_result> callback) throws TException{
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
            create_removeDbLockingServiceCall(routeKey, timeout, platformArgs, lockedBy), callback);
  }

  public long add_removeDbLockingCall(AsyncCallRunner runner, int routeKey, int timeout, String lockedBy, IMethodCallback<ContractDaoService.removeDbLocking_args, ContractDaoService.removeDbLocking_result> callback) throws TException{
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
            create_removeDbLockingServiceCall(routeKey, timeout, platformArgs, lockedBy), callback);
  }

  protected TServiceCall create_removeDbLockingServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, String lockedBy){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeDbLocking_args request = new ContractDaoService.removeDbLocking_args();
    request.setPlatformArgs(platformArgs);
    request.setLockedBy(lockedBy);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeDbLocking");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeDbLocking_result.class);
    return serviceCall;
  }

  public void send_reqDbLockingInfo(int routeKey, int timeout) throws TException {
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
        create_reqDbLockingInfoServiceCall(routeKey, timeout, platformArgs), new TRequestOption());
  }

  public void send_reqDbLockingInfo(int routeKey, int timeout,TRequestOption requestOption) throws TException { 
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
        create_reqDbLockingInfoServiceCall(routeKey, timeout, platformArgs), requestOption);
  }

  public long reqDbLockingInfo(int routeKey, int timeout, IMethodCallback<ContractDaoService.reqDbLockingInfo_args, ContractDaoService.reqDbLockingInfo_result> callback) throws TException{
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
            create_reqDbLockingInfoServiceCall(routeKey, timeout, platformArgs), callback);
  }

  public long add_reqDbLockingInfoCall(AsyncCallRunner runner, int routeKey, int timeout, IMethodCallback<ContractDaoService.reqDbLockingInfo_args, ContractDaoService.reqDbLockingInfo_result> callback) throws TException{
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
            create_reqDbLockingInfoServiceCall(routeKey, timeout, platformArgs), callback);
  }

  protected TServiceCall create_reqDbLockingInfoServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqDbLockingInfo_args request = new ContractDaoService.reqDbLockingInfo_args();
    request.setPlatformArgs(platformArgs);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqDbLockingInfo");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqDbLockingInfo_result.class);
    return serviceCall;
  }

  public void send_addSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config) throws TException {
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
        create_addSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), new TRequestOption());
  }

  public void send_addSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config,TRequestOption requestOption) throws TException { 
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
        create_addSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), requestOption);
  }

  public long addSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config, IMethodCallback<ContractDaoService.addSledTradeTimeConfig_args, ContractDaoService.addSledTradeTimeConfig_result> callback) throws TException{
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
            create_addSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), callback);
  }

  public long add_addSledTradeTimeConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config, IMethodCallback<ContractDaoService.addSledTradeTimeConfig_args, ContractDaoService.addSledTradeTimeConfig_result> callback) throws TException{
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
            create_addSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), callback);
  }

  protected TServiceCall create_addSledTradeTimeConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addSledTradeTimeConfig_args request = new ContractDaoService.addSledTradeTimeConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setConfig(config);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addSledTradeTimeConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addSledTradeTimeConfig_result.class);
    return serviceCall;
  }

  public void send_updateSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config) throws TException {
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
        create_updateSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), new TRequestOption());
  }

  public void send_updateSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config,TRequestOption requestOption) throws TException { 
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
        create_updateSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), requestOption);
  }

  public long updateSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config, IMethodCallback<ContractDaoService.updateSledTradeTimeConfig_args, ContractDaoService.updateSledTradeTimeConfig_result> callback) throws TException{
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
            create_updateSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), callback);
  }

  public long add_updateSledTradeTimeConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config, IMethodCallback<ContractDaoService.updateSledTradeTimeConfig_args, ContractDaoService.updateSledTradeTimeConfig_result> callback) throws TException{
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
            create_updateSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, config), callback);
  }

  protected TServiceCall create_updateSledTradeTimeConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateSledTradeTimeConfig_args request = new ContractDaoService.updateSledTradeTimeConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setConfig(config);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateSledTradeTimeConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateSledTradeTimeConfig_result.class);
    return serviceCall;
  }

  public void send_reqSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqSledTradeTimeConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSledTradeTimeConfig_args, ContractDaoService.reqSledTradeTimeConfig_result> callback) throws TException{
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
            create_reqSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqSledTradeTimeConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSledTradeTimeConfig_args, ContractDaoService.reqSledTradeTimeConfig_result> callback) throws TException{
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
            create_reqSledTradeTimeConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqSledTradeTimeConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSledTradeTimeConfig_args request = new ContractDaoService.reqSledTradeTimeConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSledTradeTimeConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSledTradeTimeConfig_result.class);
    return serviceCall;
  }

  public void send_addSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime) throws TException {
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
        create_addSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), new TRequestOption());
  }

  public void send_addSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime,TRequestOption requestOption) throws TException { 
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
        create_addSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), requestOption);
  }

  public long addSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime, IMethodCallback<ContractDaoService.addSpecTradeTime_args, ContractDaoService.addSpecTradeTime_result> callback) throws TException{
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
            create_addSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), callback);
  }

  public long add_addSpecTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime, IMethodCallback<ContractDaoService.addSpecTradeTime_args, ContractDaoService.addSpecTradeTime_result> callback) throws TException{
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
            create_addSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), callback);
  }

  protected TServiceCall create_addSpecTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addSpecTradeTime_args request = new ContractDaoService.addSpecTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setSpecTradeTime(specTradeTime);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addSpecTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addSpecTradeTime_result.class);
    return serviceCall;
  }

  public void send_updateSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime) throws TException {
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
        create_updateSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), new TRequestOption());
  }

  public void send_updateSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime,TRequestOption requestOption) throws TException { 
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
        create_updateSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), requestOption);
  }

  public long updateSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime, IMethodCallback<ContractDaoService.updateSpecTradeTime_args, ContractDaoService.updateSpecTradeTime_result> callback) throws TException{
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
            create_updateSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), callback);
  }

  public long add_updateSpecTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime, IMethodCallback<ContractDaoService.updateSpecTradeTime_args, ContractDaoService.updateSpecTradeTime_result> callback) throws TException{
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
            create_updateSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, specTradeTime), callback);
  }

  protected TServiceCall create_updateSpecTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateSpecTradeTime_args request = new ContractDaoService.updateSpecTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setSpecTradeTime(specTradeTime);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateSpecTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateSpecTradeTime_result.class);
    return serviceCall;
  }

  public void send_reqSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSpecTradeTime_args, ContractDaoService.reqSpecTradeTime_result> callback) throws TException{
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
            create_reqSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqSpecTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSpecTradeTime_args, ContractDaoService.reqSpecTradeTime_result> callback) throws TException{
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
            create_reqSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqSpecTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSpecTradeTime_args request = new ContractDaoService.reqSpecTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSpecTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSpecTradeTime_result.class);
    return serviceCall;
  }

  public void send_reqSledCommoditySpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqSledCommoditySpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqSledCommoditySpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqSledCommoditySpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqSledCommoditySpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSledCommoditySpecTradeTime_args, ContractDaoService.reqSledCommoditySpecTradeTime_result> callback) throws TException{
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
            create_reqSledCommoditySpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqSledCommoditySpecTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSledCommoditySpecTradeTime_args, ContractDaoService.reqSledCommoditySpecTradeTime_result> callback) throws TException{
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
            create_reqSledCommoditySpecTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqSledCommoditySpecTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSledCommoditySpecTradeTime_args request = new ContractDaoService.reqSledCommoditySpecTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSledCommoditySpecTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSledCommoditySpecTradeTime_result.class);
    return serviceCall;
  }

  public void send_reqSledTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqSledTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqSledTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqSledTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqSledTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSledTradeTime_args, ContractDaoService.reqSledTradeTime_result> callback) throws TException{
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
            create_reqSledTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqSledTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqSledTradeTime_args, ContractDaoService.reqSledTradeTime_result> callback) throws TException{
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
            create_reqSledTradeTimeServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqSledTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSledTradeTime_args request = new ContractDaoService.reqSledTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSledTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSledTradeTime_result.class);
    return serviceCall;
  }

  public void send_batAddSledTradeTime(int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes) throws TException {
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
        create_batAddSledTradeTimeServiceCall(routeKey, timeout, platformArgs, sledTradeTimes), new TRequestOption());
  }

  public void send_batAddSledTradeTime(int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes,TRequestOption requestOption) throws TException { 
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
        create_batAddSledTradeTimeServiceCall(routeKey, timeout, platformArgs, sledTradeTimes), requestOption);
  }

  public long batAddSledTradeTime(int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes, IMethodCallback<ContractDaoService.batAddSledTradeTime_args, ContractDaoService.batAddSledTradeTime_result> callback) throws TException{
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
            create_batAddSledTradeTimeServiceCall(routeKey, timeout, platformArgs, sledTradeTimes), callback);
  }

  public long add_batAddSledTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes, IMethodCallback<ContractDaoService.batAddSledTradeTime_args, ContractDaoService.batAddSledTradeTime_result> callback) throws TException{
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
            create_batAddSledTradeTimeServiceCall(routeKey, timeout, platformArgs, sledTradeTimes), callback);
  }

  protected TServiceCall create_batAddSledTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.batAddSledTradeTime_args request = new ContractDaoService.batAddSledTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setSledTradeTimes(sledTradeTimes);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("batAddSledTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.batAddSledTradeTime_result.class);
    return serviceCall;
  }

  public void send_addDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig) throws TException {
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
        create_addDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), new TRequestOption());
  }

  public void send_addDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig,TRequestOption requestOption) throws TException { 
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
        create_addDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), requestOption);
  }

  public long addDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig, IMethodCallback<ContractDaoService.addDstTransferConfig_args, ContractDaoService.addDstTransferConfig_result> callback) throws TException{
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
            create_addDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), callback);
  }

  public long add_addDstTransferConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig, IMethodCallback<ContractDaoService.addDstTransferConfig_args, ContractDaoService.addDstTransferConfig_result> callback) throws TException{
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
            create_addDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), callback);
  }

  protected TServiceCall create_addDstTransferConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addDstTransferConfig_args request = new ContractDaoService.addDstTransferConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setTransferConfig(transferConfig);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addDstTransferConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addDstTransferConfig_result.class);
    return serviceCall;
  }

  public void send_updateDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig) throws TException {
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
        create_updateDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), new TRequestOption());
  }

  public void send_updateDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig,TRequestOption requestOption) throws TException { 
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
        create_updateDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), requestOption);
  }

  public long updateDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig, IMethodCallback<ContractDaoService.updateDstTransferConfig_args, ContractDaoService.updateDstTransferConfig_result> callback) throws TException{
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
            create_updateDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), callback);
  }

  public long add_updateDstTransferConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig, IMethodCallback<ContractDaoService.updateDstTransferConfig_args, ContractDaoService.updateDstTransferConfig_result> callback) throws TException{
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
            create_updateDstTransferConfigServiceCall(routeKey, timeout, platformArgs, transferConfig), callback);
  }

  protected TServiceCall create_updateDstTransferConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateDstTransferConfig_args request = new ContractDaoService.updateDstTransferConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setTransferConfig(transferConfig);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateDstTransferConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateDstTransferConfig_result.class);
    return serviceCall;
  }

  public void send_reqDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize) throws TException {
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
        create_reqDstTransferConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), new TRequestOption());
  }

  public void send_reqDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize,TRequestOption requestOption) throws TException { 
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
        create_reqDstTransferConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), requestOption);
  }

  public long reqDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqDstTransferConfig_args, ContractDaoService.reqDstTransferConfig_result> callback) throws TException{
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
            create_reqDstTransferConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  public long add_reqDstTransferConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize, IMethodCallback<ContractDaoService.reqDstTransferConfig_args, ContractDaoService.reqDstTransferConfig_result> callback) throws TException{
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
            create_reqDstTransferConfigServiceCall(routeKey, timeout, platformArgs, option, pageIndex, pageSize), callback);
  }

  protected TServiceCall create_reqDstTransferConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqDstTransferConfig_args request = new ContractDaoService.reqDstTransferConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageIndex(pageIndex);
    request.setPageSize(pageSize);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqDstTransferConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqDstTransferConfig_result.class);
    return serviceCall;
  }

  public void send_removeSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption) throws TException {
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
        create_removeSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, removeOption), new TRequestOption());
  }

  public void send_removeSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption,TRequestOption requestOption) throws TException { 
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
        create_removeSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, removeOption), requestOption);
  }

  public long removeSpecTradeTime(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption, IMethodCallback<ContractDaoService.removeSpecTradeTime_args, ContractDaoService.removeSpecTradeTime_result> callback) throws TException{
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
            create_removeSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  public long add_removeSpecTradeTimeCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption, IMethodCallback<ContractDaoService.removeSpecTradeTime_args, ContractDaoService.removeSpecTradeTime_result> callback) throws TException{
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
            create_removeSpecTradeTimeServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  protected TServiceCall create_removeSpecTradeTimeServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeSpecTradeTime_args request = new ContractDaoService.removeSpecTradeTime_args();
    request.setPlatformArgs(platformArgs);
    request.setRemoveOption(removeOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeSpecTradeTime");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeSpecTradeTime_result.class);
    return serviceCall;
  }

  public void send_removeDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption) throws TException {
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
        create_removeDstTransferConfigServiceCall(routeKey, timeout, platformArgs, removeOption), new TRequestOption());
  }

  public void send_removeDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption,TRequestOption requestOption) throws TException { 
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
        create_removeDstTransferConfigServiceCall(routeKey, timeout, platformArgs, removeOption), requestOption);
  }

  public long removeDstTransferConfig(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption, IMethodCallback<ContractDaoService.removeDstTransferConfig_args, ContractDaoService.removeDstTransferConfig_result> callback) throws TException{
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
            create_removeDstTransferConfigServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  public long add_removeDstTransferConfigCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption, IMethodCallback<ContractDaoService.removeDstTransferConfig_args, ContractDaoService.removeDstTransferConfig_result> callback) throws TException{
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
            create_removeDstTransferConfigServiceCall(routeKey, timeout, platformArgs, removeOption), callback);
  }

  protected TServiceCall create_removeDstTransferConfigServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeDstTransferConfig_args request = new ContractDaoService.removeDstTransferConfig_args();
    request.setPlatformArgs(platformArgs);
    request.setRemoveOption(removeOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeDstTransferConfig");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeDstTransferConfig_result.class);
    return serviceCall;
  }

  public void send_batUpdateSledTradeTimeConfigs(int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs) throws TException {
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
        create_batUpdateSledTradeTimeConfigsServiceCall(routeKey, timeout, platformArgs, configs), new TRequestOption());
  }

  public void send_batUpdateSledTradeTimeConfigs(int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs,TRequestOption requestOption) throws TException { 
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
        create_batUpdateSledTradeTimeConfigsServiceCall(routeKey, timeout, platformArgs, configs), requestOption);
  }

  public long batUpdateSledTradeTimeConfigs(int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs, IMethodCallback<ContractDaoService.batUpdateSledTradeTimeConfigs_args, ContractDaoService.batUpdateSledTradeTimeConfigs_result> callback) throws TException{
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
            create_batUpdateSledTradeTimeConfigsServiceCall(routeKey, timeout, platformArgs, configs), callback);
  }

  public long add_batUpdateSledTradeTimeConfigsCall(AsyncCallRunner runner, int routeKey, int timeout, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs, IMethodCallback<ContractDaoService.batUpdateSledTradeTimeConfigs_args, ContractDaoService.batUpdateSledTradeTimeConfigs_result> callback) throws TException{
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
            create_batUpdateSledTradeTimeConfigsServiceCall(routeKey, timeout, platformArgs, configs), callback);
  }

  protected TServiceCall create_batUpdateSledTradeTimeConfigsServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.batUpdateSledTradeTimeConfigs_args request = new ContractDaoService.batUpdateSledTradeTimeConfigs_args();
    request.setPlatformArgs(platformArgs);
    request.setConfigs(configs);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("batUpdateSledTradeTimeConfigs");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.batUpdateSledTradeTimeConfigs_result.class);
    return serviceCall;
  }

  public void send_addCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource) throws TException {
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
        create_addCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), new TRequestOption());
  }

  public void send_addCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource,TRequestOption requestOption) throws TException { 
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
        create_addCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), requestOption);
  }

  public long addCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource, IMethodCallback<ContractDaoService.addCommoditySource_args, ContractDaoService.addCommoditySource_result> callback) throws TException{
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
            create_addCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), callback);
  }

  public long add_addCommoditySourceCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource, IMethodCallback<ContractDaoService.addCommoditySource_args, ContractDaoService.addCommoditySource_result> callback) throws TException{
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
            create_addCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), callback);
  }

  protected TServiceCall create_addCommoditySourceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addCommoditySource_args request = new ContractDaoService.addCommoditySource_args();
    request.setPlatformArgs(platformArgs);
    request.setCommoditySource(commoditySource);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addCommoditySource");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addCommoditySource_result.class);
    return serviceCall;
  }

  public void send_updateCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource) throws TException {
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
        create_updateCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), new TRequestOption());
  }

  public void send_updateCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource,TRequestOption requestOption) throws TException { 
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
        create_updateCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), requestOption);
  }

  public long updateCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource, IMethodCallback<ContractDaoService.updateCommoditySource_args, ContractDaoService.updateCommoditySource_result> callback) throws TException{
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
            create_updateCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), callback);
  }

  public long add_updateCommoditySourceCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource, IMethodCallback<ContractDaoService.updateCommoditySource_args, ContractDaoService.updateCommoditySource_result> callback) throws TException{
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
            create_updateCommoditySourceServiceCall(routeKey, timeout, platformArgs, commoditySource), callback);
  }

  protected TServiceCall create_updateCommoditySourceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateCommoditySource_args request = new ContractDaoService.updateCommoditySource_args();
    request.setPlatformArgs(platformArgs);
    request.setCommoditySource(commoditySource);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateCommoditySource");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateCommoditySource_result.class);
    return serviceCall;
  }

  public void send_reqCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option) throws TException {
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
        create_reqCommoditySourceServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option,TRequestOption requestOption) throws TException { 
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
        create_reqCommoditySourceServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqCommoditySource(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option, IMethodCallback<ContractDaoService.reqCommoditySource_args, ContractDaoService.reqCommoditySource_result> callback) throws TException{
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
            create_reqCommoditySourceServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqCommoditySourceCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option, IMethodCallback<ContractDaoService.reqCommoditySource_args, ContractDaoService.reqCommoditySource_result> callback) throws TException{
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
            create_reqCommoditySourceServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqCommoditySourceServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqCommoditySource_args request = new ContractDaoService.reqCommoditySource_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCommoditySource");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqCommoditySource_result.class);
    return serviceCall;
  }

  public void send_addCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount) throws TException {
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
        create_addCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), new TRequestOption());
  }

  public void send_addCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount,TRequestOption requestOption) throws TException { 
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
        create_addCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), requestOption);
  }

  public long addCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount, IMethodCallback<ContractDaoService.addCommoditySourceAccount_args, ContractDaoService.addCommoditySourceAccount_result> callback) throws TException{
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
            create_addCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), callback);
  }

  public long add_addCommoditySourceAccountCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount, IMethodCallback<ContractDaoService.addCommoditySourceAccount_args, ContractDaoService.addCommoditySourceAccount_result> callback) throws TException{
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
            create_addCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), callback);
  }

  protected TServiceCall create_addCommoditySourceAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addCommoditySourceAccount_args request = new ContractDaoService.addCommoditySourceAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setCommoditySourceAccount(commoditySourceAccount);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addCommoditySourceAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addCommoditySourceAccount_result.class);
    return serviceCall;
  }

  public void send_updateCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount) throws TException {
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
        create_updateCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), new TRequestOption());
  }

  public void send_updateCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount,TRequestOption requestOption) throws TException { 
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
        create_updateCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), requestOption);
  }

  public long updateCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount, IMethodCallback<ContractDaoService.updateCommoditySourceAccount_args, ContractDaoService.updateCommoditySourceAccount_result> callback) throws TException{
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
            create_updateCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), callback);
  }

  public long add_updateCommoditySourceAccountCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount, IMethodCallback<ContractDaoService.updateCommoditySourceAccount_args, ContractDaoService.updateCommoditySourceAccount_result> callback) throws TException{
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
            create_updateCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, commoditySourceAccount), callback);
  }

  protected TServiceCall create_updateCommoditySourceAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateCommoditySourceAccount_args request = new ContractDaoService.updateCommoditySourceAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setCommoditySourceAccount(commoditySourceAccount);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateCommoditySourceAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateCommoditySourceAccount_result.class);
    return serviceCall;
  }

  public void send_reqCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option) throws TException {
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
        create_reqCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, option), new TRequestOption());
  }

  public void send_reqCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option,TRequestOption requestOption) throws TException { 
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
        create_reqCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, option), requestOption);
  }

  public long reqCommoditySourceAccount(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option, IMethodCallback<ContractDaoService.reqCommoditySourceAccount_args, ContractDaoService.reqCommoditySourceAccount_result> callback) throws TException{
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
            create_reqCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  public long add_reqCommoditySourceAccountCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option, IMethodCallback<ContractDaoService.reqCommoditySourceAccount_args, ContractDaoService.reqCommoditySourceAccount_result> callback) throws TException{
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
            create_reqCommoditySourceAccountServiceCall(routeKey, timeout, platformArgs, option), callback);
  }

  protected TServiceCall create_reqCommoditySourceAccountServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqCommoditySourceAccount_args request = new ContractDaoService.reqCommoditySourceAccount_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqCommoditySourceAccount");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqCommoditySourceAccount_result.class);
    return serviceCall;
  }

  public void send_addSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession) throws TException {
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
        create_addSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), new TRequestOption());
  }

  public void send_addSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession,TRequestOption requestOption) throws TException { 
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
        create_addSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), requestOption);
  }

  public long addSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession, IMethodCallback<ContractDaoService.addSledTradingSession_args, ContractDaoService.addSledTradingSession_result> callback) throws TException{
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
            create_addSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), callback);
  }

  public long add_addSledTradingSessionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession, IMethodCallback<ContractDaoService.addSledTradingSession_args, ContractDaoService.addSledTradingSession_result> callback) throws TException{
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
            create_addSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), callback);
  }

  protected TServiceCall create_addSledTradingSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.addSledTradingSession_args request = new ContractDaoService.addSledTradingSession_args();
    request.setPlatformArgs(platformArgs);
    request.setSledTradingSession(sledTradingSession);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("addSledTradingSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.addSledTradingSession_result.class);
    return serviceCall;
  }

  public void send_updateSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession) throws TException {
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
        create_updateSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), new TRequestOption());
  }

  public void send_updateSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession,TRequestOption requestOption) throws TException { 
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
        create_updateSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), requestOption);
  }

  public long updateSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession, IMethodCallback<ContractDaoService.updateSledTradingSession_args, ContractDaoService.updateSledTradingSession_result> callback) throws TException{
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
            create_updateSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), callback);
  }

  public long add_updateSledTradingSessionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession, IMethodCallback<ContractDaoService.updateSledTradingSession_args, ContractDaoService.updateSledTradingSession_result> callback) throws TException{
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
            create_updateSledTradingSessionServiceCall(routeKey, timeout, platformArgs, sledTradingSession), callback);
  }

  protected TServiceCall create_updateSledTradingSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.updateSledTradingSession_args request = new ContractDaoService.updateSledTradingSession_args();
    request.setPlatformArgs(platformArgs);
    request.setSledTradingSession(sledTradingSession);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("updateSledTradingSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.updateSledTradingSession_result.class);
    return serviceCall;
  }

  public void send_reqSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws TException {
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
        create_reqSledTradingSessionServiceCall(routeKey, timeout, platformArgs, option, pageOption), new TRequestOption());
  }

  public void send_reqSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption,TRequestOption requestOption) throws TException { 
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
        create_reqSledTradingSessionServiceCall(routeKey, timeout, platformArgs, option, pageOption), requestOption);
  }

  public long reqSledTradingSession(int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<ContractDaoService.reqSledTradingSession_args, ContractDaoService.reqSledTradingSession_result> callback) throws TException{
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
            create_reqSledTradingSessionServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  public long add_reqSledTradingSessionCall(AsyncCallRunner runner, int routeKey, int timeout, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption, IMethodCallback<ContractDaoService.reqSledTradingSession_args, ContractDaoService.reqSledTradingSession_result> callback) throws TException{
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
            create_reqSledTradingSessionServiceCall(routeKey, timeout, platformArgs, option, pageOption), callback);
  }

  protected TServiceCall create_reqSledTradingSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.reqSledTradingSession_args request = new ContractDaoService.reqSledTradingSession_args();
    request.setPlatformArgs(platformArgs);
    request.setOption(option);
    request.setPageOption(pageOption);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("reqSledTradingSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.reqSledTradingSession_result.class);
    return serviceCall;
  }

  public void send_removeSledTradingSession(int routeKey, int timeout, long tradeSessionId) throws TException {
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
        create_removeSledTradingSessionServiceCall(routeKey, timeout, platformArgs, tradeSessionId), new TRequestOption());
  }

  public void send_removeSledTradingSession(int routeKey, int timeout, long tradeSessionId,TRequestOption requestOption) throws TException { 
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
        create_removeSledTradingSessionServiceCall(routeKey, timeout, platformArgs, tradeSessionId), requestOption);
  }

  public long removeSledTradingSession(int routeKey, int timeout, long tradeSessionId, IMethodCallback<ContractDaoService.removeSledTradingSession_args, ContractDaoService.removeSledTradingSession_result> callback) throws TException{
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
            create_removeSledTradingSessionServiceCall(routeKey, timeout, platformArgs, tradeSessionId), callback);
  }

  public long add_removeSledTradingSessionCall(AsyncCallRunner runner, int routeKey, int timeout, long tradeSessionId, IMethodCallback<ContractDaoService.removeSledTradingSession_args, ContractDaoService.removeSledTradingSession_result> callback) throws TException{
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
            create_removeSledTradingSessionServiceCall(routeKey, timeout, platformArgs, tradeSessionId), callback);
  }

  protected TServiceCall create_removeSledTradingSessionServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, long tradeSessionId){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.removeSledTradingSession_args request = new ContractDaoService.removeSledTradingSession_args();
    request.setPlatformArgs(platformArgs);
    request.setTradeSessionId(tradeSessionId);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("removeSledTradingSession");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.removeSledTradingSession_result.class);
    return serviceCall;
  }

  public void send_clearAllTechPlatformCommodity(int routeKey, int timeout, int techPlatformValue) throws TException {
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
        create_clearAllTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformValue), new TRequestOption());
  }

  public void send_clearAllTechPlatformCommodity(int routeKey, int timeout, int techPlatformValue,TRequestOption requestOption) throws TException { 
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
        create_clearAllTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformValue), requestOption);
  }

  public long clearAllTechPlatformCommodity(int routeKey, int timeout, int techPlatformValue, IMethodCallback<ContractDaoService.clearAllTechPlatformCommodity_args, ContractDaoService.clearAllTechPlatformCommodity_result> callback) throws TException{
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
            create_clearAllTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformValue), callback);
  }

  public long add_clearAllTechPlatformCommodityCall(AsyncCallRunner runner, int routeKey, int timeout, int techPlatformValue, IMethodCallback<ContractDaoService.clearAllTechPlatformCommodity_args, ContractDaoService.clearAllTechPlatformCommodity_result> callback) throws TException{
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
            create_clearAllTechPlatformCommodityServiceCall(routeKey, timeout, platformArgs, techPlatformValue), callback);
  }

  protected TServiceCall create_clearAllTechPlatformCommodityServiceCall(int routeKey, int timeout, org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, int techPlatformValue){
    TServiceCall serviceCall = new TServiceCall();
    if (!getPeerAddr().isEmpty()) {
      serviceCall.setChooseServiceIp(getPeerAddr());
    }
    if (getPeerPort() != 0) {
      serviceCall.setChooseServicePort(getPeerPort());
    }
    serviceCall.setServiceKey(ContractDaoServiceVariable.serviceKey);
    serviceCall.setRouteKey(routeKey);
    serviceCall.setOneWay(false);
    ContractDaoService.clearAllTechPlatformCommodity_args request = new ContractDaoService.clearAllTechPlatformCommodity_args();
    request.setPlatformArgs(platformArgs);
    request.setTechPlatformValue(techPlatformValue);
    serviceCall.setRequest(request);
    serviceCall.setMethodName("clearAllTechPlatformCommodity");
    serviceCall.setTimeout(timeout);
    serviceCall.setResponse(ContractDaoService.clearAllTechPlatformCommodity_result.class);
    return serviceCall;
  }

}
