package xueqiao.quotation.plan.bo.server.tasks;

import java.util.ArrayList;
import java.util.List;

import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import xueqiao.quotation.account.thriftapi.ContractRegisterRule;
import xueqiao.quotation.account.thriftapi.ContractRegisterRulePage;
import xueqiao.quotation.account.thriftapi.QueryOrderType;
import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationAccountOrderBy;
import xueqiao.quotation.account.thriftapi.QuotationAccountPage;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.account.thriftapi.ReqContractRegisterRuleOption;
import xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption;
import xueqiao.quotation.account.thriftapi.client.QuotationAccountDaoStub;
import xueqiao.quotation.plan.bo.EGenPreviewStatus;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;
import xueqiao.quotation.plan.bo.server.core.Globals;
import xueqiao.quotation.plan.bo.server.core.IPlannerDataProvider;
import xueqiao.quotation.plan.bo.server.core.PlannerFactory;
import xueqiao.quotation.plan.bo.server.persistance.struct.PlanState;
import xueqiao.quotation.plan.bo.server.persistance.table.PlanStateTable;
import xueqiao.quotation.plan.bo.server.persistance.table.SCClassTable;

public class PlanGenerateTask implements Runnable {
    
    private List<QuotationAccount> getAllAcounts() throws Exception {
        int pageIndex = 0;
        final int pageSize = 50;
        
        List<QuotationAccount> resultList = new ArrayList<>();
        while(true) {
            QuotationAccountPage accountPage = new QuotationAccountDaoStub().reqQuotationAccount(
                    new ReqQuotationAccountOption().setOrderBy(QuotationAccountOrderBy.CREATE_TIMESTAMP).setOrderType(QueryOrderType.ASC)
                    , new IndexedPageOption().setPageIndex(pageIndex).setPageSize(pageSize));
            if (accountPage.isSetPage()) {
                resultList.addAll(accountPage.getPage());
            }
            if (accountPage.getPageSize() < pageSize) {
                break;
            }
            ++pageIndex;
        }
        
        return resultList;
    }
    
    private List<ContractRegisterRule> getAllRegisterRule() throws Exception {
        int pageIndex = 0;
        final int pageSize = 50;
        
        List<ContractRegisterRule> resultList = new ArrayList<>();
        while(true) {
            ContractRegisterRulePage rulePage = new QuotationAccountDaoStub().reqContractRegisterRule(
                    new ReqContractRegisterRuleOption()
                    ,  new IndexedPageOption().setPageIndex(pageIndex).setPageSize(pageSize));
            if (rulePage.isSetPage()) {
                resultList.addAll(rulePage.getPage());
            }
            if (rulePage.getPageSize() < pageSize) {
                break;
            }
            ++pageIndex;
        }
        return resultList;
    }
    
    private List<SubscribeCommodityClass> planProcess() throws Exception {
        List<SubscribeCommodityClass> resultSCClassList = new ArrayList<>();
        
        List<QuotationAccount> allAccounts = getAllAcounts();
        List<ContractRegisterRule> rules = getAllRegisterRule();
        
        IPlannerDataProvider dataProvider = PlannerFactory.createPlannerDataProvider();
        resultSCClassList.addAll(PlannerFactory.createPlanner().plan(QuotationPlatformEnv.SIM, allAccounts, rules, dataProvider));
        resultSCClassList.addAll(PlannerFactory.createPlanner().plan(QuotationPlatformEnv.REAL, allAccounts, rules, dataProvider));
        
        return resultSCClassList;
    }
    
    private void planGenerate() throws ErrorInfo {
        AppLog.i("Start planGenerate...");
        
        new DBTransactionHelper<Void>(new DalSetDataSource(Globals.getDalSetRoleName(), "thread_quotplan_gen", false, 0)) {
            private boolean needUpdate = true;
            private PlanState currentState;
            
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                currentState = new PlanStateTable(getConnection()).getCurrentState();
                if (currentState.getPreviewStatus() != EGenPreviewStatus.PREVIEW_GENTASK_RUNNING) {
                    needUpdate = false;
                    AppLog.i("No preview gentask need running!!!");
                    return ;
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                if (!needUpdate) {
                    return ;
                }
                
                PlanState newState = new PlanState();
                
                List<SubscribeCommodityClass> scClassList = null;
                try {
                    scClassList = planProcess();
                } catch (Throwable e) {
                    AppLog.e(e.getMessage(), e);
                    
                    newState.setPreviewStatus(EGenPreviewStatus.PREVIEW_GENFAILED);
                    newState.setPreviewStateMsg("Exception ocurrs:" + e.getMessage());
                    new PlanStateTable(getConnection()).updateState(newState);
                    return ;
                }
                    
                SCClassTable previewTable = null;
                if (currentState.getCurrentSCClassIndex() == 0) {
                    previewTable = new SCClassTable(getConnection(), 1);
                } else {
                    previewTable = new SCClassTable(getConnection(), 0);
                }
                
                // 首先清空原有预览表
                previewTable.deleteAll();
                
                // 添加生成的订阅策略类，TODO 可优化为批量添加, 加快速度
                for (SubscribeCommodityClass scClass : scClassList) {
                    previewTable.addSCClass(scClass);
                }
                
                // 更新计划状态
                newState.setPreviewStatus(EGenPreviewStatus.PREVIEW_FINISHED);
                newState.setPreviewStateMsg("");
                new PlanStateTable(getConnection()).updateState(newState);
            }

            @Override
            public Void getResult() {
                return null;
            }
            
        }.execute();
        
        AppLog.i("PlanGenerate finished!");
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                planGenerate();
                return ;
            } catch (Throwable e) {
                // 正常情况下是DB出现相关问题，导致无法继续，这个时候需要重试
                AppLog.e(e.getMessage());
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
