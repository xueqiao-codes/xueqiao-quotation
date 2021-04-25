package xueqiao.quotation.account.storage;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import org.soldier.base.logger.AppLog;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.thriftapi.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuotationAccountRegisterAbilityTable extends TableHelper<AccountCommodityRegisterAbility> {

    private final static String TABLE_NAME = "t_quotation_account_support";
    private final static String FABILITY_ID = "Fsupport_ability_id";
    private final static String FACCOUNT_ID = "Faccount_id";
    private final static String FSLED_COMMODITY_ID = "Fsled_commodity_id";
    private final static String FSLED_EXCHANGE_ID = "Fsled_exchange_id";
    private final static String FSUPPORT_TYPE = "Fsupport_type";
    private final static String FEXCHANGE_MIC = "Fexchange_mic";

    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private final static String FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public QuotationAccountRegisterAbilityTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() throws SQLException {
        return TABLE_NAME;
    }

    @Override
    public AccountCommodityRegisterAbility fromResultSet(ResultSet resultSet) throws Exception {
        AccountCommodityRegisterAbility ability = new AccountCommodityRegisterAbility();
        ability.setRegisterAbilityId(resultSet.getLong(FABILITY_ID));
        ability.setAccountId(resultSet.getLong(FACCOUNT_ID));
        ability.setSledCommodityId(resultSet.getInt(FSLED_COMMODITY_ID));
        ability.setSledExchangeId(resultSet.getInt(FSLED_EXCHANGE_ID));
        ability.setSupportType(SupportType.findByValue(resultSet.getInt(FSUPPORT_TYPE)));
        ability.setExchangeMic(resultSet.getString(FEXCHANGE_MIC));
        ability.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        ability.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        return ability;
    }

    public void add(AccountCommodityRegisterAbility ability) throws SQLException, ErrorInfo {
        Preconditions.checkNotNull(ability);
        Preconditions.checkArgument(ability.getAccountId() > 0);
        Preconditions.checkArgument(ability.getSledExchangeId() > 0);

        long now = System.currentTimeMillis() / 1000;
        PreparedFields fields = new PreparedFields();
        fields.addLong(FACCOUNT_ID, ability.getAccountId());
        fields.addInt(FSLED_COMMODITY_ID, ability.getSledCommodityId());
        fields.addInt(FSLED_EXCHANGE_ID, ability.getSledExchangeId());
        if (ability.isSetSupportType()) {
            fields.addInt(FSUPPORT_TYPE, ability.getSupportType().getValue());
        }
        if (ability.isSetExchangeMic()) {
            fields.addString(FEXCHANGE_MIC, ability.getExchangeMic());
        }
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);

        long abilityId;
        try {
            abilityId = ConfigurationProperty.instance().getQaSupportAbilityIdMaker().createId();
        } catch (IdException e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "quotation support ability id maker create id fail.");
        }
        fields.addLong(FABILITY_ID, abilityId);

        super.insert(fields);
    }

    public AccountCommodityRegisterAbilityPage query(ReqAccountCommodityRegisterAbilityOption relateInfoOption, IndexedPageOption pageOption) throws SQLException {
        Preconditions.checkNotNull(relateInfoOption);
        AccountCommodityRegisterAbilityPage page = new AccountCommodityRegisterAbilityPage();
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        if (relateInfoOption.isSetAccountId()) {
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_ID + "=?", relateInfoOption.getAccountId());
        }
        if (relateInfoOption.isSetSledCommodityIds() && relateInfoOption.getSledCommodityIdsSize() > 0) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_COMMODITY_ID, relateInfoOption.getSledCommodityIds());
        }
        if (relateInfoOption.isSetSledExchangeIds() && relateInfoOption.getSledExchangeIdsSize() > 0) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_EXCHANGE_ID, relateInfoOption.getSledExchangeIds());
        }
        if (relateInfoOption.isSetExchangeMics() && relateInfoOption.getExchangeMicsSize() > 0) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FEXCHANGE_MIC, relateInfoOption.getExchangeMics());
        }
        if (relateInfoOption.isSetSupportType()){
            sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSUPPORT_TYPE+"=?", relateInfoOption.getSupportType().getValue());
        }

        int total = 0;
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                total = super.getTotalNum(sqlQueryBuilder);
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                sqlQueryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }

        List<AccountCommodityRegisterAbility> list = super.getItemList(sqlQueryBuilder);
        return page.setTotal(total).setPage(list);
    }

    public AccountCommodityRegisterAbilityPage query(ReqQuotationAccountSupportOption option, IndexedPageOption pageOption) throws SQLException {
        Preconditions.checkNotNull(option);
        AccountCommodityRegisterAbilityPage page = new AccountCommodityRegisterAbilityPage();
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        if (option.isSetAccountIds() && option.getAccountIdsSize() > 0) {
            sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_ID, option.getAccountIds());
        }

        int total = 0;
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                total = super.getTotalNum(sqlQueryBuilder);
            }
            if (pageOption.isSetPageIndex() && pageOption.isSetPageSize()) {
                sqlQueryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
            }
        }

        List<AccountCommodityRegisterAbility> list = super.getItemList(sqlQueryBuilder);
        return page.setTotal(total).setPage(list);
    }

    public AccountCommodityRegisterAbility queryForUpdate(long accountId, int sledCommodityId, boolean isForUpdate) throws SQLException {
        Preconditions.checkArgument(accountId > 0);
        Preconditions.checkArgument(sledCommodityId > 0);

        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_ID + "=?", accountId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_COMMODITY_ID + "=?", sledCommodityId);
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public AccountCommodityRegisterAbility queryExchangeAllForUpdate(long accountId, int sledExchange, boolean isForUpdate) throws SQLException {
        Preconditions.checkArgument(accountId > 0);
        Preconditions.checkArgument(sledExchange > 0);

        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_ID + "=?", accountId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSLED_EXCHANGE_ID + "=?", sledExchange);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FSUPPORT_TYPE + "=?", SupportType.ALL);
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public AccountCommodityRegisterAbility queryForUpdate(long supportAbilityId, boolean isForUpdate) throws SQLException {
        Preconditions.checkArgument(supportAbilityId > 0);

        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FABILITY_ID + "=?", supportAbilityId);
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public void delete(long abilityId) throws SQLException {
        super.delete(FABILITY_ID + "=? ", abilityId);
    }

    public void delete(long accountId, long sledExchangeId) throws SQLException {
        super.delete(FACCOUNT_ID + "=? and " + FSLED_EXCHANGE_ID + " =? ", accountId, sledExchangeId);
    }
}
