package com.longsheng.xueqiao.contract.thriftapi.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import com.longsheng.xueqiao.contract.thriftapi.ActiveCommodityResp;
import com.longsheng.xueqiao.contract.thriftapi.CommodityMappingEdit;
import com.longsheng.xueqiao.contract.thriftapi.CommodityMappingEditPage;
import com.longsheng.xueqiao.contract.thriftapi.ImportCommodityMapResp;
import com.longsheng.xueqiao.contract.thriftapi.LinkCommodityMappingInfo;
import com.longsheng.xueqiao.contract.thriftapi.LinkCommodityMappingResp;
import com.longsheng.xueqiao.contract.thriftapi.RemoveCommodityResp;
import com.longsheng.xueqiao.contract.thriftapi.RemoveSledExchangeResp;
import com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMappingEditOption;
import com.longsheng.xueqiao.contract.thriftapi.SledCommodityEdit;
import com.longsheng.xueqiao.contract.thriftapi.SledCommodityEditPage;
import com.longsheng.xueqiao.contract.thriftapi.SledCommodityStateChangeResp;
import com.longsheng.xueqiao.contract.thriftapi.SledContractEditPage;
import com.longsheng.xueqiao.contract.thriftapi.SyncContractOption;
import com.longsheng.xueqiao.contract.thriftapi.SyncContractResp;
import com.longsheng.xueqiao.contract.thriftapi.SyncTradeTimeResp;
import com.longsheng.xueqiao.contract.thriftapi.VerifyCommodityResp;
import com.longsheng.xueqiao.contract.thriftapi.ContractService;
import com.longsheng.xueqiao.contract.thriftapi.ContractServiceVariable;

public class ContractServiceStub extends BaseStub {

  public ContractServiceStub() {
    super(ContractServiceVariable.serviceKey);
  }

