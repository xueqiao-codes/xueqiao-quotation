package xueqiao.quotation.plan.bo.server.tasks;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.svr_platform.client.TRequestOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import com.google.common.base.Preconditions;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledCommodity;
import com.longsheng.xueqiao.contract.standard.thriftapi.SledContract;

import xueqiao.quotation.account.thriftapi.ContractActiveType;
import xueqiao.quotation.account.thriftapi.client.QuotationAccountDaoAsyncStub;
import xueqiao.quotation.plan.bo.SubscribeAccountClass;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;
import xueqiao.quotation.plan.bo.SubscribeContractItem;
import xueqiao.quotation.plan.bo.server.core.Globals;
import xueqiao.quotation.plan.bo.server.core.IDGenerator;
import xueqiao.quotation.plan.bo.server.core.IPlannerDataProvider;
import xueqiao.quotation.plan.bo.server.core.PlannerFactory;
import xueqiao.quotation.plan.bo.server.ext.ActiveRuleHelper;
import xueqiao.quotation.plan.bo.server.persistance.struct.PlanState;
import xueqiao.quotation.plan.bo.server.persistance.table.PlanStateTable;
import xueqiao.quotation.plan.bo.server.persistance.table.SCClassTable;
import xueqiao.quotation.plan.bo.server.persistance.table.SCItemTable;

/**
 * @author 每次切换Preview后，需要重新初始化一次
 */
public class SCItemInitTask implements Runnable {
    
    private List<SubscribeContractItem> getSCItemsResult(SubscribeCommodityClass scClass
            , List<SledContract> subscribeContracts) throws IdException {
        if (scClass.getActiveType() == ContractActiveType.ACTIVE_MONTH) {
            Preconditions.checkArgument(subscribeContracts.size() 
                == scClass.getActiveSubscribeNum() + scClass.getInActiveSubscribeNum()
                , "Unexpected subscribeContracts size for sledCommodityId=" + scClass.getSledCommodityId()
                    + ", sledExchangeMic=" + scClass.getSledExchangeMic()
                    + ", sledCommodityCode=" + scClass.getSledCommodityCode());
        } else {
            Preconditions.checkArgument(subscribeContracts.size() == 1);
        }
        
        List<SubscribeContractItem> resultItemList = new ArrayList<>();
        for (int index = 0; index < subscribeContracts.size(); ++index) {
            SledContract subscribeContract = subscribeContracts.get(index);
            if (subscribeContract.getSledContractId() <= 0) {
                // 填充
                continue;
            }
            for (List<SubscribeAccountClass> sacList : scClass.getSubscribeAccounts()) {
                
                SubscribeContractItem scItem = new SubscribeContractItem();
                scItem.setClassId(scClass.getClassId());
                scItem.setItemId(IDGenerator.generateItemId());
                scItem.setPlatformEnv(scClass.getPlatformEnv());
                scItem.setSledExchangeMic(scClass.getSledExchangeMic());
                scItem.setSledCommodityId(scClass.getSledCommodityId());
                scItem.setSledCommodityType(scClass.getSledCommodityType());
                scItem.setSledCommodityCode(scClass.getSledCommodityCode());
                scItem.setSledContractCode(subscribeContract.getSledContractCode());
                scItem.setSledContractId(subscribeContract.getSledContractId());
                
                int sacTotal = 0;
                for (int sacIndex = 0; sacIndex < sacList.size(); ++ sacIndex) {
                    sacTotal += sacList.get(sacIndex).getSubscribeNum();
                    if (index < sacTotal) {
                        scItem.setQuotationAccountId(sacList.get(sacIndex).getQuotationAccountId());
                        scItem.setQuotationDeploySet(sacList.get(sacIndex).getQuotationDeploySet());
                        break;
                    }
                }
                
                Preconditions.checkState(scItem.isSetQuotationAccountId() && scItem.getQuotationAccountId() > 0);
                
                if (index < scClass.getActiveSubscribeNum()) {
                    scItem.setIsForActive(true);
                }
                
                resultItemList.add(scItem);
            }
        }
        
        return resultItemList;
    }
    
    private List<SubscribeContractItem> processSCClass(long processTimestampMs
            , IPlannerDataProvider plannerDataProvider
            , SubscribeCommodityClass scClass) throws IdException {
        SledCommodity commodity = plannerDataProvider.getCommodity(scClass.getSledCommodityId());
        Preconditions.checkNotNull(commodity, "Found commodity failed for sledCommodityId=" + scClass.getSledCommodityId());
        
        List<SledContract> subscribeContracts = new ArrayList<>();
        if (scClass.getActiveType() == ContractActiveType.FIXED_CODE) {
            subscribeContracts.addAll(getSubscribeContractsFromContractCode(plannerDataProvider
                    , 1, processTimestampMs, scClass, Arrays.asList(scClass.getFixedContractCode())));
            return getSCItemsResult(scClass, subscribeContracts);
        }
        
        TimeZone tz = TimeZone.getTimeZone(commodity.getZoneId());
        Preconditions.checkNotNull(tz, "timezone found failed for sledCommodityId=" + scClass.getSledCommodityId() 
            + ", zoneId=" + commodity.getZoneId());
        
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTimeInMillis(processTimestampMs);
        
        // 当前的年月日
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        
        if (scClass.getActiveSubscribeNum() > 0) {
            List<String> activeContractCodes = ActiveRuleHelper.calContractWindow(
                    scClass.getActiveSubscribeNum() + 1
                    , scClass.getActiveMonths()
                    , currentYear
                    , currentMonth);
            subscribeContracts.addAll(getSubscribeContractsFromContractCode(plannerDataProvider
                    , scClass.getActiveSubscribeNum(), processTimestampMs, scClass, activeContractCodes));
        }
        
        if (scClass.getInActiveSubscribeNum() > 0) {
            List<String> inactiveContractCodes = ActiveRuleHelper.calContractWindow(scClass.getInActiveSubscribeNum() + 1
                    , scClass.getInactiveMonths()
                    , currentYear
                    , currentMonth);
            subscribeContracts.addAll(getSubscribeContractsFromContractCode(plannerDataProvider
                    , scClass.getInActiveSubscribeNum(), processTimestampMs, scClass, inactiveContractCodes));
        }
        
        return getSCItemsResult(scClass, subscribeContracts);
    }
    
