package xueqiao.quotation.plan.bo.server.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBStepHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import xueqiao.quotation.plan.bo.server.core.Globals;
import xueqiao.quotation.plan.bo.server.persistance.struct.PlanState;
import xueqiao.quotation.plan.bo.server.persistance.table.PlanStateTable;
import xueqiao.quotation.plan.bo.server.tasks.SCItemInitTask;

public class SCItemInitScheduleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        try {
            new DBStepHelper<Void>(new DalSetDataSource(Globals.getDalSetRoleName(), "thread_quotplan_gen", false, 0)) {

                @Override
                public void onPrepareData() throws ErrorInfo, Exception {
                }

                @Override
                public void onUpdate() throws ErrorInfo, Exception {
                    PlanState newState = new PlanState();
                    newState.setNeedInitSCItems(true);
                    new PlanStateTable(getConnection()).updateState(newState);
                }

                @Override
                public Void getResult() {
                    return null;
                }
                
            }.execute();
            
            Globals.getPlanGenerateThread().postTask(new SCItemInitTask());
            
        } catch (ErrorInfo e) {
            e.printStackTrace();
        }
        
    }
    
}
