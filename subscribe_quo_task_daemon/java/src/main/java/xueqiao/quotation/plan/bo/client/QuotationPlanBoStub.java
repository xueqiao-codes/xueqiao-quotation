package xueqiao.quotation.plan.bo.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.soldier.platform.svr_platform.client.BaseStub;
import org.soldier.platform.svr_platform.client.TStubOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import java.util.List;
import xueqiao.quotation.plan.bo.GenPreviewState;
import xueqiao.quotation.plan.bo.QuerySubscribeContractItemOption;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;
import xueqiao.quotation.plan.bo.SubscribeContractItemPage;
import xueqiao.quotation.plan.bo.QuotationPlanBo;
import xueqiao.quotation.plan.bo.QuotationPlanBoVariable;

public class QuotationPlanBoStub extends BaseStub {

  public QuotationPlanBoStub() {
    super(QuotationPlanBoVariable.serviceKey);
  }

  public GenPreviewState  startGenPreviewSCClasses() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return startGenPreviewSCClasses(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public GenPreviewState  startGenPreviewSCClasses(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("startGenPreviewSCClasses").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<GenPreviewState>(){
    @Override
    public GenPreviewState call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationPlanBo.Client(protocol).startGenPreviewSCClasses(platformArgs);
      }
    }, invokeInfo);
  }

  public GenPreviewState  startGenPreviewSCClasses(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return startGenPreviewSCClasses(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public GenPreviewState  getGenPreviewState() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getGenPreviewState(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public GenPreviewState  getGenPreviewState(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getGenPreviewState").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<GenPreviewState>(){
    @Override
    public GenPreviewState call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationPlanBo.Client(protocol).getGenPreviewState(platformArgs);
      }
    }, invokeInfo);
  }

  public GenPreviewState  getGenPreviewState(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getGenPreviewState(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  commitPreviewSCClasses() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    commitPreviewSCClasses(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public void  commitPreviewSCClasses(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("commitPreviewSCClasses").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    super.runSync(new ThriftCallable<Void>(){
    @Override
    public Void call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      new QuotationPlanBo.Client(protocol).commitPreviewSCClasses(platformArgs);
      return null;
      }
    }, invokeInfo);
  }

  public void  commitPreviewSCClasses(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    commitPreviewSCClasses(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SubscribeContractItemPage  querySubscribeContractItemPage(QuerySubscribeContractItemOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return querySubscribeContractItemPage(queryOption, pageOption, new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public SubscribeContractItemPage  querySubscribeContractItemPage(QuerySubscribeContractItemOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption,TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("querySubscribeContractItemPage").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<SubscribeContractItemPage>(){
    @Override
    public SubscribeContractItemPage call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationPlanBo.Client(protocol).querySubscribeContractItemPage(platformArgs, queryOption, pageOption);
      }
    }, invokeInfo);
  }

  public SubscribeContractItemPage  querySubscribeContractItemPage(int routeKey, int timeout,QuerySubscribeContractItemOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return querySubscribeContractItemPage(queryOption, pageOption, new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<SubscribeCommodityClass>  getCurrentSCClasses() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getCurrentSCClasses(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<SubscribeCommodityClass>  getCurrentSCClasses(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getCurrentSCClasses").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<SubscribeCommodityClass>>(){
    @Override
    public List<SubscribeCommodityClass> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationPlanBo.Client(protocol).getCurrentSCClasses(platformArgs);
      }
    }, invokeInfo);
  }

  public List<SubscribeCommodityClass>  getCurrentSCClasses(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getCurrentSCClasses(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<SubscribeCommodityClass>  getPreviewSCClasses() throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getPreviewSCClasses(new TStubOption().setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

  public List<SubscribeCommodityClass>  getPreviewSCClasses(TStubOption platformStubOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException {
    InvokeMethodInfo invokeInfo = new InvokeMethodInfo("getPreviewSCClasses").setRouteKey(platformStubOption.getRouteKey()).setTimeoutMs(platformStubOption.getTimeoutMs());
    if (platformStubOption.getCallTrace() != null) { 
      invokeInfo.setSourceCallTrace(platformStubOption.getCallTrace());
    } else {
      invokeInfo.setSourceCallTrace(Thread.currentThread().getStackTrace()[2]);
    }
    return super.runSync(new ThriftCallable<List<SubscribeCommodityClass>>(){
    @Override
    public List<SubscribeCommodityClass> call(TProtocol protocol, PlatformArgs platformArgs) throws ErrorInfo, TException {
      return new QuotationPlanBo.Client(protocol).getPreviewSCClasses(platformArgs);
      }
    }, invokeInfo);
  }

  public List<SubscribeCommodityClass>  getPreviewSCClasses(int routeKey, int timeout)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    return getPreviewSCClasses(new TStubOption().setRouteKey(routeKey).setTimeoutMs(timeout).setCallTrace(Thread.currentThread().getStackTrace()[2]));
  }

}
