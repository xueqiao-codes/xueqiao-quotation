package xueqiao.quotation.plan.bo.server.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.id_maker.IdException;

import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodityType;

import xueqiao.quotation.account.thriftapi.ContractActiveRule;
import xueqiao.quotation.account.thriftapi.ContractActiveType;
import xueqiao.quotation.account.thriftapi.ContractRegisterRule;
import xueqiao.quotation.account.thriftapi.DeploySet;
import xueqiao.quotation.account.thriftapi.MicSupportCommodity;
import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationAccountState;
import xueqiao.quotation.account.thriftapi.QuotationAccountSupport;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.account.thriftapi.SupportType;
import xueqiao.quotation.plan.bo.SubscribeAccountClass;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;
import xueqiao.quotation.plan.bo.server.core.IDGenerator;
import xueqiao.quotation.plan.bo.server.core.IPlanner;
import xueqiao.quotation.plan.bo.server.core.IPlannerDataProvider;
import xueqiao.quotation.plan.bo.server.ext.ActiveRuleHelper;

public class PlannerImpl implements IPlanner {
    private QuotationPlatformEnv mPlatformEnv;
    private IPlannerDataProvider mDataProvider;
    
    private Map<Long, Integer> mAccountCanSubscribeNum = new HashMap<>();
    private Map<String, ContractRegisterRule> mRules = new HashMap<>();
    private Map<DeploySet, Map<Long, QuotationAccount>> mAvaiableAccounts = new HashMap<>();
    
    private String generateRegisterRuleKey(ContractRegisterRule registerRule) {
        StringBuilder keyBuilder = new StringBuilder(64);
        keyBuilder.append(registerRule.getSledCommodityId())
                  .append("-")
                  .append(registerRule.getActiveType())
                  .append("-");
        if (registerRule.getActiveType() == ContractActiveType.FIXED_CODE) {
            keyBuilder.append(registerRule.getFixedCode());
        }
        return keyBuilder.toString();
    }
    
    private void initAvaiableAccounts(List<QuotationAccount> allAccounts) {
        mAvaiableAccounts.put(DeploySet.MASTER, new HashMap<>());
        mAvaiableAccounts.put(DeploySet.SLAVE, new HashMap<>());
        
        for (QuotationAccount account: allAccounts) {
            if (account.getAccountState() == QuotationAccountState.ACCOUNT_DISABLED) {
                continue;
            }
            if (account.getPlatformEnv() != mPlatformEnv) {
                continue;
            }
            if (!account.isSetDeploySet()) {
                AppLog.w("DeploySet not set for accountId=" + account.getAccountId());
                continue;
            }
            
            mAvaiableAccounts.get(account.getDeploySet()).put(account.getAccountId(), account);
            
            if (account.isSetMaxRegisterCount()) {
                mAccountCanSubscribeNum.put(account.getAccountId(), account.getMaxRegisterCount());
            } else {
                mAccountCanSubscribeNum.put(account.getAccountId(), 0);
            }
            
            AppLog.i("AddAvaibleAccount for platformEnv=" + mPlatformEnv + ", accountId=" 
                    + account.getAccountId() + ", platform=" + account.getPlatform()
                    + ", deploySet=" + account.getDeploySet()
                    + ", maxRegisterCount=" + account.maxRegisterCount);
        }
    }
    
    private void reduceCanSubscribeNum(long accountId, int reduceNum) {
        Integer value = mAccountCanSubscribeNum.get(accountId);
        if (value == null) {
            return ;
        }
        
        Integer newValue = value - reduceNum;
        Preconditions.checkState(newValue >= 0, "reduceCanSubscribeNum accountId=" + accountId
                + ", value=" + value
                + ", reduceNum=" + reduceNum 
                + " for newValue should not < 0" );
        mAccountCanSubscribeNum.put(accountId, newValue);
    }
    
    private int getCanSubscribeNum(long accountId) {
        Integer canSubscribeNum = mAccountCanSubscribeNum.get(accountId);
        Preconditions.checkNotNull(canSubscribeNum, "Failed to get canSubscribeNum for accountId=" + accountId
                    + ", there may has some bug for account!!!");
        return canSubscribeNum.intValue();
    }
    
