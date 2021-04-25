package com.longsheng.xueqiao.contract.dao.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
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
import com.longsheng.xueqiao.contract.dao.thriftapi.ContractDaoService;
import com.longsheng.xueqiao.contract.dao.thriftapi.ContractDaoServiceVariable;

public class ContractDaoServiceStub extends BaseStub {

  public ContractDaoServiceStub() {
    super(ContractDaoServiceVariable.serviceKey);
  }

  public int  addTSledCommodity(TSledCommodity tSledCommodity) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledCommodity(tSledCommodity, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledCommodity(TSledCommodity tSledCommodity,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addTSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).addTSledCommodity(platformArgs, tSledCommodity);
      }
    }, invokeInfo);
  }

  public int  addTSledCommodity(int routeKey, int timeout,TSledCommodity tSledCommodity)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledCommodity(tSledCommodity, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTSledCommodity(TSledCommodity tSledCommodity) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTSledCommodity(tSledCommodity, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTSledCommodity(TSledCommodity tSledCommodity,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateTSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).updateTSledCommodity(platformArgs, tSledCommodity);
      }
    }, invokeInfo);
  }

  public int  updateTSledCommodity(int routeKey, int timeout,TSledCommodity tSledCommodity)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTSledCommodity(tSledCommodity, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TCommodityPage  reqTSledCommodity(ReqTSledCommodityOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledCommodity(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TCommodityPage  reqTSledCommodity(ReqTSledCommodityOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqTSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<TCommodityPage>(){
    @Override
    public TCommodityPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqTSledCommodity(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public TCommodityPage  reqTSledCommodity(int routeKey, int timeout,ReqTSledCommodityOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledCommodity(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledContract(TSledContract tSledContract) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledContract(tSledContract, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledContract(TSledContract tSledContract,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addTSledContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).addTSledContract(platformArgs, tSledContract);
      }
    }, invokeInfo);
  }

  public int  addTSledContract(int routeKey, int timeout,TSledContract tSledContract)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledContract(tSledContract, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTSledContract(TSledContract tSledContract) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTSledContract(tSledContract, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTSledContract(TSledContract tSledContract,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateTSledContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).updateTSledContract(platformArgs, tSledContract);
      }
    }, invokeInfo);
  }

  public int  updateTSledContract(int routeKey, int timeout,TSledContract tSledContract)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTSledContract(tSledContract, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TSledContractPage  reqTSledContract(ReqTSledContractOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledContract(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TSledContractPage  reqTSledContract(ReqTSledContractOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqTSledContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<TSledContractPage>(){
    @Override
    public TSledContractPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqTSledContract(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public TSledContractPage  reqTSledContract(int routeKey, int timeout,ReqTSledContractOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledContract(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledExchange(TSledExchange tSledExchange) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledExchange(tSledExchange, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledExchange(TSledExchange tSledExchange,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addTSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).addTSledExchange(platformArgs, tSledExchange);
      }
    }, invokeInfo);
  }

  public int  addTSledExchange(int routeKey, int timeout,TSledExchange tSledExchange)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledExchange(tSledExchange, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTSledExchange(TSledExchange tSledExchange) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTSledExchange(tSledExchange, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTSledExchange(TSledExchange tSledExchange,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateTSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).updateTSledExchange(platformArgs, tSledExchange);
      }
    }, invokeInfo);
  }

  public int  updateTSledExchange(int routeKey, int timeout,TSledExchange tSledExchange)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTSledExchange(tSledExchange, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TSledExchangePage  reqTSledExchange(ReqTSledExchangeOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledExchange(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TSledExchangePage  reqTSledExchange(ReqTSledExchangeOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqTSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<TSledExchangePage>(){
    @Override
    public TSledExchangePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqTSledExchange(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public TSledExchangePage  reqTSledExchange(int routeKey, int timeout,ReqTSledExchangeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledExchange(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TCommodityMapPage  reqTCommodityMap(ReqTCommodityMapOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTCommodityMap(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TCommodityMapPage  reqTCommodityMap(ReqTCommodityMapOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqTCommodityMap").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<TCommodityMapPage>(){
    @Override
    public TCommodityMapPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqTCommodityMap(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public TCommodityMapPage  reqTCommodityMap(int routeKey, int timeout,ReqTCommodityMapOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTCommodityMap(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addTCommodityMap(TCommodityMap tCommodityMap) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addTCommodityMap(tCommodityMap, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addTCommodityMap(TCommodityMap tCommodityMap,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addTCommodityMap").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addTCommodityMap(platformArgs, tCommodityMap);
      return null;
      }
    }, invokeInfo);
  }

  public void  addTCommodityMap(int routeKey, int timeout,TCommodityMap tCommodityMap)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addTCommodityMap(tCommodityMap, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledExchangeMapping(com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledExchangeMapping(sledExchangeMapping, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledExchangeMapping(com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSledExchangeMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addSledExchangeMapping(platformArgs, sledExchangeMapping);
      return null;
      }
    }, invokeInfo);
  }

  public void  addSledExchangeMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledExchangeMapping(sledExchangeMapping, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledExchangeMapping(com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledExchangeMapping(sledExchangeMapping, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledExchangeMapping(com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSledExchangeMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateSledExchangeMapping(platformArgs, sledExchangeMapping);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateSledExchangeMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledExchangeMapping sledExchangeMapping)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledExchangeMapping(sledExchangeMapping, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledExchangeMappingPage  reqSledExchangeMapping(com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledExchangeMapping(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledExchangeMappingPage  reqSledExchangeMapping(com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledExchangeMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SledExchangeMappingPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SledExchangeMappingPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSledExchangeMapping(platformArgs, option);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledExchangeMappingPage  reqSledExchangeMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSledExchangeMappingOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledExchangeMapping(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledCommodityTypeMapping(com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledCommodityTypeMapping(sledCommodityTypeMapping, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledCommodityTypeMapping(com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSledCommodityTypeMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addSledCommodityTypeMapping(platformArgs, sledCommodityTypeMapping);
      return null;
      }
    }, invokeInfo);
  }

  public void  addSledCommodityTypeMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledCommodityTypeMapping(sledCommodityTypeMapping, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledCommodityTypeMapping(com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledCommodityTypeMapping(sledCommodityTypeMapping, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledCommodityTypeMapping(com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSledCommodityTypeMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateSledCommodityTypeMapping(platformArgs, sledCommodityTypeMapping);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateSledCommodityTypeMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMapping sledCommodityTypeMapping)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledCommodityTypeMapping(sledCommodityTypeMapping, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMappingPage  reqSledCommodityTypeMapping(com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommodityTypeMapping(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMappingPage  reqSledCommodityTypeMapping(com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledCommodityTypeMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMappingPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMappingPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSledCommodityTypeMapping(platformArgs, option);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommodityTypeMappingPage  reqSledCommodityTypeMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSledCommodityTypeMappingOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommodityTypeMapping(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTCommodityMap(TCommodityMap tCommodityMap) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTCommodityMap(tCommodityMap, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  updateTCommodityMap(TCommodityMap tCommodityMap,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateTCommodityMap").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).updateTCommodityMap(platformArgs, tCommodityMap);
      }
    }, invokeInfo);
  }

  public int  updateTCommodityMap(int routeKey, int timeout,TCommodityMap tCommodityMap)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateTCommodityMap(tCommodityMap, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  inactiveExpiredSledContract(long expiredTimestamp) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return inactiveExpiredSledContract(expiredTimestamp, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  inactiveExpiredSledContract(long expiredTimestamp,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("inactiveExpiredSledContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Boolean>(){
    @Override
    public Boolean call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).inactiveExpiredSledContract(platformArgs, expiredTimestamp);
      }
    }, invokeInfo);
  }

  public boolean  inactiveExpiredSledContract(int routeKey, int timeout,long expiredTimestamp)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return inactiveExpiredSledContract(expiredTimestamp, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TSledCommodityChangePage  reqTSledCommodityChange(ReqTSledCommodityChangeOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledCommodityChange(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public TSledCommodityChangePage  reqTSledCommodityChange(ReqTSledCommodityChangeOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqTSledCommodityChange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<TSledCommodityChangePage>(){
    @Override
    public TSledCommodityChangePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqTSledCommodityChange(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public TSledCommodityChangePage  reqTSledCommodityChange(int routeKey, int timeout,ReqTSledCommodityChangeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTSledCommodityChange(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledCommodityChange(TSledCommodityChange tCommodityChange) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledCommodityChange(tCommodityChange, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public int  addTSledCommodityChange(TSledCommodityChange tCommodityChange,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addTSledCommodityChange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Integer>(){
    @Override
    public Integer call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).addTSledCommodityChange(platformArgs, tCommodityChange);
      }
    }, invokeInfo);
  }

  public int  addTSledCommodityChange(int routeKey, int timeout,TSledCommodityChange tCommodityChange)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addTSledCommodityChange(tCommodityChange, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  removeTSledCommodityChange(TSledCommodityChange tCommodityChange) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return removeTSledCommodityChange(tCommodityChange, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public boolean  removeTSledCommodityChange(TSledCommodityChange tCommodityChange,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeTSledCommodityChange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<Boolean>(){
    @Override
    public Boolean call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).removeTSledCommodityChange(platformArgs, tCommodityChange);
      }
    }, invokeInfo);
  }

  public boolean  removeTSledCommodityChange(int routeKey, int timeout,TSledCommodityChange tCommodityChange)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return removeTSledCommodityChange(tCommodityChange, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addCommodityMapFileInfo(mapFileInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addCommodityMapFileInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addCommodityMapFileInfo(platformArgs, mapFileInfo);
      return null;
      }
    }, invokeInfo);
  }

  public void  addCommodityMapFileInfo(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addCommodityMapFileInfo(mapFileInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCommodityMapFileInfo(mapFileInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateCommodityMapFileInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateCommodityMapFileInfo(platformArgs, mapFileInfo);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateCommodityMapFileInfo(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCommodityMapFileInfo(mapFileInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfoPage  reqCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommodityMapFileInfo(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfoPage  reqCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCommodityMapFileInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfoPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfoPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqCommodityMapFileInfo(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfoPage  reqCommodityMapFileInfo(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommodityMapFileInfo(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SyncMappingTaskPage  reqSyncMappingTask(com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSyncMappingTask(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SyncMappingTaskPage  reqSyncMappingTask(com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSyncMappingTask").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SyncMappingTaskPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SyncMappingTaskPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSyncMappingTask(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SyncMappingTaskPage  reqSyncMappingTask(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSyncMappingTask(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSyncMappingTask(com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSyncMappingTask(tTask, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSyncMappingTask(com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSyncMappingTask").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addSyncMappingTask(platformArgs, tTask);
      return null;
      }
    }, invokeInfo);
  }

  public void  addSyncMappingTask(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SyncMappingTask tTask)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSyncMappingTask(tTask, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSyncMappingTask(com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSyncMappingTask(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSyncMappingTask(com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSyncMappingTask").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeSyncMappingTask(platformArgs, option);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeSyncMappingTask(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSyncMappingTaskOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSyncMappingTask(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addTechPlatformCommodity(com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addTechPlatformCommodity(techPlatformCommodity, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addTechPlatformCommodity(com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addTechPlatformCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addTechPlatformCommodity(platformArgs, techPlatformCommodity);
      return null;
      }
    }, invokeInfo);
  }

  public void  addTechPlatformCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addTechPlatformCommodity(techPlatformCommodity, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodityPage  reqTechPlatformCommodity(com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTechPlatformCommodity(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodityPage  reqTechPlatformCommodity(com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqTechPlatformCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodityPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodityPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqTechPlatformCommodity(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodityPage  reqTechPlatformCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTechPlatformCommodity(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSledCommodity(RemoveSledCommodityOption removeOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSledCommodity(removeOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSledCommodity(RemoveSledCommodityOption removeOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeSledCommodity(platformArgs, removeOption);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeSledCommodity(int routeKey, int timeout,RemoveSledCommodityOption removeOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSledCommodity(removeOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSledExchange(RemoveSledExchangeOption removeOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSledExchange(removeOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSledExchange(RemoveSledExchangeOption removeOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeSledExchange(platformArgs, removeOption);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeSledExchange(int routeKey, int timeout,RemoveSledExchangeOption removeOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSledExchange(removeOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addContractVersion(com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addContractVersion(contractVersion, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addContractVersion(com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addContractVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addContractVersion(platformArgs, contractVersion);
      return null;
      }
    }, invokeInfo);
  }

  public void  addContractVersion(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addContractVersion(contractVersion, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeContractVersion(com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeContractVersion(removeOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeContractVersion(com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeContractVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeContractVersion(platformArgs, removeOption);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeContractVersion(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption removeOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeContractVersion(removeOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.ContractVersionPage  reqContractVersion(com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqContractVersion(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.ContractVersionPage  reqContractVersion(com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqContractVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.ContractVersionPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.ContractVersionPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqContractVersion(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.ContractVersionPage  reqContractVersion(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqContractVersionOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqContractVersion(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateContractVersion(com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateContractVersion(contractVersion, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateContractVersion(com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateContractVersion").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateContractVersion(platformArgs, contractVersion);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateContractVersion(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ContractVersion contractVersion)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateContractVersion(contractVersion, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addDbLocking(com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addDbLocking(dbLockingInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addDbLocking(com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addDbLocking").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addDbLocking(platformArgs, dbLockingInfo);
      return null;
      }
    }, invokeInfo);
  }

  public void  addDbLocking(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo dbLockingInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addDbLocking(dbLockingInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeDbLocking(String lockedBy) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeDbLocking(lockedBy, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeDbLocking(String lockedBy,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeDbLocking").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeDbLocking(platformArgs, lockedBy);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeDbLocking(int routeKey, int timeout,String lockedBy)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeDbLocking(lockedBy, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo  reqDbLockingInfo() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqDbLockingInfo(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo  reqDbLockingInfo(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqDbLockingInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqDbLockingInfo(platformArgs);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo  reqDbLockingInfo(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqDbLockingInfo(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledTradeTimeConfig(com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledTradeTimeConfig(config, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledTradeTimeConfig(com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSledTradeTimeConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addSledTradeTimeConfig(platformArgs, config);
      return null;
      }
    }, invokeInfo);
  }

  public void  addSledTradeTimeConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledTradeTimeConfig(config, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledTradeTimeConfig(com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledTradeTimeConfig(config, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledTradeTimeConfig(com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSledTradeTimeConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateSledTradeTimeConfig(platformArgs, config);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateSledTradeTimeConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig config)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledTradeTimeConfig(config, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfigPage  reqSledTradeTimeConfig(com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradeTimeConfig(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfigPage  reqSledTradeTimeConfig(com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledTradeTimeConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfigPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfigPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSledTradeTimeConfig(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfigPage  reqSledTradeTimeConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeConfigOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradeTimeConfig(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSpecTradeTime(specTradeTime, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSpecTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addSpecTradeTime(platformArgs, specTradeTime);
      return null;
      }
    }, invokeInfo);
  }

  public void  addSpecTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSpecTradeTime(specTradeTime, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSpecTradeTime(specTradeTime, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSpecTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateSpecTradeTime(platformArgs, specTradeTime);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateSpecTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SpecTradeTime specTradeTime)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSpecTradeTime(specTradeTime, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SpecTradeTimePage  reqSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSpecTradeTime(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SpecTradeTimePage  reqSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSpecTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SpecTradeTimePage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SpecTradeTimePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSpecTradeTime(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SpecTradeTimePage  reqSpecTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSpecTradeTimeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSpecTradeTime(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommoditySpecTradeTimePage  reqSledCommoditySpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommoditySpecTradeTime(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommoditySpecTradeTimePage  reqSledCommoditySpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledCommoditySpecTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SledCommoditySpecTradeTimePage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SledCommoditySpecTradeTimePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSledCommoditySpecTradeTime(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommoditySpecTradeTimePage  reqSledCommoditySpecTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommoditySpecTradeTime(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimePage  reqSledTradeTime(com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradeTime(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimePage  reqSledTradeTime(com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimePage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSledTradeTime(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimePage  reqSledTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradeTime(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  batAddSledTradeTime(List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    batAddSledTradeTime(sledTradeTimes, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  batAddSledTradeTime(List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("batAddSledTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).batAddSledTradeTime(platformArgs, sledTradeTimes);
      return null;
      }
    }, invokeInfo);
  }

  public void  batAddSledTradeTime(int routeKey, int timeout,List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTime> sledTradeTimes)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    batAddSledTradeTime(sledTradeTimes, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addDstTransferConfig(transferConfig, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addDstTransferConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addDstTransferConfig(platformArgs, transferConfig);
      return null;
      }
    }, invokeInfo);
  }

  public void  addDstTransferConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addDstTransferConfig(transferConfig, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateDstTransferConfig(transferConfig, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateDstTransferConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateDstTransferConfig(platformArgs, transferConfig);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateDstTransferConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.DstTransferConfig transferConfig)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateDstTransferConfig(transferConfig, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.DstTransferConfigPage  reqDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqDstTransferConfig(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.DstTransferConfigPage  reqDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqDstTransferConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.DstTransferConfigPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.DstTransferConfigPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqDstTransferConfig(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.DstTransferConfigPage  reqDstTransferConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqDstTransferConfigOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqDstTransferConfig(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSpecTradeTime(removeOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSpecTradeTime(com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSpecTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeSpecTradeTime(platformArgs, removeOption);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeSpecTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.RemoveSpecTradeTimeOption removeOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSpecTradeTime(removeOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeDstTransferConfig(removeOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeDstTransferConfig(com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeDstTransferConfig").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeDstTransferConfig(platformArgs, removeOption);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeDstTransferConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeDstTransferConfig(removeOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  batUpdateSledTradeTimeConfigs(List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    batUpdateSledTradeTimeConfigs(configs, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  batUpdateSledTradeTimeConfigs(List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("batUpdateSledTradeTimeConfigs").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).batUpdateSledTradeTimeConfigs(platformArgs, configs);
      return null;
      }
    }, invokeInfo);
  }

  public void  batUpdateSledTradeTimeConfigs(int routeKey, int timeout,List<com.longsheng.xueqiao.contract.thriftapi.SledTradeTimeConfig> configs)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    batUpdateSledTradeTimeConfigs(configs, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addCommoditySource(com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addCommoditySource(commoditySource, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addCommoditySource(com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addCommoditySource").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addCommoditySource(platformArgs, commoditySource);
      return null;
      }
    }, invokeInfo);
  }

  public void  addCommoditySource(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addCommoditySource(commoditySource, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCommoditySource(com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCommoditySource(commoditySource, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCommoditySource(com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateCommoditySource").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateCommoditySource(platformArgs, commoditySource);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateCommoditySource(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommoditySource commoditySource)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCommoditySource(commoditySource, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommoditySourcePage  reqCommoditySource(com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommoditySource(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommoditySourcePage  reqCommoditySource(com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCommoditySource").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.CommoditySourcePage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.CommoditySourcePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqCommoditySource(platformArgs, option);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommoditySourcePage  reqCommoditySource(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommoditySource(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addCommoditySourceAccount(com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addCommoditySourceAccount(commoditySourceAccount, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addCommoditySourceAccount(com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addCommoditySourceAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addCommoditySourceAccount(platformArgs, commoditySourceAccount);
      return null;
      }
    }, invokeInfo);
  }

  public void  addCommoditySourceAccount(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addCommoditySourceAccount(commoditySourceAccount, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCommoditySourceAccount(com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCommoditySourceAccount(commoditySourceAccount, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateCommoditySourceAccount(com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateCommoditySourceAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateCommoditySourceAccount(platformArgs, commoditySourceAccount);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateCommoditySourceAccount(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccount commoditySourceAccount)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateCommoditySourceAccount(commoditySourceAccount, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccountPage  reqCommoditySourceAccount(com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommoditySourceAccount(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccountPage  reqCommoditySourceAccount(com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCommoditySourceAccount").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccountPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccountPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqCommoditySourceAccount(platformArgs, option);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommoditySourceAccountPage  reqCommoditySourceAccount(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySourceAccountOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommoditySourceAccount(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledTradingSession(com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledTradingSession(sledTradingSession, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  addSledTradingSession(com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSledTradingSession").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).addSledTradingSession(platformArgs, sledTradingSession);
      return null;
      }
    }, invokeInfo);
  }

  public void  addSledTradingSession(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addSledTradingSession(sledTradingSession, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledTradingSession(com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledTradingSession(sledTradingSession, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  updateSledTradingSession(com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSledTradingSession").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).updateSledTradingSession(platformArgs, sledTradingSession);
      return null;
      }
    }, invokeInfo);
  }

  public void  updateSledTradingSession(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.SledTradingSession sledTradingSession)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    updateSledTradingSession(sledTradingSession, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradingSessionPage  reqSledTradingSession(com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradingSession(option, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradingSessionPage  reqSledTradingSession(com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledTradingSession").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.thriftapi.SledTradingSessionPage>(){
    @Override
    public com.longsheng.xueqiao.contract.thriftapi.SledTradingSessionPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractDaoService.Client(protocol).reqSledTradingSession(platformArgs, option, pageOption);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradingSessionPage  reqSledTradingSession(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSledTradingSessionOption option, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradingSession(option, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSledTradingSession(long tradeSessionId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSledTradingSession(tradeSessionId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeSledTradingSession(long tradeSessionId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSledTradingSession").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).removeSledTradingSession(platformArgs, tradeSessionId);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeSledTradingSession(int routeKey, int timeout,long tradeSessionId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeSledTradingSession(tradeSessionId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  clearAllTechPlatformCommodity(int techPlatformValue) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    clearAllTechPlatformCommodity(techPlatformValue, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  clearAllTechPlatformCommodity(int techPlatformValue,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("clearAllTechPlatformCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractDaoService.Client(protocol).clearAllTechPlatformCommodity(platformArgs, techPlatformValue);
      return null;
      }
    }, invokeInfo);
  }

  public void  clearAllTechPlatformCommodity(int routeKey, int timeout,int techPlatformValue)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    clearAllTechPlatformCommodity(techPlatformValue, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
