package xueqiao.quotation.query.dao.server;

import java.util.Properties;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import xueqiao.quotation.query.dao.QueryKLineMinuteOption;
import xueqiao.quotation.query.dao.QueryTickOption;
import xueqiao.quotation.query.dao.QuotationQueryDao;
import xueqiao.quotation.query.dao.QuotationQueryDaoVariable;


public abstract class QuotationQueryDaoAdaptor implements QuotationQueryDao.Iface{
  // unmodified map, so we do not need lock for this 
  private final Map<String, String[]> methodParameterNameMap = new HashMap<String, String[]>(); 

  public String[] getMethodParameterName(String methodName) {
    return methodParameterNameMap.get(methodName);
  }

  @Override
  public List<xueqiao.quotation.QuotationItem> getTicks(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, QueryTickOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationQueryDaoVariable.serviceKey,"getTicks",platformArgs);
    return getTicks(oCntl, option);
  }

  protected abstract List<xueqiao.quotation.QuotationItem> getTicks(TServiceCntl oCntl, QueryTickOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  @Override
  public List<xueqiao.quotation.KLineQuotationMinuteItem> getKLineMinutes(org.soldier.platform.svr_platform.comm.PlatformArgs platformArgs, QueryKLineMinuteOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException{
    TServiceCntl oCntl = new TServiceCntl(QuotationQueryDaoVariable.serviceKey,"getKLineMinutes",platformArgs);
    return getKLineMinutes(oCntl, option);
  }

  protected abstract List<xueqiao.quotation.KLineQuotationMinuteItem> getKLineMinutes(TServiceCntl oCntl, QueryKLineMinuteOption option) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException;

  protected QuotationQueryDaoAdaptor(){
    methodParameterNameMap.put("getTicks",new String[]{"platformArgs", "option"});
    methodParameterNameMap.put("getKLineMinutes",new String[]{"platformArgs", "option"});
  }
  protected abstract int InitApp(final Properties props);

  protected abstract void destroy();

}
