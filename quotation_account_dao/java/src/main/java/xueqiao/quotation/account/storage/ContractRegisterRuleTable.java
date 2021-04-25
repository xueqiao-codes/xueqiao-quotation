package xueqiao.quotation.account.storage;

import com.google.common.base.Preconditions;
import org.apache.commons.dbutils.QueryRunner;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.quotation.account.thriftapi.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContractRegisterRuleTable extends TableHelper<ContractRegisterRule> {

    private final static String TABLE_NAME = "t_contract_register_rule";
    private final static String FSLED_COMMODITY_ID = "Fsled_commodity_id";
    private final static String FACTIVE_SHOW_COUNT = "Factive_show_count";
    private final static String FINACTIVE_SHOW_COUNT = "Finactive_show_count";
    private final static String FQUOTATION_PLATFORM_ENV = "Fquotation_platform_env";

    private final static String FORDER_INDEX = "Forder_index";

    private final static String FCONTRACT_ACTIVE_TYPE = "Fcontract_active_type";
    private final static String FFIXED_CODE = "Ffixed_code";

    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private final static String FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public ContractRegisterRuleTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public ContractRegisterRule fromResultSet(ResultSet resultSet) throws Exception {
        ContractRegisterRule rule = new ContractRegisterRule();
        rule.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        rule.setLastModityTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        rule.setActiveShowCount(resultSet.getInt(FACTIVE_SHOW_COUNT));
        rule.setInactiveShowCount(resultSet.getInt(FINACTIVE_SHOW_COUNT));
        rule.setSledCommodityId(resultSet.getInt(FSLED_COMMODITY_ID));
        rule.setPlatformEnv(QuotationPlatformEnv.findByValue(resultSet.getInt(FQUOTATION_PLATFORM_ENV)));
        rule.setOrderIndex(resultSet.getInt(FORDER_INDEX));
        rule.setActiveType(ContractActiveType.findByValue(resultSet.getInt(FCONTRACT_ACTIVE_TYPE)));
        rule.setFixedCode(resultSet.getString(FFIXED_CODE));
        return rule;
    }

    public ContractRegisterRule queryForUpdate(int sledCommodityId, QuotationPlatformEnv platformEnv, ContractActiveType activeType, String fixedCode, boolean isForUpdate) throws SQLException {
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_COMMODITY_ID + "=?", sledCommodityId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM_ENV + "=?", platformEnv.getValue());
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FCONTRACT_ACTIVE_TYPE + "=?", activeType.getValue());
        if (fixedCode == null) {
            fixedCode = "";
        }
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FFIXED_CODE + "=?", fixedCode);
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public ContractRegisterRule query(int orderIndex,   QuotationPlatformEnv platformEnv) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FORDER_INDEX + "=? ", orderIndex);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM_ENV+"=?", platformEnv.getValue());
        return super.getItem(sqlQueryBuilder);
    }

    public void add(ContractRegisterRule registerRule) throws SQLException {
        Preconditions.checkNotNull(registerRule);
        Preconditions.checkArgument(registerRule.getSledCommodityId() > 0);
        Preconditions.checkArgument(registerRule.isSetPlatformEnv());

        PreparedFields fields = getPreparedFields(registerRule);

        fields.addInt(FSLED_COMMODITY_ID, registerRule.getSledCommodityId());
        fields.addInt(FQUOTATION_PLATFORM_ENV, registerRule.getPlatformEnv().getValue());
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);

        super.insert(fields);
    }

    private PreparedFields getPreparedFields(ContractRegisterRule registerRule) {
        PreparedFields fields = new PreparedFields();
        if (registerRule.isSetActiveShowCount()) {
            fields.addInt(FACTIVE_SHOW_COUNT, registerRule.getActiveShowCount());
        }
        if (registerRule.isSetInactiveShowCount()) {
            fields.addInt(FINACTIVE_SHOW_COUNT, registerRule.getInactiveShowCount());
        }
        if (registerRule.isSetOrderIndex()) {
            fields.addInt(FORDER_INDEX, registerRule.getOrderIndex());
        }
        if (registerRule.isSetActiveType()) {
            fields.addInt(FCONTRACT_ACTIVE_TYPE, registerRule.getActiveType().getValue());
        }
        if (registerRule.isSetFixedCode()) {
            fields.addString(FFIXED_CODE, registerRule.getFixedCode());
        }
        return fields;
    }

    public void update(ContractRegisterRule registerRule) throws SQLException {
        Preconditions.checkNotNull(registerRule);
        Preconditions.checkArgument(registerRule.getSledCommodityId() > 0);

        if (!registerRule.isSetActiveType()) {
            registerRule.setActiveType(ContractActiveType.ACTIVE_MONTH);
        }
        if (!registerRule.isSetFixedCode()) {
            registerRule.setFixedCode("");
        }

        PreparedFields fields = getPreparedFields(registerRule);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FSLED_COMMODITY_ID + "=? and " + FQUOTATION_PLATFORM_ENV + "=? and " + FCONTRACT_ACTIVE_TYPE + "=? and " + FFIXED_CODE + "=?",
                registerRule.getSledCommodityId(), registerRule.getPlatformEnv().getValue(), registerRule.getActiveType().getValue(), registerRule.getFixedCode());
    }

    public int getLatestIndex(QuotationPlatformEnv platformEnv) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.setOrder(SqlQueryBuilder.OrderType.DESC, FORDER_INDEX);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM_ENV + " =?", platformEnv.getValue());
        ContractRegisterRule item = super.getItem(sqlQueryBuilder, true);
        int index = 0;
        if (item != null) {
            index = item.getOrderIndex();
        }
        return index + 1;
    }

    public ContractRegisterRulePage query(ReqContractRegisterRuleOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        ContractRegisterRulePage page = new ContractRegisterRulePage();
        if (option != null) {
            if (option.isSetCommodityIds() && option.getCommodityIdsSize() > 0) {
                sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_COMMODITY_ID, option.getCommodityIds());
            }
            if (option.isSetPlatformEnv()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM_ENV + "=?", option.getPlatformEnv().getValue());
            }
        }

        sqlQueryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, FORDER_INDEX);

        int total = 0;
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                total = super.getTotalNum(sqlQueryBuilder);
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                sqlQueryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }

        List<ContractRegisterRule> list = super.getItemList(sqlQueryBuilder);
        return page.setTotal(total).setPage(list);
    }

    public void delete(int sledCommodityId, QuotationPlatformEnv platformEnv, ContractActiveType activeType, String fixedCode) throws SQLException {
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        super.delete(FSLED_COMMODITY_ID + "=? and " + FQUOTATION_PLATFORM_ENV + "=? and " + FCONTRACT_ACTIVE_TYPE + "=? and " + FFIXED_CODE + "=?",
                sledCommodityId, platformEnv.getValue(), activeType.getValue(), fixedCode);
    }

    public void setOrderIndex(int sledCommodityId, QuotationPlatformEnv platformEnv, ContractActiveType activeType, String fixedCode, int orderIndex) throws SQLException {
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        PreparedFields fields = new PreparedFields();
        fields.addInt(FORDER_INDEX, orderIndex);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, System.currentTimeMillis() / 1000);
        String reSortSql = "update t_contract_register_rule set Forder_index=Forder_index+1 where Fquotation_platform_env = ? and Forder_index >=?";

        new QueryRunner().update(this.getConnection(), reSortSql, platformEnv.getValue(), orderIndex);
        super.update(fields, FSLED_COMMODITY_ID + "=? and " + FQUOTATION_PLATFORM_ENV + "=? and " + FCONTRACT_ACTIVE_TYPE + "=? and " + FFIXED_CODE + "=?",
                sledCommodityId, platformEnv.getValue(), activeType.getValue(), fixedCode);
    }
}
