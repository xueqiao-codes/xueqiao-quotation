package xueqiao.quotation.plan.bo.server.core;

import com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledContract;

import xueqiao.quotation.account.thriftapi.ContractActiveRule;
import xueqiao.quotation.account.thriftapi.ContractActiveType;
import xueqiao.quotation.account.thriftapi.QuotationAccountSupport;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;

public interface IPlannerDataProvider {
    public ContractActiveRule getActiveRule(long sledCommodityId);
    
    public int getExchangeCommodityTotalNum(String exchangeMic);
    
    public SledCommodity getCommodity(long sledCommodityId);
    
    public QuotationAccountSupport getAccountSupport(long quotationAccountId);
    
    public SledContract getContract(long sledCommodityId
            , String contractCode
            , QuotationPlatformEnv platformEnv);
}
