package xueqiao.quotation.plan.bo.server.persistance.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.base.sql.SqlQueryBuilder.ConditionType;
import org.soldier.platform.db_helper.TableHelper;

import com.google.common.base.Preconditions;

import xueqiao.quotation.plan.bo.EGenPreviewStatus;
import xueqiao.quotation.plan.bo.server.persistance.struct.PlanState;

public class PlanStateTable extends TableHelper<PlanState> {

    public PlanStateTable(Connection conn) {
        super(conn);
    }

    public void updateState(PlanState newState) throws SQLException {
        Preconditions.checkNotNull(newState);
        
        PreparedFields pf = new PreparedFields();
        if (newState.getPreviewStatus() != null) {
            pf.addShort("Fpreview_status", (short)newState.getPreviewStatus().getValue());
        }
        if (newState.getPreviewStateMsg() != null) {
            pf.addString("Fpreivew_statemsg", newState.getPreviewStateMsg().trim());
        }
        if (newState.getCurrentSCClassIndex() != null) {
            pf.addShort("Fcurrent_scclass_index", newState.getCurrentSCClassIndex());
            pf.addLong("Fswitch_scclass_timestampms", System.currentTimeMillis());
        }
        if (newState.getCurrentSCItemIndex() != null) {
            pf.addShort("Fcurrent_scitem_index", newState.getCurrentSCItemIndex());
            pf.addLong("Fswitch_scitem_timestampms", System.currentTimeMillis());
        }
        if (newState.getNeedInitSCItems() != null) {
            if (newState.getNeedInitSCItems()) {
                pf.addByte("Fneed_init_scitems", (byte)1);
            } else {
                pf.addByte("Fneed_init_scitems", (byte)0);
            }
        }
        pf.addLong("Flastupdate_timestampms", System.currentTimeMillis());
        
        super.update(pf, "Fkey=?", 1);
    }
    
    public PlanState getCurrentState() throws SQLException {
        return getCurrentState(false);
    }
    
    public PlanState getCurrentState(boolean forUpdate) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        queryBuilder.addFieldCondition(ConditionType.AND, "Fkey=?", 1);
        return super.getItem(queryBuilder, forUpdate);
    }
    
    @Override
    public PlanState fromResultSet(ResultSet rs) throws Exception {
        PlanState result = new PlanState();
        result.setPreviewStatus(EGenPreviewStatus.findByValue(rs.getShort("Fpreview_status")));
        result.setPreviewStateMsg(rs.getString("Fpreivew_statemsg"));
        result.setLastUpdateTimestampMs(rs.getLong("Flastupdate_timestampms"));
        result.setCurrentSCClassIndex(rs.getShort("Fcurrent_scclass_index"));
        result.setSwitchSCClassTimestampMs(rs.getLong("Fswitch_scclass_timestampms"));
        result.setCurrentSCItemIndex(rs.getShort("Fcurrent_scitem_index"));
        result.setSwitchSCItemTimestampMs(rs.getLong("Fswitch_scitem_timestampms"));
        result.setNeedInitSCItems(rs.getByte("Fneed_init_scitems") != 0);
        return result;
    }

    @Override
    protected String getTableName() throws SQLException {
        return "tplan_state";
    }
    
}
