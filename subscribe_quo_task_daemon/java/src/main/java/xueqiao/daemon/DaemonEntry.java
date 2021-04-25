package xueqiao.daemon;

import com.longsheng.xueqiao.contract.dao.thriftapi.ReqTSledContractOption;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledContract;
import com.longsheng.xueqiao.contract.dao.thriftapi.TSledContractPage;
import com.longsheng.xueqiao.contract.dao.thriftapi.client.ContractDaoServiceStub;
import com.longsheng.xueqiao.contract.standard.thriftapi.TechPlatformEnv;
import com.longsheng.xueqiao.contract.thriftapi.ContractVersion;
import com.longsheng.xueqiao.contract.thriftapi.DbLockingInfo;
import com.longsheng.xueqiao.contract.thriftapi.RemoveContractVersionOption;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import net.qihoo.qconf.Qconf;
import net.qihoo.qconf.QconfException;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask;
import xueqiao.quotation.account.thriftapi.client.QuotationAccountDaoStub;
import xueqiao.quotation.plan.bo.QuerySubscribeContractItemOption;
import xueqiao.quotation.plan.bo.SubscribeContractItem;
import xueqiao.quotation.plan.bo.SubscribeContractItemPage;
import xueqiao.quotation.plan.bo.client.QuotationPlanBoStub;

import java.util.*;

public class DaemonEntry {

    public static void main(String[] args) {

        // TODO REQ TASK
        // REQ QUOTATION PLAN BO
        // REQ SLED CONTRACT WITH SUBSCRIBE QUOTE STATE
        // IN BO NOT IN SLED ADD
        // IN SLED NOT IN BO REMOVE
        // REMOVE TASK

        TechPlatformEnv[] envs = {TechPlatformEnv.REAL, TechPlatformEnv.SIM};
        int retry = 0;
        boolean isdaily = false;
        do {

            try {
                SubcribeQuoteStateTask task = reqTask();
                AppLog.d("SubcribeQuoteStateTask: " + task);
                long taskId = task.getTaskId();
                if (taskId == 0) {
                    sleep();
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    if (hour == 1 && !isdaily) {
                        AppLog.w("Daily sync@hour:" + hour);
                        doSyncSubscribeState(envs);
                        addSyncJob();
                        retry = 0;
                        isdaily = true;
                    }
                    if (hour < 1) {
                        isdaily = false;
                    }
                    continue;
                }

                doSyncSubscribeState(envs);

                AppLog.d("remove task : " + taskId);
                removeTask(taskId);
                retry = 0;
                addSyncJob();
            } catch (ErrorInfo e) {
                AppLog.e(e.getErrorMsg(), e);
                retry++;
                if (retry > 5) {
                    sendSms("sync subscribe quotation state fail.");
                    break;
                }
            } catch (Exception e) {
                AppLog.e(e.getMessage(), e);
                retry++;
                if (retry > 5) {
                    sendSms("sync subscribe quotation state fail.");
                    break;
                }
            }
            sleep();
        } while (true);
    }

    private static void doSyncSubscribeState(TechPlatformEnv[] envs) throws TException {
        for (TechPlatformEnv env : envs) {
            AppLog.d("env: " + env);
            List<SubscribeContractItem> planBo = reqPlanBo(map2PlanBoEnv(env));
            AppLog.d("SubscribeContractItem: " + planBo);
            for (SubscribeContractItem item : planBo) {
                setSubscribeState(true, item.getSledContractId());
            }
        }
    }

    private static void addSyncJob() throws TException {
        ContractDaoServiceStub stub = new ContractDaoServiceStub();
        DbLockingInfo lockInfo = stub.reqDbLockingInfo();
        if (!lockInfo.isIsLocked()) {
            RemoveContractVersionOption option = new RemoveContractVersionOption();
            option.setAll(true);
            stub.removeContractVersion(option);
            ContractVersion version = new ContractVersion();
            version.setFileMD5("");
            version.setFilePath("");
            stub.addContractVersion(version);
        }
    }

    private static QuotationPlatformEnv map2PlanBoEnv(TechPlatformEnv env) {
        if (TechPlatformEnv.REAL.equals(env)) {
            return QuotationPlatformEnv.REAL;
        } else if (TechPlatformEnv.SIM.equals(env)) {
            return QuotationPlatformEnv.SIM;
        } else {
            return QuotationPlatformEnv.NONE;
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static SubcribeQuoteStateTask reqTask() throws TException {
        QuotationAccountDaoStub stub = new QuotationAccountDaoStub();
        return stub.reqLatestSQSTask();
    }

    private static void removeTask(long taskId) throws TException {
        QuotationAccountDaoStub stub = new QuotationAccountDaoStub();
        stub.removeEarlySQSTask(taskId);
    }

    private static void setSubscribeState(boolean isSubscribed, long sledContractId) throws TException {
        int num = isSubscribed ? 1 : 0;
        ContractDaoServiceStub contractDaoServiceStub = new ContractDaoServiceStub();
        TSledContract tSledContract = new TSledContract();
        tSledContract.setSledContractId((int) sledContractId);
        tSledContract.setSubscribeXQQuote(num);
        contractDaoServiceStub.updateTSledContract(tSledContract);
    }

    private static List<SubscribeContractItem> reqPlanBo(QuotationPlatformEnv env) throws TException {

        QuotationPlanBoStub planBoStub = new QuotationPlanBoStub();
        int pageIndex = 0;
        int pageSize = 20;
        int total = 0;
        List<SubscribeContractItem> resultList = new ArrayList<>();
        do {
            QuerySubscribeContractItemOption reqOption = new QuerySubscribeContractItemOption();
            reqOption.setPlatformEnv(env);
            IndexedPageOption pageOption = new IndexedPageOption();
            pageOption.setPageIndex(pageIndex);
            pageOption.setPageSize(pageSize);
            pageOption.setNeedTotalCount(true);
            SubscribeContractItemPage page = planBoStub.querySubscribeContractItemPage(reqOption, pageOption);
            total = page.getTotalCount();
            if (page.getResultListSize() > 0) {
                resultList.addAll(page.getResultList());
            }
            pageIndex++;
        } while (total > pageIndex * pageSize);
        return resultList;
    }

    private static void sendSms(String msg) {
        try {
            String env = "";
            try {
                env = Qconf.getConf("platform/environment");
            } catch (QconfException e) {
                e.printStackTrace();
            }
            new GooseAoStub().sendUserNotificationSms("18576421291", env + ": " + msg);
        } catch (TException e1) {
            e1.printStackTrace();
        }
    }
}
