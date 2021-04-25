package xueqiao.quotation.access.core.impl;


import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.broker.thriftapi.AccessAddress;
import com.longsheng.xueqiao.broker.thriftapi.BrokerAccessEntry;

import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.account.thriftapi.QuotationTechPlatform;

public class CTPDCConvertor extends BaseDCConvertor {

    public CTPDCConvertor(QuotationAccount account) {
        super(account);
        Preconditions.checkNotNull(account);
        Preconditions.checkArgument(account.getPlatform() == QuotationTechPlatform.CTP);
    }

    @Override
    protected String getContainerNodeName() {
        return "ctp-access";
    }

    @Override
    protected String getPlatform() {
        if (getAccount().getPlatformEnv() == QuotationPlatformEnv.SIM) {
            return "SCTP";
        }
        return "CTP";
    }

    @Override
    protected void getEnvs(Map<String, String> envs, BrokerAccessEntry broker) {
        if (broker.isSetCustomInfoMap()) {
            envs.put("BROKER_ID", StringUtils.trimToEmpty(broker.getCustomInfoMap().get("BROKER_ID_INFO")));
        } else {
            envs.put("BROKER_ID", "");
        }
        
        StringBuilder tradeFrontEndBuilder = new StringBuilder(128);
        for (int index = 0; index < broker.getTradeAddressesSize() && index < 5; ++index) {
            AccessAddress tradeAddr = broker.getTradeAddresses().get(index);
            if (index > 0) {
                tradeFrontEndBuilder.append(";");
            }
            tradeFrontEndBuilder.append("tcp://").append(tradeAddr.getAddress())
                .append(":").append(tradeAddr.port);
        }
        envs.put("TRADE_FRONTEND", tradeFrontEndBuilder.toString());
        
        StringBuilder mdFrontEndBuilder = new StringBuilder(128);
        for (int index = 0; index < broker.getQuotaAddressesSize() && index < 5; ++index) {
            AccessAddress quotAddr = broker.getQuotaAddresses().get(index);
            if (index > 0) {
                mdFrontEndBuilder.append(";");
            }
            mdFrontEndBuilder.append("tcp://").append(quotAddr.getAddress())
                .append(":").append(quotAddr.getPort());
        }
        envs.put("MD_FRONTEND", mdFrontEndBuilder.toString());
        
        String userBrokerAuthCode = getAccount().getAccountProperties().get("CTP_STS_AUTHCODE");
        String userBrokerAuthAppId = getAccount().getAccountProperties().get("CTP_STS_APPID");
        
        if( StringUtils.isNotEmpty(userBrokerAuthCode) && StringUtils.isNotEmpty(userBrokerAuthAppId) && userBrokerAuthCode.trim().length() > 0 && userBrokerAuthAppId.trim().length() >0)
        {
            envs.put("STS_AUTHCODE", userBrokerAuthCode);
            envs.put("STS_APPID", userBrokerAuthAppId);
        }
        else
        {
            Map<String, String> customInfoMap = broker.getCustomInfoMap();
            String brokerAuthCode = customInfoMap.get("STS_AUTHCODE");
            if (StringUtils.isNotEmpty(brokerAuthCode)) {
                envs.put("STS_AUTHCODE", brokerAuthCode);
            }
            
            String brokerAuthAppId = customInfoMap.get("STS_APPID");
            if (StringUtils.isNotEmpty(brokerAuthAppId)) {
                envs.put("STS_APPID", brokerAuthAppId);
            }
        }
    }

    
}
