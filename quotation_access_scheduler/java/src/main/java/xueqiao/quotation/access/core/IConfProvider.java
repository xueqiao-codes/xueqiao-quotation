package xueqiao.quotation.access.core;

import xueqiao.quotation.account.thriftapi.DeploySet;
import xueqiao.quotation.account.thriftapi.QuotationTechPlatform;

public interface IConfProvider {
    /**
     *  调度器的版本
     */
    String getSchedulerVersion(QuotationTechPlatform techPlatform);
    
    /**
     *  镜像地址
     */
    String getImage(QuotationTechPlatform techPlatform);
    
    String getLimitsMemory(QuotationTechPlatform techPlatform);
    String getLimitsCpu(QuotationTechPlatform techPlatform);
    String getRequestsMemory(QuotationTechPlatform techPlatform);
    String getRequestsCpu(QuotationTechPlatform techPlatform);
    
    String getDefaultRestartClocks(QuotationTechPlatform techPlatform, DeploySet deploySet);
}
