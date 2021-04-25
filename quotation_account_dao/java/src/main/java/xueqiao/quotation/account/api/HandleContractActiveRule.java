package xueqiao.quotation.account.api;

import com.antiy.error_code.ErrorCodeOuter;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.storage.ContractActiveRuleTable;
import xueqiao.quotation.account.thriftapi.ContractActiveRule;
import xueqiao.quotation.account.thriftapi.ContractActiveRulePage;
import xueqiao.quotation.account.thriftapi.ContractActiveType;
import xueqiao.quotation.account.thriftapi.ReqContractActiveRuleOption;

import java.sql.Connection;

public class HandleContractActiveRule {

    public ContractActiveRulePage reqContractActiveRule(ReqContractActiveRuleOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<ContractActiveRulePage>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            protected ContractActiveRulePage onQuery(Connection connection) throws Exception {
                return new ContractActiveRuleTable(connection).query(option, pageOption);
            }
        }.query();

    }

    public void addContractActiveRule(ContractActiveRule activeRule) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {

            ContractActiveRuleTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ContractActiveRuleTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                ContractActiveRule item = table.queryForUpdate(activeRule.getSledCommodityId(), activeRule.getActiveType(), activeRule.getFixedCode(), false);
                if (item != null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record exists.");
                }
                table.add(activeRule);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void updateContractActiveRule(ContractActiveRule activeRule) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {

            ContractActiveRuleTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new ContractActiveRuleTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                ContractActiveRule item = table.queryForUpdate(activeRule.getSledCommodityId(), activeRule.getActiveType(), activeRule.getFixedCode(), false);
                if (item == null) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record not found.");
                }
                table.update(activeRule);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void delete(int sledCommodityId, ContractActiveType activeType, String fixedCode) throws ErrorInfo {

        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {

            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new ContractActiveRuleTable(getConnection()).delete(sledCommodityId, activeType, fixedCode);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }
}
