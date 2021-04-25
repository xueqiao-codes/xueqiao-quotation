package xueqiao.quotation.plan.bo.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import xueqiao.quotation.plan.bo.GenPreviewState;
import xueqiao.quotation.plan.bo.QuerySubscribeContractItemOption;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;
import xueqiao.quotation.plan.bo.SubscribeContractItemPage;
import xueqiao.quotation.plan.bo.QuotationPlanBo;
import xueqiao.quotation.plan.bo.QuotationPlanBoVariable;


public abstract class QuotationPlanBoAdaptor implements QuotationPlanBo.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public GenPreviewState startGenPreviewSCClasses(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationPlanBoVariable.serviceKey,"startGenPreviewSCClasses",platformArgs);
    return startGenPreviewSCClasses(oCntl);
  }

  protected abstract GenPreviewState startGenPreviewSCClasses(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public GenPreviewState getGenPreviewState(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationPlanBoVariable.serviceKey,"getGenPreviewState",platformArgs);
    return getGenPreviewState(oCntl);
  }

  protected abstract GenPreviewState getGenPreviewState(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public void commitPreviewSCClasses(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationPlanBoVariable.serviceKey,"commitPreviewSCClasses",platformArgs);
commitPreviewSCClasses(oCntl);
  }

  protected abstract void commitPreviewSCClasses(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public SubscribeContractItemPage querySubscribeContractItemPage(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, QuerySubscribeContractItemOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationPlanBoVariable.serviceKey,"querySubscribeContractItemPage",platformArgs);
    return querySubscribeContractItemPage(oCntl, queryOption, pageOption);
  }

  protected abstract SubscribeContractItemPage querySubscribeContractItemPage(TServiceCntl oCntl, QuerySubscribeContractItemOption queryOption, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<SubscribeCommodityClass> getCurrentSCClasses(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationPlanBoVariable.serviceKey,"getCurrentSCClasses",platformArgs);
    return getCurrentSCClasses(oCntl);
  }

  protected abstract List<SubscribeCommodityClass> getCurrentSCClasses(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<SubscribeCommodityClass> getPreviewSCClasses(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationPlanBoVariable.serviceKey,"getPreviewSCClasses",platformArgs);
    return getPreviewSCClasses(oCntl);
  }

  protected abstract List<SubscribeCommodityClass> getPreviewSCClasses(TServiceCntl oCntl) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected QuotationPlanBoAdaptor(){
    methodParameterNameMap.put("startGenPreviewSCClasses",new String[]{"platformArgs"});
    methodParameterNameMap.put("getGenPreviewState",new String[]{"platformArgs"});
    methodParameterNameMap.put("commitPreviewSCClasses",new String[]{"platformArgs"});
    methodParameterNameMap.put("querySubscribeContractItemPage",new String[]{"platformArgs", "queryOption", "pageOption"});
    methodParameterNameMap.put("getCurrentSCClasses",new String[]{"platformArgs"});
    methodParameterNameMap.put("getPreviewSCClasses",new String[]{"platformArgs"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
