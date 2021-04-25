package xueqiao.quotation.account.api;

import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.storage.ContractRegisterRuleTable;
import xueqiao.quotation.account.thriftapi.*;

import java.sql.Connection;

public class HandleContractRegisterRule {

    public ContractRegisterRulePage query(ReqContractRegisterRuleOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<ContractRegisterRulePage>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            protected ContractRegisterRulePage onQuery(Connection connection) throws Exception {
                return new ContractRegisterRuleTable(connection).query(option, pageOption);
            }
        }.query();
    }

    public void addContractRegisterRule(ContractRegisterRule registerRule) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {

            ContractRegisterRuleTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ContractRegisterRuleTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                ContractRegisterRule item = table.queryForUpdate(registerRule.getSledCommodityId(), registerRule.getPlatformEnv(), registerRule.getActiveType(), registerRule.getFixedCode(), false);
                if (item != null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record exists.");
                }
                int orderIndex = table.getLatestIndex(registerRule.getPlatformEnv());
                registerRule.setOrderIndex(orderIndex);
                table.add(registerRule);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void updateContractRegisterRule(ContractRegisterRule registerRule) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {

            ContractRegisterRuleTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ContractRegisterRuleTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                ContractRegisterRule item = table.queryForUpdate(registerRule.getSledCommodityId(),
                        registerRule.getPlatformEnv(),
                        registerRule.getActiveType(),
                        registerRule.getFixedCode(),
                        false);
                if (item == null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record not found.");
                }
                table.update(registerRule);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void delete(int sledCommodityId, QuotationPlatformEnv platformEnv, ContractActiveType activeType, String fixedCode) throws ErrorInfo {

        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {

            ContractRegisterRuleTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ContractRegisterRuleTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {

                table.delete(sledCommodityId, platformEnv, activeType, fixedCode);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void setCommodityRegisterOrderIndex(int sledCommodityId, QuotationPlatformEnv platformEnv, ContractActiveType activeType, String fixedCode, int orderIndex) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {

            ContractRegisterRuleTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ContractRegisterRuleTable(getConnection());
                ContractRegisterRule item = table.queryForUpdate(sledCommodityId, platformEnv, activeType, fixedCode, true);
                if (item == null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record not found.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                ContractRegisterRule item = table.query(orderIndex, platformEnv);
                if (item == null) {
                    ContractRegisterRule registerRule = new ContractRegisterRule();
                    registerRule.setSledCommodityId(sledCommodityId);
                    registerRule.setPlatformEnv(platformEnv);
                    registerRule.setOrderIndex(orderIndex);
                    registerRule.setFixedCode(fixedCode);
                    registerRule.setActiveType(activeType);
                    table.update(registerRule);
                } else {
                    table.setOrderIndex(sledCommodityId, platformEnv, activeType, fixedCode, orderIndex);
                }
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }
}