    @Override
    public List<SubscribeCommodityClass> plan(
            QuotationPlatformEnv platformEnv
            , List<QuotationAccount> allAccounts
            , List<ContractRegisterRule> contractRegisterRules
            , IPlannerDataProvider provider) throws Exception {
        AppLog.i("Plan start for platformEnv=" + platformEnv);
        
        mPlatformEnv = platformEnv;
        mDataProvider = provider;
        initAvaiableAccounts(allAccounts);
        
        List<SubscribeCommodityClass> resultList = new ArrayList<>();
        
        for (ContractRegisterRule rule : contractRegisterRules) {
            if (rule.getPlatformEnv() != platformEnv) {
                continue;
            }
            
            if (mRules.containsKey(generateRegisterRuleKey(rule))) {
                continue;
            }
            
            if (rule.getActiveType() == null) {
                continue;
            }
            
            SledCommodity commodity = mDataProvider.getCommodity(rule.getSledCommodityId());
            Preconditions.checkNotNull(commodity, "Failed to get commodity for sledCommodityId=" + rule.getSledCommodityId());
            
            int subscribeNum = 0 ;
            if (rule.getActiveType() == ContractActiveType.FIXED_CODE) {
                subscribeNum = 1;
            } else {
                subscribeNum = rule.getActiveShowCount() + rule.getInactiveShowCount();
            }
            
            if (subscribeNum <= 0) {
                AppLog.i("NO subcribe is need for sledCommodityId=" + rule.getSledCommodityId());
                continue;
            }
            
            mRules.put(generateRegisterRuleKey(rule), rule);
            QuotationAccount masterQA = calSubscribeAccount(DeploySet.MASTER, subscribeNum, commodity);
            if (masterQA == null) {
                // 主区域无法订阅，则直接跳过
                continue;
            }
            
            SubscribeCommodityClass scc = new SubscribeCommodityClass();
            scc.setClassId(IDGenerator.generateClassId());
            scc.setSledExchangeMic(commodity.getExchangeMic());
            scc.setSledCommodityId(rule.getSledCommodityId());
            scc.setSledCommodityType((short)commodity.getSledCommodityType().getValue());
            scc.setSledCommodityCode(commodity.getSledCommodityCode());
            scc.setPlatformEnv(mPlatformEnv);
            scc.setActiveType(rule.getActiveType());
            
            if (rule.getActiveType() == ContractActiveType.ACTIVE_MONTH) {
                ContractActiveRule activeRule = mDataProvider.getActiveRule(rule.getSledCommodityId());
                Preconditions.checkNotNull(activeRule, "Failed to get ActiveRule for sledCommodityId=" + rule.getSledCommodityId());
                
                List<Integer> activeMonths = ActiveRuleHelper.getActiveMonths(activeRule);
                if (rule.getActiveShowCount() > 0 && activeMonths.isEmpty()) {
                    Preconditions.checkState(false, 
                        "Failed to process Active for sledCommodityId=" + rule.getSledCommodityId() 
                              + ", activeMonths=" + activeMonths 
                              + ", activeShowNum=" + rule.getActiveShowCount());
                }
            
                List<Integer> inactiveMonths = ActiveRuleHelper.getInactiveMonths(activeRule);
                if (rule.getInactiveShowCount() > 0 && inactiveMonths.isEmpty()) {
                    Preconditions.checkState(false, "Faild to process InActive for sledCommodityId=" + rule.getSledCommodityId()
                        + ", inactiveMonths=" + inactiveMonths
                        + ", inactiveShowNum=" + rule.getInactiveShowCount());
                }
            
                scc.setActiveMonths(activeMonths);
                scc.setInactiveMonths(inactiveMonths);
                scc.setActiveSubscribeNum(rule.getActiveShowCount());
                scc.setInActiveSubscribeNum(rule.getInactiveShowCount());
            } else {
                Preconditions.checkArgument(rule.getActiveType() == ContractActiveType.FIXED_CODE);
                Preconditions.checkArgument(StringUtils.isNotBlank(rule.getFixedCode()));
                scc.setFixedContractCode(StringUtils.trim(rule.getFixedCode()));
            }
            
            scc.setSubscribeAccounts(new ArrayList<>());
            
            List<SubscribeAccountClass> masterSACList = new ArrayList<>();
            masterSACList.add(new SubscribeAccountClass()
                    .setSubscribeNum(subscribeNum)
                    .setQuotationAccountId(masterQA.getAccountId())
                    .setQuotationDeploySet(masterQA.getDeploySet()));
            reduceCanSubscribeNum(masterQA.getAccountId(), subscribeNum);
            scc.getSubscribeAccounts().add(masterSACList);
            
            QuotationAccount slaveQA = this.calSubscribeAccount(DeploySet.SLAVE, subscribeNum, commodity);
            if (slaveQA != null) {
                List<SubscribeAccountClass> slaveSACList = new ArrayList<>();
                slaveSACList.add(new SubscribeAccountClass()
                        .setSubscribeNum(subscribeNum)
                        .setQuotationAccountId(slaveQA.getAccountId())
                        .setQuotationDeploySet(slaveQA.getDeploySet()));
                reduceCanSubscribeNum(slaveQA.getAccountId(), subscribeNum);
                
                scc.getSubscribeAccounts().add(slaveSACList);
            }
            
            AppLog.i("Add " + scc + ", subscribeNum=" + subscribeNum);
            resultList.add(scc);
        }
        
        return resultList;
    }
    
