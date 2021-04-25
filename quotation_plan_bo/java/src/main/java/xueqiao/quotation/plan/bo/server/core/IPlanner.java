package xueqiao.quotation.plan.bo.server.core;

import java.util.List;

import xueqiao.quotation.account.thriftapi.ContractRegisterRule;
import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;

/**
 * 
 * @author 规划器
 *
 */
public interface IPlanner {
    List<SubscribeCommodityClass> plan(
            QuotationPlatformEnv platformEnv
            , List<QuotationAccount> avaibleAccounts
            , List<ContractRegisterRule> contractRegisterRules
            , IPlannerDataProvider provider) throws Exception;
}
