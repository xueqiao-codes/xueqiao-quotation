package xueqiao.quotation.account.storage;

import com.antiy.error_code.ErrorCodeInner;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
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
import java.util.Map;

public class QuotationAccountTable extends TableHelper<QuotationAccount> {

    // TODO

    private final static String TABLE_NAME = "t_quotation_account";
    private final static String FACCOUNT_ID = "Faccount_id";

    private final static String FACCOUNT_NAME = "Faccount_name";
    private final static String FACCOUNT_PWD = "Faccount_pwd";
    private final static String FNICK_NAME = "Fnick_name";
    private final static String FQUOTATION_PLATFORM = "Fquotation_platform";
    private final static String FQUOTATION_PLATFORM_ENV = "Fquotation_platform_env";
    private final static String FBROKER_ID = "Fbroker_id";
    private final static String FBROKER_ACCESS_ID = "Fbroker_access_id";
    private final static String FACCOUNT_PROPERTIES = "Faccount_properties";
    private final static String FACCOUNT_STATE = "Faccount_state";
    private final static String FACCOUNT_ACCESS_STATE = "Faccount_access_state";
    private final static String FINVALID_REASON = "Finvalid_reason";
    private final static String FINVALID_ERROR_CODE = "Finvalid_error_code";
    private final static String FAPI_RET_CODE = "Fapi_ret_code";
    private final static String FMAX_REGISTER_COUNT = "Fmax_register_count";

    private final static String FDEPLOY_SET = "Fdeploy_set";