    private QuotationAccount calSubscribeAccount(DeploySet deploySet
            , int subscribeNum
            , SledCommodity commodity) throws IdException {
        if (commodity.getSledCommodityType() != SledCommodityType.FUTURES) {
            AppLog.i("Commodity type " + commodity.getSledCommodityType() + " is not supported for " + commodity.getSledCommodityId());
            return null;
        }
        
        List<QuotationAccount> supportedQuotationAccountList = getSupportedAccounts(deploySet, commodity);
        if (supportedQuotationAccountList.isEmpty()) {
            AppLog.i("Not Enough Accounts support for sledCommodityId=" + commodity.getSledCommodityId()
                    + ", exchangeMic=" + commodity.getExchangeMic()
                    + ", commodityCode=" + commodity.getSledCommodityCode()
                    + ", deploySet=" + deploySet
                    + ", supportedQuotationAccountList.size=" + supportedQuotationAccountList.size()) ;
            return null;
        }
        
        // 过滤掉剩余订阅数量不足
        Map<Long, QuotationAccount> canSubscribeAccounts = new HashMap<>();
        for (QuotationAccount account : supportedQuotationAccountList) {
            int canSubscribeNum = getCanSubscribeNum(account.getAccountId());
            if (canSubscribeNum < subscribeNum) {
                continue;
            }
            canSubscribeAccounts.put(account.getAccountId(), account);
        }
        
        if (canSubscribeAccounts.isEmpty()) {
            AppLog.i("Not Enough Accounts support after filter for sledCommodityId=" + commodity.getSledCommodityId()
                    + ", exchangeMic=" + commodity.getExchangeMic()
                    + ", commodityCode=" + commodity.getSledCommodityCode()
                    + ", deploySet=" + deploySet);
            return null;
        }
        
        // 从可订阅账号中选取分数值最低的
        long minScore = Long.MAX_VALUE;
        QuotationAccount minScoreAccount = null;
        
        for(Entry<Long, QuotationAccount> accountEntry : canSubscribeAccounts.entrySet()) {
            long score = calAccountAbilityScore(accountEntry.getValue());
            if (score < minScore) {
                minScore = score;
                minScoreAccount = accountEntry.getValue();
            }
        }
        
        Preconditions.checkState(minScoreAccount != null, "calSubscribeAC should has minScoreAccount");
        return minScoreAccount;
    }
    
    private long calAccountAbilityScore(QuotationAccount account) {
        QuotationAccountSupport accountSupport = mDataProvider.getAccountSupport(account.getAccountId());
        if (accountSupport == null || !accountSupport.isSetMicSupport()) {
            // 理论上不应该出现
            Preconditions.checkState(false, "calAccountAbilityScore should not has no accountSupport");
        }
        
        long score = accountSupport.getMicSupportSize() * 100; // 基础分值，支持的交易所越多，基础分值越高
        for (Entry<String, MicSupportCommodity> e: accountSupport.getMicSupport().entrySet()) {
            if (e.getValue().getSupportType() == SupportType.ALL) {
                score += mDataProvider.getExchangeCommodityTotalNum(e.getKey());
            } else {
                score += e.getValue().getSupportCommodityIds().size();
            }
        }
        return score;
    }
    
    private List<QuotationAccount> getSupportedAccounts(
            DeploySet deploySet
            , SledCommodity commodity) {
        List<QuotationAccount> resultList = new ArrayList<>();
        
        for (QuotationAccount account : mAvaiableAccounts.get(deploySet).values()) {
            QuotationAccountSupport accountSupport = mDataProvider.getAccountSupport(account.getAccountId());
            if (accountSupport == null || !accountSupport.isSetMicSupport()) {
                AppLog.i("No AccountSupport for  accountId=" + account.getAccountId());
                continue;
            }
            
            MicSupportCommodity supportCommodity 
                = accountSupport.getMicSupport().get(commodity.getExchangeMic());
            if (supportCommodity == null) {
                continue;
            }
            if (supportCommodity.getSupportType() == SupportType.ALL) {
                resultList.add(account);
                continue;
            }
            if (supportCommodity.isSetSupportCommodityIds() 
                    && supportCommodity.getSupportCommodityIds().contains(commodity.getSledCommodityId())) {
                resultList.add(account);
                continue;
            }
        }
        
        return resultList;
    }
}