  public SledContractEditPage  reqSledContractDetail(com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledContractOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledContractDetail(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledContractEditPage  reqSledContractDetail(com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledContractOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledContractDetail").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SledContractEditPage>(){
    @Override
    public SledContractEditPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).reqSledContractDetail(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public SledContractEditPage  reqSledContractDetail(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledContractOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledContractDetail(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityEditPage  reqSledCommodity(com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledCommodityOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommodity(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityEditPage  reqSledCommodity(com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledCommodityOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SledCommodityEditPage>(){
    @Override
    public SledCommodityEditPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).reqSledCommodity(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public SledCommodityEditPage  reqSledCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledCommodityOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommodity(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchangePage  reqSledExchange(com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledExchangeOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledExchange(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchangePage  reqSledExchange(com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledExchangeOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.standard.thriftapi.SledExchangePage>(){
    @Override
    public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchangePage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).reqSledExchange(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchangePage  reqSledExchange(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledExchangeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledExchange(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CommodityMappingEdit  addCommodityMapping(com.longsheng.xueqiao.contract.standard.thriftapi.CommodityMapping commodityMapping) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addCommodityMapping(commodityMapping, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CommodityMappingEdit  addCommodityMapping(com.longsheng.xueqiao.contract.standard.thriftapi.CommodityMapping commodityMapping,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addCommodityMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<CommodityMappingEdit>(){
    @Override
    public CommodityMappingEdit call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).addCommodityMapping(platformArgs, commodityMapping);
      }
    }, invokeInfo);
  }

  public CommodityMappingEdit  addCommodityMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.CommodityMapping commodityMapping)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addCommodityMapping(commodityMapping, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange  addSledExchange(com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange sledExchange) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addSledExchange(sledExchange, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange  addSledExchange(com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange sledExchange,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange>(){
    @Override
    public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).addSledExchange(platformArgs, sledExchange);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange  addSledExchange(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange sledExchange)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addSledExchange(sledExchange, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CommodityMappingEditPage  reqCommodityMapping(ReqCommodityMappingEditOption option, int pageIndex, int pageSize) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommodityMapping(option, pageIndex, pageSize, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CommodityMappingEditPage  reqCommodityMapping(ReqCommodityMappingEditOption option, int pageIndex, int pageSize,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("reqCommodityMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<CommodityMappingEditPage>(){
    @Override
    public CommodityMappingEditPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).reqCommodityMapping(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public CommodityMappingEditPage  reqCommodityMapping(int routeKey, int timeout,ReqCommodityMappingEditOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommodityMapping(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityEdit  addSledCommodity(com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity sledCommodity) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addSledCommodity(sledCommodity, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityEdit  addSledCommodity(com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity sledCommodity,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("addSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SledCommodityEdit>(){
    @Override
    public SledCommodityEdit call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).addSledCommodity(platformArgs, sledCommodity);
      }
    }, invokeInfo);
  }

  public SledCommodityEdit  addSledCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity sledCommodity)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return addSledCommodity(sledCommodity, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange  updateSledExchange(com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange sledExchange) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateSledExchange(sledExchange, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange  updateSledExchange(com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange sledExchange,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange>(){
    @Override
    public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).updateSledExchange(platformArgs, sledExchange);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange  updateSledExchange(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.SledExchange sledExchange)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateSledExchange(sledExchange, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityEdit  updateSledCommodity(com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity sledCommodity) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateSledCommodity(sledCommodity, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityEdit  updateSledCommodity(com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity sledCommodity,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SledCommodityEdit>(){
    @Override
    public SledCommodityEdit call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).updateSledCommodity(platformArgs, sledCommodity);
      }
    }, invokeInfo);
  }

  public SledCommodityEdit  updateSledCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity sledCommodity)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateSledCommodity(sledCommodity, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CommodityMappingEdit  updateCommodityMapping(com.longsheng.xueqiao.contract.standard.thriftapi.CommodityMapping commodityMapping) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateCommodityMapping(commodityMapping, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public CommodityMappingEdit  updateCommodityMapping(com.longsheng.xueqiao.contract.standard.thriftapi.CommodityMapping commodityMapping,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("updateCommodityMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<CommodityMappingEdit>(){
    @Override
    public CommodityMappingEdit call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).updateCommodityMapping(platformArgs, commodityMapping);
      }
    }, invokeInfo);
  }

  public CommodityMappingEdit  updateCommodityMapping(int routeKey, int timeout,com.longsheng.xueqiao.contract.standard.thriftapi.CommodityMapping commodityMapping)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return updateCommodityMapping(commodityMapping, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public ImportCommodityMapResp  importCommodityMapFile(com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return importCommodityMapFile(mapFileInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public ImportCommodityMapResp  importCommodityMapFile(com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("importCommodityMapFile").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<ImportCommodityMapResp>(){
    @Override
    public ImportCommodityMapResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).importCommodityMapFile(platformArgs, mapFileInfo);
      }
    }, invokeInfo);
  }

  public ImportCommodityMapResp  importCommodityMapFile(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfo mapFileInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return importCommodityMapFile(mapFileInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      return new ContractService.Client(protocol).reqCommodityMapFileInfo(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.CommodityMapFileInfoPage  reqCommodityMapFileInfo(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqCommodityMapFileInfoOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqCommodityMapFileInfo(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public LinkCommodityMappingResp  linkTechPlatformMapping(LinkCommodityMappingInfo linkCommodityMappingInfo) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return linkTechPlatformMapping(linkCommodityMappingInfo, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public LinkCommodityMappingResp  linkTechPlatformMapping(LinkCommodityMappingInfo linkCommodityMappingInfo,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("linkTechPlatformMapping").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<LinkCommodityMappingResp>(){
    @Override
    public LinkCommodityMappingResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).linkTechPlatformMapping(platformArgs, linkCommodityMappingInfo);
      }
    }, invokeInfo);
  }

  public LinkCommodityMappingResp  linkTechPlatformMapping(int routeKey, int timeout,LinkCommodityMappingInfo linkCommodityMappingInfo)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return linkTechPlatformMapping(linkCommodityMappingInfo, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      return new ContractService.Client(protocol).reqTechPlatformCommodity(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodityPage  reqTechPlatformCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqTechPlatformCommodityOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqTechPlatformCommodity(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      new ContractService.Client(protocol).addTechPlatformCommodity(platformArgs, techPlatformCommodity);
      return null;
      }
    }, invokeInfo);
  }

  public void  addTechPlatformCommodity(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.TechPlatformCommodity techPlatformCommodity)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    addTechPlatformCommodity(techPlatformCommodity, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public ActiveCommodityResp  activeSledCommodity(int sledCommodityId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return activeSledCommodity(sledCommodityId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public ActiveCommodityResp  activeSledCommodity(int sledCommodityId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("activeSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<ActiveCommodityResp>(){
    @Override
    public ActiveCommodityResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).activeSledCommodity(platformArgs, sledCommodityId);
      }
    }, invokeInfo);
  }

  public ActiveCommodityResp  activeSledCommodity(int routeKey, int timeout,int sledCommodityId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return activeSledCommodity(sledCommodityId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public VerifyCommodityResp  verifySledCommodity(int sledCommodityId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return verifySledCommodity(sledCommodityId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public VerifyCommodityResp  verifySledCommodity(int sledCommodityId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("verifySledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<VerifyCommodityResp>(){
    @Override
    public VerifyCommodityResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).verifySledCommodity(platformArgs, sledCommodityId);
      }
    }, invokeInfo);
  }

  public VerifyCommodityResp  verifySledCommodity(int routeKey, int timeout,int sledCommodityId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return verifySledCommodity(sledCommodityId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public RemoveCommodityResp  removeSledCommodity(int sledCommodityId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return removeSledCommodity(sledCommodityId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public RemoveCommodityResp  removeSledCommodity(int sledCommodityId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSledCommodity").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<RemoveCommodityResp>(){
    @Override
    public RemoveCommodityResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).removeSledCommodity(platformArgs, sledCommodityId);
      }
    }, invokeInfo);
  }

  public RemoveCommodityResp  removeSledCommodity(int routeKey, int timeout,int sledCommodityId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return removeSledCommodity(sledCommodityId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public RemoveSledExchangeResp  removeSledExchange(int sledExchangeId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return removeSledExchange(sledExchangeId, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public RemoveSledExchangeResp  removeSledExchange(int sledExchangeId,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeSledExchange").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<RemoveSledExchangeResp>(){
    @Override
    public RemoveSledExchangeResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).removeSledExchange(platformArgs, sledExchangeId);
      }
    }, invokeInfo);
  }

  public RemoveSledExchangeResp  removeSledExchange(int routeKey, int timeout,int sledExchangeId)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return removeSledExchange(sledExchangeId, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityStateChangeResp  changeSledCommodityState(int sledCommodityId, com.longsheng.xueqiao.contract.standard.thriftapi.CommodityState newState) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return changeSledCommodityState(sledCommodityId, newState, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SledCommodityStateChangeResp  changeSledCommodityState(int sledCommodityId, com.longsheng.xueqiao.contract.standard.thriftapi.CommodityState newState,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("changeSledCommodityState").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SledCommodityStateChangeResp>(){
    @Override
    public SledCommodityStateChangeResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).changeSledCommodityState(platformArgs, sledCommodityId, newState);
      }
    }, invokeInfo);
  }

  public SledCommodityStateChangeResp  changeSledCommodityState(int routeKey, int timeout,int sledCommodityId, com.longsheng.xueqiao.contract.standard.thriftapi.CommodityState newState)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return changeSledCommodityState(sledCommodityId, newState, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      new ContractService.Client(protocol).addDbLocking(platformArgs, dbLockingInfo);
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
      new ContractService.Client(protocol).removeDbLocking(platformArgs, lockedBy);
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
      return new ContractService.Client(protocol).reqDbLockingInfo(platformArgs);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo  reqDbLockingInfo(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqDbLockingInfo(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SyncContractResp  syncContract(SyncContractOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return syncContract(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SyncContractResp  syncContract(SyncContractOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("syncContract").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SyncContractResp>(){
    @Override
    public SyncContractResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).syncContract(platformArgs, option);
      }
    }, invokeInfo);
  }

  public SyncContractResp  syncContract(int routeKey, int timeout,SyncContractOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return syncContract(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.RemoveCommodityMapFileInfoOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeCommodityMapFileInfo(option, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  removeCommodityMapFileInfo(com.longsheng.xueqiao.contract.thriftapi.RemoveCommodityMapFileInfoOption option,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("removeCommodityMapFileInfo").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new ContractService.Client(protocol).removeCommodityMapFileInfo(platformArgs, option);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeCommodityMapFileInfo(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.RemoveCommodityMapFileInfoOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeCommodityMapFileInfo(option, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      new ContractService.Client(protocol).addSledTradeTimeConfig(platformArgs, config);
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
      new ContractService.Client(protocol).updateSledTradeTimeConfig(platformArgs, config);
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
      return new ContractService.Client(protocol).reqSledTradeTimeConfig(platformArgs, option, pageIndex, pageSize);
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
      new ContractService.Client(protocol).addSpecTradeTime(platformArgs, specTradeTime);
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
      new ContractService.Client(protocol).updateSpecTradeTime(platformArgs, specTradeTime);
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
      return new ContractService.Client(protocol).reqSpecTradeTime(platformArgs, option, pageIndex, pageSize);
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
      return new ContractService.Client(protocol).reqSledCommoditySpecTradeTime(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledCommoditySpecTradeTimePage  reqSledCommoditySpecTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqCommoditySpecTradeTimeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledCommoditySpecTradeTime(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SyncTradeTimeResp  syncTradeTime() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return syncTradeTime(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SyncTradeTimeResp  syncTradeTime(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("syncTradeTime").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SyncTradeTimeResp>(){
    @Override
    public SyncTradeTimeResp call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new ContractService.Client(protocol).syncTradeTime(platformArgs);
      }
    }, invokeInfo);
  }

  public SyncTradeTimeResp  syncTradeTime(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return syncTradeTime(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      return new ContractService.Client(protocol).reqSledTradeTime(platformArgs, option, pageIndex, pageSize);
      }
    }, invokeInfo);
  }

  public com.longsheng.xueqiao.contract.thriftapi.SledTradeTimePage  reqSledTradeTime(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.ReqSledTradeTimeOption option, int pageIndex, int pageSize)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return reqSledTradeTime(option, pageIndex, pageSize, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
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
      new ContractService.Client(protocol).addDstTransferConfig(platformArgs, transferConfig);
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
      new ContractService.Client(protocol).updateDstTransferConfig(platformArgs, transferConfig);
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
      return new ContractService.Client(protocol).reqDstTransferConfig(platformArgs, option, pageIndex, pageSize);
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
      new ContractService.Client(protocol).removeSpecTradeTime(platformArgs, removeOption);
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
      new ContractService.Client(protocol).removeDstTransferConfig(platformArgs, removeOption);
      return null;
      }
    }, invokeInfo);
  }

  public void  removeDstTransferConfig(int routeKey, int timeout,com.longsheng.xueqiao.contract.thriftapi.RemoveDstTransferConfigOption removeOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    removeDstTransferConfig(removeOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
