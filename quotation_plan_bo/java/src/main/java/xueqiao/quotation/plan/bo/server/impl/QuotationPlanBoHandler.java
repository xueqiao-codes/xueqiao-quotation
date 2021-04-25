package xueqiao.quotation.plan.bo.server.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import com.antiy.error_code.ErrorCodeInner;

import xueqiao.quotation.plan.bo.EGenPreviewStatus;
import xueqiao.quotation.plan.bo.EQuotationPlanBoErrorCode;
import xueqiao.quotation.plan.bo.GenPreviewState;
import xueqiao.quotation.plan.bo.QuerySubscribeContractItemOption;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;
import xueqiao.quotation.plan.bo.SubscribeContractItemPage;
import xueqiao.quotation.plan.bo.server.QuotationPlanBoAdaptor;
import xueqiao.quotation.plan.bo.server.core.Globals;
import xueqiao.quotation.plan.bo.server.core.IDGenerator;
import xueqiao.quotation.plan.bo.server.job.SCItemInitScheduleJob;
import xueqiao.quotation.plan.bo.server.persistance.struct.PlanState;
import xueqiao.quotation.plan.bo.server.persistance.table.PlanStateTable;
import xueqiao.quotation.plan.bo.server.persistance.table.SCClassTable;
import xueqiao.quotation.plan.bo.server.persistance.table.SCItemTable;
import xueqiao.quotation.plan.bo.server.tasks.PlanGenerateTask;
import xueqiao.quotation.plan.bo.server.tasks.SCItemInitTask;

public class QuotationPlanBoHandler extends QuotationPlanBoAdaptor {
    private Scheduler mJobScheduler;
    
    @Override
    public int InitApp(Properties props) {
        IDGenerator.init();
        
        try {
            DalSetProxy.getInstance().loadFromXml();
        } catch (Exception e) {
            AppLog.e(e.getMessage(), e);
            return -1;
        }
        
        Globals.getPlanGenerateThread().postTask(new PlanGenerateTask());
        Globals.getPlanGenerateThread().postTask(new SCItemInitTask());
        
        try {
            System.setProperty("log4j.logger.org.quartz", "INFO");
            mJobScheduler = StdSchedulerFactory.getDefaultScheduler();
            mJobScheduler.start();
            
            JobDetail jobDetail = JobBuilder.newJob(SCItemInitScheduleJob.class)
                    .withIdentity("scitem-init")
                    .build();
            
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("scitem-init-trigger")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                    .build();
            mJobScheduler.scheduleJob(jobDetail, trigger);
            
        } catch (SchedulerException e) {
            AppLog.e(e.getMessage(), e);
            return -1;
        }
        
        return 0;
    }
    
    private DataSource getDataSource(TServiceCntl oCntl) {
        return new DalSetDataSource(Globals.getDalSetRoleName(), oCntl.getDalSetServiceName(), false, 0);
    }
    
    @Override
    protected GenPreviewState startGenPreviewSCClasses(TServiceCntl oCntl)
            throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        GenPreviewState resultState = new DBTransactionHelper<GenPreviewState>(getDataSource(oCntl)) {
            private PlanState newPlanState;
            
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                PlanState currentState = new PlanStateTable(getConnection()).getCurrentState(true);
                if (currentState.getPreviewStatus() == EGenPreviewStatus.PREVIEW_GENTASK_RUNNING) {
                    throw new ErrorInfo(EQuotationPlanBoErrorCode.ERROR_GENPREVIEW_ISHANDING.getValue()
                            , "GenPreview is running!!!");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                newPlanState = new PlanState();
                newPlanState.setPreviewStatus(EGenPreviewStatus.PREVIEW_GENTASK_RUNNING);
                newPlanState.setPreviewStateMsg("Start generating preview!");
                newPlanState.setLastUpdateTimestampMs(System.currentTimeMillis());
                
                new PlanStateTable(getConnection()).updateState(newPlanState);
            }

            @Override
            public GenPreviewState getResult() {
                GenPreviewState resultState = new GenPreviewState();
                resultState.setStatus(newPlanState.getPreviewStatus());
                resultState.setStateMsg(newPlanState.getPreviewStateMsg());
                resultState.setLastUpdateTimestampMs(newPlanState.getLastUpdateTimestampMs());
                return resultState;
            }
        }.execute().getResult();
        
        Globals.getPlanGenerateThread().postTask(new PlanGenerateTask());
        