    private final static String FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private final static String FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public QuotationAccountTable(Connection conn) {
        super(conn);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public QuotationAccount fromResultSet(ResultSet resultSet) throws Exception {
        QuotationAccount quotationAccount = new QuotationAccount();
        quotationAccount.setAccountId(resultSet.getLong(FACCOUNT_ID));
        quotationAccount.setAccountName(resultSet.getString(FACCOUNT_NAME));
        quotationAccount.setAccountpwd(resultSet.getString(FACCOUNT_PWD));
        quotationAccount.setNickName(resultSet.getString(FNICK_NAME));
        quotationAccount.setPlatform(QuotationTechPlatform.findByValue(resultSet.getInt(FQUOTATION_PLATFORM)));
        quotationAccount.setBrokerId(resultSet.getInt(FBROKER_ID));
        quotationAccount.setBrokerAccessId(resultSet.getInt(FBROKER_ACCESS_ID));

        Map<String, String> map = new Gson().fromJson(resultSet.getString(FACCOUNT_PROPERTIES), new TypeToken<Map<String, String>>() {
        }.getType());

        quotationAccount.setAccountProperties(map);
        quotationAccount.setAccountState(QuotationAccountState.findByValue(resultSet.getInt(FACCOUNT_STATE)));
        quotationAccount.setAccessState(QuotationAccountAccessState.findByValue(resultSet.getInt(FACCOUNT_ACCESS_STATE)));
        quotationAccount.setInvalidReason(resultSet.getString(FINVALID_REASON));
        quotationAccount.setInvalidErrorCode(resultSet.getInt(FINVALID_ERROR_CODE));
        quotationAccount.setApiRetCode(resultSet.getInt(FAPI_RET_CODE));
        quotationAccount.setMaxRegisterCount(resultSet.getInt(FMAX_REGISTER_COUNT));
        quotationAccount.setCreateTimestamp(resultSet.getLong(FCREATE_TIMESTAMP));
        quotationAccount.setLastModifyTimestamp(resultSet.getLong(FLAST_MODIFY_TIMESTAMP));
        quotationAccount.setPlatformEnv(QuotationPlatformEnv.findByValue(resultSet.getInt(FQUOTATION_PLATFORM_ENV)));

        quotationAccount.setDeploySet(DeploySet.findByValue(resultSet.getInt(FDEPLOY_SET)));
        return quotationAccount;
    }

    public QuotationAccount queryForUpdate(String accountName, int brokerId, QuotationTechPlatform techPlatform, boolean isForUpdate) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_NAME + "=?", accountName);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FBROKER_ID + "=?", brokerId);
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM + "=?", techPlatform.getValue());
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public QuotationAccount queryForUpdate(long accountId, boolean isForUpdate) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_ID + "=?", accountId);
        return super.getItem(sqlQueryBuilder, isForUpdate);
    }

    public long add(QuotationAccount account) throws ErrorInfo, SQLException {
        Preconditions.checkNotNull(account);
        Preconditions.checkArgument(StringUtils.isNotEmpty(account.getAccountName()));
        Preconditions.checkArgument(StringUtils.isNotEmpty(account.getAccountpwd()));
        Preconditions.checkArgument(account.getBrokerId() > 0);
        Preconditions.checkArgument(account.getBrokerAccessId() > 0);
        Preconditions.checkNotNull(account.getPlatform());
        Preconditions.checkNotNull(account.getPlatformEnv());

        PreparedFields fields = getPreparedFields(account);
        if (!account.isSetAccountProperties()) {
            fields.addString(FACCOUNT_PROPERTIES, "");
        }

        long accountId;
        try {
            accountId = ConfigurationProperty.instance().getQuotationAccountIdMaker().createId();
        } catch (IdException e) {
            AppLog.e(e.getMessage(), e);
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "QuotationAccount Id Maker create id fail.");
        }
        fields.addLong(FACCOUNT_ID, accountId);

        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FCREATE_TIMESTAMP, now);
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.insert(fields);
        return accountId;
    }

    public void update(QuotationAccount account) throws SQLException {
        Preconditions.checkNotNull(account);
        Preconditions.checkArgument(account.getAccountId() > 0);
        PreparedFields fields = getPreparedFields(account);
        long now = System.currentTimeMillis() / 1000;
        fields.addLong(FLAST_MODIFY_TIMESTAMP, now);
        super.update(fields, FACCOUNT_ID + "=?", account.getAccountId());
    }

    private PreparedFields getPreparedFields(QuotationAccount account) {
        PreparedFields fields = new PreparedFields();

        if (account.isSetAccountName()) {
            fields.addString(FACCOUNT_NAME, account.getAccountName());
        }
        if (account.isSetAccountpwd()) {
            fields.addString(FACCOUNT_PWD, account.getAccountpwd());
        }
        if (account.isSetNickName()) {
            fields.addString(FNICK_NAME, account.getNickName());
        }
        if (account.isSetPlatform()) {
            fields.addInt(FQUOTATION_PLATFORM, account.getPlatform().getValue());
        }
        if (account.isSetBrokerId()) {
            fields.addInt(FBROKER_ID, account.getBrokerId());
        }
        if (account.isSetBrokerAccessId()) {
            fields.addInt(FBROKER_ACCESS_ID, account.getBrokerAccessId());
        }
        if (account.isSetAccountProperties()) {
            if (account.getAccountPropertiesSize() > 0) {
                fields.addString(FACCOUNT_PROPERTIES, new Gson().toJson(account.getAccountProperties()));
            } else {
                fields.addString(FACCOUNT_PROPERTIES, "");
            }
        }
        if (account.isSetAccountState()) {
            fields.addInt(FACCOUNT_STATE, account.getAccountState().getValue());
        }
        if (account.isSetAccessState()) {
            fields.addInt(FACCOUNT_ACCESS_STATE, account.getAccessState().getValue());
        }
        if (account.isSetInvalidReason()) {
            fields.addString(FINVALID_REASON, account.getInvalidReason());
        }
        if (account.isSetInvalidErrorCode()) {
            fields.addInt(FINVALID_ERROR_CODE, account.getInvalidErrorCode());
        }
        if (account.isSetApiRetCode()) {
            fields.addInt(FAPI_RET_CODE, account.getApiRetCode());
        }
        if (account.isSetMaxRegisterCount()) {
            fields.addInt(FMAX_REGISTER_COUNT, account.getMaxRegisterCount());
        }
        if (account.isSetPlatformEnv()) {
            fields.addInt(FQUOTATION_PLATFORM_ENV, account.getPlatformEnv().getValue());
        }
        if (account.isSetDeploySet()) {
            fields.addInt(FDEPLOY_SET, account.getDeploySet().getValue());
        }
        return fields;
    }

    public QuotationAccountPage query(ReqQuotationAccountOption option, IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder sqlQueryBuilder = super.prepareSqlQueryBuilder();
        QuotationAccountPage page = new QuotationAccountPage();
        if (option != null) {
            if (option.isSetAccountIds() && option.getAccountIdsSize() > 0) {
                sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_ID, option.getAccountIds());
            }
            if (option.isSetBrokerIds() && option.getBrokerIdsSize() > 0) {
                sqlQueryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, FBROKER_ID, option.getBrokerIds());
            }
            if (option.isSetPlatform()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM + "=?", option.getPlatform().getValue());
            }
            if (option.isSetPlatformEnv()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FQUOTATION_PLATFORM_ENV + "=?", option.getPlatformEnv().getValue());
            }
            if (option.isSetAccountNamePartical()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FACCOUNT_NAME + " like ?", "%" + option.getAccountNamePartical() + "%");
            }
            if (option.isSetNickNamePartical()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FNICK_NAME + " like ?", "%" + option.getNickNamePartical() + "%");
            }
            if (option.isSetDeploySet()) {
                sqlQueryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, FDEPLOY_SET + "=?", option.getDeploySet().getValue());
            }

            SqlQueryBuilder.OrderType type = SqlQueryBuilder.OrderType.ASC;
            if (option.isSetOrderType()) {
                if (QueryOrderType.DESC.equals(option.getOrderType())) {
                    type = SqlQueryBuilder.OrderType.DESC;
                }
            }

            if (option.isSetOrderBy()) {
                if (QuotationAccountOrderBy.CREATE_TIMESTAMP.equals(option.getOrderBy())) {
                    sqlQueryBuilder.setOrder(type, FCREATE_TIMESTAMP);
                }
                if (QuotationAccountOrderBy.ACCOUNT_ID.equals(option.getOrderBy())) {
                    sqlQueryBuilder.setOrder(type, FACCOUNT_ID);
                }
            }
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

        List<QuotationAccount> list = super.getItemList(sqlQueryBuilder);
        return page.setTotal(total).setPage(list);
    }

    public void delete(long accountId) throws SQLException {
        super.delete(FACCOUNT_ID + "=?", accountId);
    }
}
