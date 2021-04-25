package xueqiao.quotation.account.storage;

import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import xueqiao.quotation.account.thriftapi.SubcribeQuoteStateTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscribeQuoteStateTaskTable extends TableHelper<SubcribeQuoteStateTask> {


    private final static String TABLE_NAME = "t_subcribe_quote_state_task";
    private final static String FTASK_ID = "Ftask_id";
    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";

    public SubscribeQuoteStateTaskTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() throws SQLException {
        return TABLE_NAME;
    }

    @Override
    public SubcribeQuoteStateTask fromResultSet(ResultSet resultSet) throws Exception {
        SubcribeQuoteStateTask task = new SubcribeQuoteStateTask();
        task.setTaskId(resultSet.getLong(FTASK_ID));
        task.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        return task;
    }

    public SubcribeQuoteStateTask queryLatest() throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.setOrder(SqlQueryBuilder.OrderType.DESC, FCREATE_TIMESTAMP);
        return super.getItem(sqlQueryBuilder);
    }

    public void removeEarly(long taskId) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FTASK_ID + "=?", taskId);
        SubcribeQuoteStateTask task = super.getItem(sqlQueryBuilder);
        if (task == null) {
            return;
        }
        super.delete(FCREATE_TIMESTAMP + "<=?", task.getCreateTimestamp());
    }

    public void add() throws SQLException {
        PreparedFields fields = new PreparedFields();
        long now = System.currentTimeMillis();
        fields.addLong(FCREATE_TIMESTAMP, now);
        super.insert(fields);
    }
}
