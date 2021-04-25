package xueqiao.quotation.account.api;

import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.storage.SubscribeQuoteStateTaskTable;
import xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask;

import java.sql.Connection;

public class HandleSQSTask {


    public void add() throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new SubscribeQuoteStateTaskTable(getConnection()).add();
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public SubcribeQuoteStateTask queryLatest() throws ErrorInfo {

        return new DBQueryHelper<SubcribeQuoteStateTask>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            protected SubcribeQuoteStateTask onQuery(Connection connection) throws Exception {
                SubcribeQuoteStateTask task = new SubscribeQuoteStateTaskTable(connection).queryLatest();
                if (task == null) {
                    task = new SubcribeQuoteStateTask();
                }
                return task;
            }
        }.query();
    }

    public void removeEarlySQSTask(long taskId) throws ErrorInfo {

        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                new SubscribeQuoteStateTaskTable(getConnection()).removeEarly(taskId);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }
}
