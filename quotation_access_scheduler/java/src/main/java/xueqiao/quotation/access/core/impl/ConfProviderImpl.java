package xueqiao.quotation.access.core.impl;

import org.apache.commons.lang.StringUtils;

import xueqiao.quotation.access.core.IConfProvider;
import xueqiao.quotation.account.thriftapi.DeploySet;
import xueqiao.quotation.account.thriftapi.QuotationTechPlatform;

public class ConfProviderImpl implements IConfProvider {
    
    private String checkEnv(String name) {
        String env = System.getenv(name);
        if (StringUtils.isEmpty(env)) {
            throw new RuntimeException("Env for " + name + " is not existed!");
        }
        return env;
    }
    
    @Override
    public String getSchedulerVersion(QuotationTechPlatform techPlatform) {
        return checkEnv(techPlatform.toString() + "_SCHEDULER_VERSION");
    }

    @Override
    public String getImage(QuotationTechPlatform techPlatform) {
        return checkEnv(techPlatform + "_IMAGE");
    }

    @Override
    public String getLimitsMemory(QuotationTechPlatform techPlatform) {
        String limitsMemory = System.getenv(techPlatform.toString() + "_LIMITS_MEMORY");
        if (StringUtils.isNotEmpty(limitsMemory)) {
            return limitsMemory;
        }
        return "500Mi";
    }

    @Override
    public String getLimitsCpu(QuotationTechPlatform techPlatform) {
        String limitsCpu = System.getenv(techPlatform.toString() + "_LIMITS_CPU");
        if (StringUtils.isNotEmpty(limitsCpu)) {
            return limitsCpu;
        }
        return "0.5";
    }

    @Override
    public String getRequestsMemory(QuotationTechPlatform techPlatform) {
        String requestsMemory = System.getenv(techPlatform.toString() + "_REQUESTS_MEM");
        if (StringUtils.isNotEmpty(requestsMemory)) {
            return requestsMemory;
        }
        return "200Mi";
    }

    @Override
    public String getRequestsCpu(QuotationTechPlatform techPlatform) {
        String requestsCpu = System.getenv(techPlatform.toString() + "_REQUESTS_CPU");
        if (StringUtils.isNotEmpty(requestsCpu)) {
            return requestsCpu;
        }
        return "0.1";
    }

    @Override
    public String getDefaultRestartClocks(QuotationTechPlatform techPlatform, DeploySet deploySet) {
        if (techPlatform == QuotationTechPlatform.ESUNNY) {
            if (deploySet == DeploySet.MASTER) {
                return "06:00";
            }
            return "05:59";
        } else if (techPlatform == QuotationTechPlatform.CTP) {
            if (deploySet == DeploySet.MASTER) {
                return "08:50;20:50";
            } 
            return "08:49;20:49";
        }
        
        return "";
    }
    
}
