package xueqiao.quotation.account.api;

import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.storage.QuotationAccountTable;
import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationAccountPage;
import xueqiao.quotation.account.thriftapi.ReqQuotationAccountOption;

import java.sql.Connection;

public class HandleQuotationAccount {

    public QuotationAccountPage queryQuotationAccount(ReqQuotationAccountOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<QuotationAccountPage>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            protected QuotationAccountPage onQuery(Connection connection) throws Exception {

                return new QuotationAccountTable(connection).query(option, pageOption);
            }
        }.query();
    }

    public long addQuotationAccount(QuotationAccount account) throws ErrorInfo {

        return new DBTransactionHelper<Long>(ConfigurationProperty.instance().getDataSource(null)) {
            QuotationAccountTable table;
            long accountId;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new QuotationAccountTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                QuotationAccount item = table.queryForUpdate(account.getAccountName(), account.getBrokerId(), account.getPlatform(), false);
                if (item != null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record exists.");
                }
                accountId = table.add(account);
            }

            @Override
            public Long getResult() {
                return accountId;
            }
        }.execute().getResult();
    }

    public void updateQuotationAccount(QuotationAccount account) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            QuotationAccountTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new QuotationAccountTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                QuotationAccount item = table.queryForUpdate(account.getAccountId(), false);
                if (item == null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record not found.");
                }
                table.update(account);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void delete(long accountId) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            QuotationAccountTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new QuotationAccountTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                table.delete(accountId);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }
}
