package xueqiao.quotation.query.dao.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.client.ServiceException;
import org.soldier.platform.svr_platform.client.ServiceFinderFactory;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.comm.SvrConfiguration;
import org.soldier.platform.svr_platform.thrift.InpSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.soldier.platform.svr_platform.client.BaseStub;
import java.util.List;
import xueqiao.quotation.query.dao.QueryKLineMinuteOption;
import xueqiao.quotation.query.dao.QueryTickOption;
import xueqiao.quotation.query.dao.QuotationQueryDao;
import xueqiao.quotation.query.dao.QuotationQueryDaoVariable;

public class QuotationQueryDaoStub extends BaseStub {

  public QuotationQueryDaoStub() {
    super(QuotationQueryDaoVariable.serviceKey);
}

  public List<xueqiao.quotation.QuotationItem>  getTicks(int routeKey, int timeout,QueryTickOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
    String ip = null ;
    int port = 0;
    if (SvrConfiguration.getIsUsingInpService() && SvrConfiguration.isServiceInProcess(QuotationQueryDaoVariable.serviceKey)) { 
      socketTransport = new InpSocket(QuotationQueryDaoVariable.serviceKey, timeout);
    } else {
      try{
        ip = chooseServiceAddr("getTicks", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
        port =  chooseServicePort("getTicks");
        socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);

    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    if(ip != null) { platformArgs.setRemoteAddress(ip); }
    if(port != 0) { platformArgs.setRemotePort(port); }
    QuotationQueryDao.Client client = new  QuotationQueryDao.Client(protocol);
    List<xueqiao.quotation.QuotationItem> result = null;
    try {
      transport.open();
      result = client.getTicks(platformArgs, option);
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        QuotationQueryDaoVariable.serviceKey,"getTicks", ip, null);
    } catch (TException e) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        QuotationQueryDaoVariable.serviceKey,"getTicks", ip, e);
      throw e;
    }finally{
      transport.close();
    }
    return result;
  }

  public List<xueqiao.quotation.KLineQuotationMinuteItem>  getKLineMinutes(int routeKey, int timeout,QueryKLineMinuteOption option)throws org.soldier.platform.svr_platform.comm.ErrorInfo, TException{
    TTransport socketTransport = null;
    String ip = null ;
    int port = 0;
    if (SvrConfiguration.getIsUsingInpService() && SvrConfiguration.isServiceInProcess(QuotationQueryDaoVariable.serviceKey)) { 
      socketTransport = new InpSocket(QuotationQueryDaoVariable.serviceKey, timeout);
    } else {
      try{
        ip = chooseServiceAddr("getKLineMinutes", routeKey);
      } catch (ServiceException e1) { throw new TException(e1.getMessage());} 
        port =  chooseServicePort("getKLineMinutes");
        socketTransport = new TSocket(ip, port, timeout);
    }
    TTransport transport = new TFramedTransport(socketTransport);
    TProtocol protocol = new TCompactProtocol(transport);
    PlatformArgs platformArgs = new PlatformArgs();
    platformArgs.setTimeoutMs(timeout);

    StackTraceElement callStackElement = Thread.currentThread().getStackTrace()[2];
    platformArgs.setSourceDesc(
         callStackElement.getClassName() + "[" + callStackElement.getMethodName() + ":" 
         + callStackElement.getLineNumber() + "]");
    try {
      platformArgs.setSourceIp(InetAddress.getLocalHost().getHostAddress());
    } catch (UnknownHostException e) {
      AppLog.w(e.getMessage(), e);
    }
    if(ip != null) { platformArgs.setRemoteAddress(ip); }
    if(port != 0) { platformArgs.setRemotePort(port); }
    QuotationQueryDao.Client client = new  QuotationQueryDao.Client(protocol);
    List<xueqiao.quotation.KLineQuotationMinuteItem> result = null;
    try {
      transport.open();
      result = client.getKLineMinutes(platformArgs, option);
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        QuotationQueryDaoVariable.serviceKey,"getKLineMinutes", ip, null);
    } catch (TException e) {
      ServiceFinderFactory.getServiceFinder().updateCallInfo(
        QuotationQueryDaoVariable.serviceKey,"getKLineMinutes", ip, e);
      throw e;
    }finally{
      transport.close();
    }
    return result;
  }

}
