package xueqiao.quotation.plan.bo.server.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;

import com.longsheng.xueqiao.contract.online.dao.thriftapi.client.ContractOnlineDaoServiceStub;
import com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledCommodityOption;
import com.longsheng.xueqiao.contract.standard.thriftapi.ReqSledContractOption;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodityPage;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledContract;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledContractPage;
import com.longsheng.xueqiao.contract.standard.thriftapi.TechPlatformEnv;

import xueqiao.quotation.account.thriftapi.ContractActiveRule;
import xueqiao.quotation.account.thriftapi.ContractActiveRulePage;
import xueqiao.quotation.account.thriftapi.QuotationAccountSupport;
import xueqiao.quotation.account.thriftapi.QuotationAccountSupportPage;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption;
import xueqiao.quotation.account.thriftapi.ReqQuotationAccountSupportOption;
import xueqiao.quotation.account.thriftapi.client.QuotationAccountDaoStub;
import xueqiao.quotation.plan.bo.server.core.IPlannerDataProvider;

public class PlannerDataProviderImpl implements IPlannerDataProvider {
    
    private Map<Long, ContractActiveRule> mRuleCaches = new HashMap<>();
    private Map<String, Integer> mExchangeCommodityNumCache = new HashMap<>();
    private Map<Long, SledCommodity> mCommodityCache = new HashMap<>();
    private Map<Long, QuotationAccountSupport> mQuotationAccountSupportCache = new HashMap<>();
    
    private static <T> T thriftErrorRetry(Callable<T> c) {
        while(true) {
            try {
                return c.call();
            } catch (Throwable e) {
                AppLog.e(e.getMessage());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
    
    @Override
    public ContractActiveRule getActiveRule(long sledCommodityId) {
        ContractActiveRule rule = mRuleCaches.get(sledCommodityId);
        if (rule != null) {
            return rule;
        }
        
        Set<Integer> queryCommodityIds = new HashSet<>();
        queryCommodityIds.add((int)sledCommodityId);
        
        ContractActiveRulePage resultPage = thriftErrorRetry(new Callable<ContractActiveRulePage>() {
            @Override
            public ContractActiveRulePage call() throws Exception {
                return new QuotationAccountDaoStub().reqContractActiveRule(
                        new ReqContractActiveRuleOption().setCommodityIds(queryCommodityIds)
                        , new IndexedPageOption().setPageIndex(0).setPageSize(10));
            }
        });
        if (resultPage.getPageSize() == 1) {
            rule = resultPage.getPage().get(0);
            mRuleCaches.put(sledCommodityId, rule);
        }
        
        return rule;
    }

    @Override
    public int getExchangeCommodityTotalNum(String exchangeMic) {
        Integer totalNum = mExchangeCommodityNumCache.get(exchangeMic);
        if (totalNum != null) {
            return totalNum;
        }
        
        SledCommodityPage commodityPage = thriftErrorRetry(new Callable<SledCommodityPage>() {
            @Override
            public SledCommodityPage call() throws Exception {
                return new ContractOnlineDaoServiceStub().reqSledCommodity(new ReqSledCommodityOption()
                        .setExchangeMic(exchangeMic).setNeedTotalCount(true), 0, 1);
            }
        });
        
        totalNum = commodityPage.getTotal();
        mExchangeCommodityNumCache.put(exchangeMic, totalNum);
        
        return totalNum;
    }

    @Override
    public SledCommodity getCommodity(long sledCommodityId) {
        SledCommodity commodity = mCommodityCache.get(sledCommodityId);
        if (commodity != null) {
            return commodity;
        }
        
        SledCommodityPage commodityPage = thriftErrorRetry(new Callable<SledCommodityPage>() {
            @Override
            public SledCommodityPage call() throws Exception {
                List<Integer> commodityIdList = new ArrayList<>();
                commodityIdList.add((int)sledCommodityId);
                return new ContractOnlineDaoServiceStub().reqSledCommodity(
                        new ReqSledCommodityOption().setSledCommodityIdList(commodityIdList)
                        , 0, 10);
            }
        });
        
        if (commodityPage.getPageSize() == 1) {
            commodity = commodityPage.getPage().get(0);
            mCommodityCache.put(sledCommodityId, commodity);
        }
        
        return commodity;
    }

    @Override
    public QuotationAccountSupport getAccountSupport(long quotationAccountId) {
        QuotationAccountSupport accountSupport = mQuotationAccountSupportCache.get(quotationAccountId);
        if (accountSupport != null) {
            return accountSupport;
        }
        
        Set<Long> queryAccoutIds = new HashSet<>();
        queryAccoutIds.add(quotationAccountId);
        
        QuotationAccountSupportPage supportPage = thriftErrorRetry(new Callable<QuotationAccountSupportPage>() {
            @Override
            public QuotationAccountSupportPage call() throws Exception {
                return  new QuotationAccountDaoStub().reqQuotationAccountSupport(
                                new ReqQuotationAccountSupportOption().setAccountIds(queryAccoutIds)
                                , new IndexedPageOption().setPageIndex(0).setPageSize(10));
            }
        });
        
        AppLog.d("getAccoutSupportPage for quotationAccountId=" + quotationAccountId + " is " + supportPage);
        
        if (supportPage.getPageSize() == 1) {
            accountSupport = supportPage.getPage().get(0);
            mQuotationAccountSupportCache.put(quotationAccountId, accountSupport);
        }
        
        return accountSupport;
    }

    @Override
    public SledContract getContract(long sledCommodityId, String contractCode, QuotationPlatformEnv platformEnv) {
        SledContractPage contractPage = thriftErrorRetry(new Callable<SledContractPage>() {
            @Override
            public SledContractPage call() throws Exception {
                return new ContractOnlineDaoServiceStub().reqSledContract(new ReqSledContractOption()
                        .setSledCommodityId((int)sledCommodityId)
                        .setSledContractCode(contractCode)
                        .setPlatformEnv((platformEnv == QuotationPlatformEnv.SIM) ? TechPlatformEnv.SIM : TechPlatformEnv.REAL), 0, 10);
            }
            
        });
        
        AppLog.d("getContract for sledCommodityId=" + sledCommodityId 
                + ", contractCode=" + contractCode + ", platformEnv=" + platformEnv
                + " is " + contractPage);
        
        if (contractPage.getPageSize() == 1) {
            return contractPage.getPage().get(0);
        }
        
        return null;
    }
    
}
