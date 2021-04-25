package xueqiao.quotation.access.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import com.google.common.base.Preconditions;

import xueqiao.quotation.account.thriftapi.QueryOrderType;
import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationAccountOrderBy;
import xueqiao.quotation.account.thriftapi.QuotationAccountPage;
import xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption;
import xueqiao.quotation.account.thriftapi.client.QuotationAccountDaoStub;

public class AccountManager extends Thread {
    
    public static interface IProcessor {
        public void onCheckAccounts(List<QuotationAccount> accounts) throws Exception;
    }
    
    private IProcessor mProcessor;
    
    public AccountManager(IProcessor processor) {
        Preconditions.checkNotNull(processor);
        
        this.mProcessor = processor;
    }
    
    private List<QuotationAccount> getAllAccounts() throws ErrorInfo, TException {
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
    
    public void checkLoop() {
        while(true) {
            try {
                AppLog.i("checkLoop start...");
                mProcessor.onCheckAccounts(getAllAccounts());
                AppLog.i("checkLoop finished...");
            } catch (Throwable e) {
                AppLog.e(e.getMessage(), e);
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
            }
        }
    }
    
}
