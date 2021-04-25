package xueqiao.quotation.account.storage;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.quotation.account.thriftapi.ContractActiveRule;
import xueqiao.quotation.account.thriftapi.ContractActiveRulePage;
import xueqiao.quotation.account.thriftapi.ContractActiveType;
import xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ContractActiveRuleTable extends TableHelper<ContractActiveRule> {

    private final static String TABLE_NAME = "t_contract_active_rule";
    private final static String FSLED_COMMODITY_ID = "Fsled_commodity_id";
    private final static String FACTIVE_MONTH_MAP = "Factive_month_map";

    private final static String FCONTRACT_ACTIVE_TYPE = "Fcontract_active_type";
    private final static String FFIXED_CODE = "Ffixed_code";

    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private final static String FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public ContractActiveRuleTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public ContractActiveRule fromResultSet(ResultSet resultSet) throws Exception {
        ContractActiveRule rule = new ContractActiveRule();
        rule.setSledCommodityId(resultSet.getInt(FSLED_COMMODITY_ID));
        Map<Integer, Boolean> map = new Gson().fromJson(resultSet.getString(FACTIVE_MONTH_MAP), new TypeToken<Map<Integer, Boolean>>() {
        }.getType());
        rule.setActiveMonthMap(map);
        rule.setActiveType(ContractActiveType.findByValue(resultSet.getInt(FCONTRACT_ACTIVE_TYPE)));
        rule.setFixedCode(resultSet.getString(FFIXED_CODE));
        rule.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        rule.setLastModityTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        return rule;
    }

    public ContractActiveRule queryForUpdate(int sledCommodityId, ContractActiveType activeType, String fixedCode, boolean isForUpdate) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_COMMODITY_ID + "=?", sledCommodityId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FCONTRACT_ACTIVE_TYPE + "=?", activeType.getValue());
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FFIXED_CODE + "=?", fixedCode);
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public void add(ContractActiveRule activeRule) throws SQLException {
        Preconditions.checkNotNull(activeRule);
        Preconditions.checkArgument(activeRule.getSledCommodityId() > 0);

        PreparedFields fields = getPreparedFields(activeRule);
        fields.addInt(FSLED_COMMODITY_ID, activeRule.getSledCommodityId());
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.insert(fields);
    }

    private PreparedFields getPreparedFields(ContractActiveRule activeRule) {
        PreparedFields fields = new PreparedFields();

        if (activeRule.isSetActiveMonthMap()) {
            fields.addString(FACTIVE_MONTH_MAP, new Gson().toJson(activeRule.getActiveMonthMap()));
        }
        if (activeRule.isSetActiveType()) {
            fields.addInt(FCONTRACT_ACTIVE_TYPE, activeRule.getActiveType().getValue());
        } else {
            activeRule.setActiveType(ContractActiveType.ACTIVE_MONTH);
        }
        if (activeRule.isSetFixedCode()) {
            fields.addString(FFIXED_CODE, activeRule.getFixedCode());
        } else {
            activeRule.setFixedCode("");
        }
        return fields;
    }

    public void update(ContractActiveRule activeRule) throws SQLException {
        Preconditions.checkNotNull(activeRule);
        Preconditions.checkArgument(activeRule.getSledCommodityId() > 0);

        PreparedFields fields = getPreparedFields(activeRule);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FSLED_COMMODITY_ID + "=? and " + FCONTRACT_ACTIVE_TYPE + "=? and " + FFIXED_CODE + "=?",
                activeRule.getSledCommodityId(), activeRule.getActiveType().getValue(), activeRule.getFixedCode());
    }

    public ContractActiveRulePage query(ReqContractActiveRuleOption option, IndexedPageOption pageOption) throws SQLException {
        Preconditions.checkNotNull(option);

        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        if (option.isSetCommodityIds() && option.getCommodityIdsSize() > 0) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_COMMODITY_ID, option.getCommodityIds());
        }
        ContractActiveRulePage page = new ContractActiveRulePage();
        int total = 0;
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                total = super.getTotalNum(sqlQueryBuilder);
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                sqlQueryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }

        List<ContractActiveRule> list = super.getItemList(sqlQueryBuilder);
        return page.setPage(list).setTotal(total);
    }

    public void delete(int sledCommodityId, ContractActiveType activeType, String fixedCode) throws SQLException {
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        super.delete(FSLED_COMMODITY_ID + "=? and " + FCONTRACT_ACTIVE_TYPE + "=? and " + FFIXED_CODE + "=?",
                sledCommodityId, activeType.getValue(), fixedCode);
    }
}
