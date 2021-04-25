package xueqiao.quotation.account.api;

import com.antiy.error_code.ErrorCodeOuter;
import org.apache.thrift.TException;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.storage.QuotationAccountRegisterAbilityTable;
import xueqiao.quotation.account.thriftapi.*;

import java.sql.Connection;
import java.util.*;

public class HandleAccountRegisterAbility {

    public AccountCommodityRegisterAbilityPage reqAccountCommodityRegisterAbility(ReqAccountCommodityRegisterAbilityOption option, IndexedPageOption pageOption) throws ErrorInfo {

        return new DBQueryHelper<AccountCommodityRegisterAbilityPage>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            protected AccountCommodityRegisterAbilityPage onQuery(Connection connection) throws Exception {
                QuotationAccountRegisterAbilityTable table = new QuotationAccountRegisterAbilityTable(connection);
                return table.query(option, pageOption);
            }
        }.query();
    }

    public void batAddAccountCommodityRegisterAbility(List<AccountCommodityRegisterAbility> abilities) throws ErrorInfo {

        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            QuotationAccountRegisterAbilityTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new QuotationAccountRegisterAbilityTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                for (AccountCommodityRegisterAbility ability : abilities) {
                    if (ability.isSetSupportType()) {
                        if (SupportType.SET.equals(ability.getSupportType())) {
                            if (ability.getSledCommodityId() <= 0) {
                                throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "SledCommodity id must set.");
                            }
                            AccountCommodityRegisterAbility item = table.queryForUpdate(ability.getAccountId(), ability.getSledCommodityId(), false);
                            if (item != null) {
                                continue;
                            }
                        }
                        if (SupportType.ALL.equals(ability.getSupportType())) {
                            if (ability.getSledExchangeId() <= 0) {
                                throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Sled exchange id must set.");
                            }

                            AccountCommodityRegisterAbility item = table.queryExchangeAllForUpdate(ability.getAccountId(), ability.getSledExchangeId(), false);
                            if (item != null) {
                                continue;
                            }
                            table.delete(ability.getAccountId(), ability.getSledExchangeId());
                        }
                    } else {
                        throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "SupportType must set.");
                    }

                    table.add(ability);
                }
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();

    }

    public void addAccountCommodityRegisterAbility(AccountCommodityRegisterAbility ability) throws ErrorInfo {
        List<AccountCommodityRegisterAbility> list = new ArrayList<>();
        list.add(ability);
        batAddAccountCommodityRegisterAbility(list);
    }

    public void removeAccountCommodityRegisterAbility(long supportAbilityId) throws ErrorInfo {
        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            QuotationAccountRegisterAbilityTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new QuotationAccountRegisterAbilityTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                AccountCommodityRegisterAbility item = table.queryForUpdate(supportAbilityId, false);
                if (item == null) {

                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Record not found.");
                }
                table.delete(supportAbilityId);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public void removeAccountCommodityRegisterAbilities(long accountId, int sledExchangeId) throws ErrorInfo {

        new DBTransactionHelper<Void>(ConfigurationProperty.instance().getDataSource(null)) {
            QuotationAccountRegisterAbilityTable table;

            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                table = new QuotationAccountRegisterAbilityTable(getConnection());
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                table.delete(accountId, sledExchangeId);
            }

            @Override
            public Void getResult() {
                return null;
            }
        }.execute();
    }

    public QuotationAccountSupportPage reqQuotationAccountSupport(ReqQuotationAccountSupportOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {

        AccountCommodityRegisterAbilityPage page = new DBQueryHelper<AccountCommodityRegisterAbilityPage>(ConfigurationProperty.instance().getDataSource(null)) {
            @Override
            protected AccountCommodityRegisterAbilityPage onQuery(Connection connection) throws Exception {
                QuotationAccountRegisterAbilityTable table = new QuotationAccountRegisterAbilityTable(connection);
                return table.query(option, null);
            }
        }.query();

        Map<Long, List<AccountCommodityRegisterAbility>> accountAbilities = new HashMap<>();
        for (AccountCommodityRegisterAbility ability : page.getPage()) {
            long accountId = ability.getAccountId();
            List<AccountCommodityRegisterAbility> list = accountAbilities.get(accountId);
            if (list == null) {
                list = new ArrayList<>();
                accountAbilities.put(accountId, list);
            }
            list.add(ability);
        }

        List<QuotationAccountSupport> supports = new ArrayList<>();
        for (long accountId : accountAbilities.keySet()) {
            QuotationAccountSupport support = new QuotationAccountSupport();
            support.setAccountId(accountId);
            List<AccountCommodityRegisterAbility> list = accountAbilities.get(accountId);
            Map<String, MicSupportCommodity> map = new HashMap<>();
            for (AccountCommodityRegisterAbility ability : list) {
                String mic = ability.getExchangeMic();
                MicSupportCommodity micSupportCommodity = map.get(mic);
                if (micSupportCommodity == null) {
                    micSupportCommodity = new MicSupportCommodity();
                    micSupportCommodity.setSupportType(ability.getSupportType());
                    map.put(mic, micSupportCommodity);
                }

                Set<Integer> commodityIds = micSupportCommodity.getSupportCommodityIds();
                if (commodityIds == null) {
                    commodityIds = new HashSet<>();
                    micSupportCommodity.setSupportCommodityIds(commodityIds);
                }
                commodityIds.add(ability.getSledCommodityId());
            }

            support.setMicSupport(map);
            supports.add(support);
        }
        QuotationAccountSupportPage supportPage = new QuotationAccountSupportPage();
        if (pageOption != null) {
            if (pageOption.isNeedTotalCount()) {
                supportPage.setTotal(supports.size());
            }
            if (pageOption.getPageIndex() > 0 && pageOption.getPageSize() > 0) {
                int index = pageOption.getPageIndex() * pageOption.getPageSize();
                int endIndex = Math.min(supports.size(), (pageOption.getPageIndex() * pageOption.getPageSize() + 1));
                if (index < supports.size()) {
                    for (; index < endIndex; index++) {
                        supportPage.addToPage(supports.get(index));
                    }
                }
                return supportPage;
            }
        }
        supportPage.setPage(supports);
        return supportPage;
    }
}
