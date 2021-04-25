package xueqiao.quotation.access.core.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.broker.thriftapi.AccessAddress;
import com.longsheng.xueqiao.broker.thriftapi.BrokerAccessEntry;

import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.account.thriftapi.QuotationTechPlatform;

public class ESunnyDCConvertor extends BaseDCConvertor {
    
    public ESunnyDCConvertor(QuotationAccount account) {
        super(account);
        Preconditions.checkNotNull(account);
        Preconditions.checkArgument(account.getPlatform() == QuotationTechPlatform.ESUNNY);
    }

    @Override
    protected String getContainerNodeName() {
        return "esunny-access";
    }

    @Override
    protected String getPlatform() {
        if (getAccount().getPlatformEnv() == QuotationPlatformEnv.SIM) {
            return "SES";
        }
        
        return "ES";
    }

    @Override
    protected void getEnvs(Map<String, String> envs, BrokerAccessEntry broker) {
        envs.put("AUTH_CODE", StringUtils.trimToEmpty(
                getAccount().getAccountProperties().get("ESUNNY9_AUTHCODE")));
        
        StringBuilder quotAddrBuilder = new StringBuilder(64);
        for (int index = 0; index < broker.getQuotaAddressesSize() && index < 1; ++index) {
            AccessAddress quotAddr = broker.getQuotaAddresses().get(index);
            if (index > 0) {
                quotAddrBuilder.append(";");
            }
            quotAddrBuilder.append(quotAddr.getAddress()).append(":").append(quotAddr.port);
        }
        envs.put("QUOT_ADDR", quotAddrBuilder.toString());
    }
    
    @Override
    protected String getRealUserName() {
        if (getAccount().getPlatformEnv() == QuotationPlatformEnv.SIM
                && getAccount().getAccountName().startsWith("ES-")) {
            return "ES";
        }
        
        return getAccount().getAccountName();
    }
}