        return resultState;
    }
    
    @Override
    protected GenPreviewState getGenPreviewState(TServiceCntl oCntl)
            throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new DBQueryHelper<GenPreviewState>(getDataSource(oCntl)) {
            @Override
            protected GenPreviewState onQuery(Connection conn) throws Exception {
                PlanState planState = new PlanStateTable(conn).getCurrentState();
                GenPreviewState resultState = new GenPreviewState();
                resultState.setStatus(planState.getPreviewStatus());
                resultState.setStateMsg(planState.getPreviewStateMsg());
                resultState.setLastUpdateTimestampMs(planState.getLastUpdateTimestampMs());
                return resultState;
            }
        }.query();
    }
    
    @Override
    protected void commitPreviewSCClasses(TServiceCntl oCntl)
            throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new DBTransactionHelper<Void>(getDataSource(oCntl)) {
            private PlanState currentState;
            
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                this.currentState = new PlanStateTable(getConnection()).getCurrentState(true);
                if (currentState.getPreviewStatus() != EGenPreviewStatus.PREVIEW_FINISHED) {
                    throw new ErrorInfo(EQuotationPlanBoErrorCode.ERROR_PREVIEW_NOTEXIST.getValue()
                            , "Preview is not existed!");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                // 切换Index，然后清空原始使用表
                PlanState newState = new PlanState();
                if (currentState.getCurrentSCClassIndex() == 0) {
                    newState.setCurrentSCClassIndex((short)1);
                } else {
                    newState.setCurrentSCClassIndex((short)0);
                }
                newState.setPreviewStatus(EGenPreviewStatus.PREVIEW_EMPTY);
                newState.setPreviewStateMsg("");
                newState.setNeedInitSCItems(true);
                
                new PlanStateTable(getConnection()).updateState(newState);
            }

            @Override
            public Void getResult() {
                return null;
            }
            
        }.execute();
        
        Globals.getPlanGenerateThread().postTask(new SCItemInitTask());
    }
    
    @Override
    protected SubscribeContractItemPage querySubscribeContractItemPage(TServiceCntl oCntl
            , QuerySubscribeContractItemOption queryOption
            , org.soldier.platform.page.IndexedPageOption pageOption)
            throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        if (queryOption == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "queryOption should not be null");
        }
        if (pageOption == null) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageOption should not be null");
        }
        if (!pageOption.isSetPageIndex() || pageOption.getPageIndex() < 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageOption's pageIndex should set and >= 0");
        }
        if (!pageOption.isSetPageSize() || pageOption.getPageSize() <= 0) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "pageOption's pageSize should set and > 0");
        }
        
        return new DBQueryHelper<SubscribeContractItemPage>(getDataSource(oCntl)) {

            @Override
            protected SubscribeContractItemPage onQuery(Connection conn) throws Exception {
                return new SCItemTable(conn
                        , new PlanStateTable(conn).getCurrentState().getCurrentSCItemIndex())
                        .query(queryOption, pageOption);
            }
            
        }.query();
    }
    
    @Override
    protected List<SubscribeCommodityClass> getCurrentSCClasses(TServiceCntl oCntl)
            throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new DBQueryHelper<List<SubscribeCommodityClass>>(getDataSource(oCntl)) {

            @Override
            protected List<SubscribeCommodityClass> onQuery(Connection conn) throws Exception {
                return new SCClassTable(conn
                        , new PlanStateTable(conn).getCurrentState().getCurrentSCClassIndex())
                        .getAll();
            }
            
        }.query();
    }
    
    @Override
    protected List<SubscribeCommodityClass> getPreviewSCClasses(TServiceCntl oCntl)
            throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new DBQueryHelper<List<SubscribeCommodityClass>>(getDataSource(oCntl)) {

            @Override
            protected List<SubscribeCommodityClass> onQuery(Connection conn) throws Exception {
                PlanState currentState = new PlanStateTable(conn).getCurrentState();
                if (currentState.getPreviewStatus() != EGenPreviewStatus.PREVIEW_FINISHED) {
                    throw new ErrorInfo(EQuotationPlanBoErrorCode.ERROR_PREVIEW_NOTEXIST.getValue(), "Preview is not existed!");
                }
                
                SCClassTable scClassTable = null;
                if (currentState.getCurrentSCClassIndex() == 0) {
                    scClassTable = new SCClassTable(conn, 1);
                } else {
                    scClassTable = new SCClassTable(conn, 0);
                }
                return scClassTable.getAll();
            }
            
        }.query();
    }
    
    @Override
    public void destroy() {
    }
}