    private List<SledContract> getSubscribeContractsFromContractCode(
            IPlannerDataProvider plannerDataProvider
            , int needSize
            , long processTimestampMs
            , SubscribeCommodityClass scClass
            , List<String> contractCodes) {
        List<SledContract> resultList = new ArrayList<>();
        for (int index = 0; index < contractCodes.size(); ++index) {
            if (resultList.size() >= needSize) {
                break;
            }
            
            SledContract contract = plannerDataProvider.getContract(scClass.getSledCommodityId()
                    , contractCodes.get(index)
                    , scClass.getPlatformEnv());
            if (contract == null) {
                // 合约在合约系统不存在， 极有可能是在交易所还存在这个合约，先预留空位
                contract = new SledContract();
                contract.setSledContractId(-1);
                contract.setSledContractCode(contractCodes.get(index));
                resultList.add(contract);
                continue;
            }
            
            // 跳过可能的当月过期合约
            if (index == 0 && contract.isSetContractExpDate()) {
                long expireTimestampMs = contract.getContractExpDate() * 1000;
                if (processTimestampMs >= expireTimestampMs) {
                    // 说明已经过期
                    continue;
                }
            }
            
            resultList.add(contract);
        }
        return resultList;
    }
    
    private List<SubscribeContractItem> generateSCItems(Connection conn, PlanState planState) throws Exception {
        List<SubscribeCommodityClass> scClassesList = new SCClassTable(conn, planState.getCurrentSCClassIndex()).getAll();
        IPlannerDataProvider plannerDataProvider = PlannerFactory.createPlannerDataProvider();
        
        List<SubscribeContractItem> resultList = new ArrayList<>();
        
        long processTimestampMs = System.currentTimeMillis();
        for (SubscribeCommodityClass scClass : scClassesList) {
            resultList.addAll(processSCClass(processTimestampMs, plannerDataProvider, scClass));
        }
        
        return resultList;
    }
    
    private void process() throws ErrorInfo {
        AppLog.i("process scitem init task...");
        
        new DBTransactionHelper<Void>(new DalSetDataSource(Globals.getDalSetRoleName(), "thread_quotplan_gen", false, 0)) {
            private boolean needUpdate = true;
            private PlanState currentState;
            
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                this.currentState = new PlanStateTable(getConnection()).getCurrentState();
                if (!currentState.getNeedInitSCItems()) {
                    needUpdate = false;
                    AppLog.i("No scitem init task need running!!!");
                    return ;
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                if (!needUpdate) {
                    return ;
                }
                
                SCItemTable prepareSCItemTable = null;
                if (currentState.getCurrentSCItemIndex() == 0) {
                    prepareSCItemTable = new SCItemTable(getConnection(), 1);
                } else {
                    prepareSCItemTable = new SCItemTable(getConnection(), 0);
                }
                
                List<SubscribeContractItem> scItemsList = null;
                
                try {
                    scItemsList = generateSCItems(getConnection(), currentState);
                } catch (Throwable e) {
                    AppLog.e(e.getMessage(), e);
                    // TODO 无法生成订阅列表条目，这个时候应该通知报警
                    throw e;
                }
                
                prepareSCItemTable.deleteAll(); // 首先清空原有数据
                
                if (scItemsList != null && !scItemsList.isEmpty()) {
                    for (SubscribeContractItem scItem : scItemsList) {
                        prepareSCItemTable.addSCItem(scItem);
                    }
                }
                
                // 切换索引，同时清除任务
                PlanState newState = new PlanState();
                newState.setCurrentSCItemIndex((short)prepareSCItemTable.getIndex());
                newState.setNeedInitSCItems(false);
                new PlanStateTable(getConnection()).updateState(newState);
            }

            @Override
            public Void getResult() {
                return null;
            }
            
        }.execute();
        
        TRequestOption option = new TRequestOption();
        option.setRetryTimes(10);
        try {
            new QuotationAccountDaoAsyncStub().send_notifySubscribeQuoteStateChange(RandomUtils.nextInt()
                    , 3000
                    , option);
        } catch (TException e) {
            AppLog.e(e.getMessage(), e);
        }
        
        AppLog.i("process scitem init task finished...");
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                process();
                break;
            } catch (Throwable e) {
                AppLog.e(e.getMessage(), e);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
