package xueqiao.quotation.account.thriftapi.server.impl;


import java.util.List;
import java.util.Properties;

import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.dal_set.DalSetProxy;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.page.IndexedPageOption;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.quotation.account.api.*;
import xueqiao.quotation.account.config.ConfigurationProperty;
import xueqiao.quotation.account.thriftapi.*;
import xueqiao.quotation.account.thriftapi.server.QuotationAccountDaoAdaptor;

import javax.security.auth.login.Configuration;

public class QuotationAccountDaoHandler extends QuotationAccountDaoAdaptor {
    @Override
    public int InitApp(Properties props) {
        try {
            DalSetProxy.getInstance().loadFromXml();
        } catch (Exception e) {
            AppLog.e("DAL init fail.", e);
            e.printStackTrace();
            return -1;
        }
        try {
            ConfigurationProperty.instance().init(props);

        } catch (Exception e) {
            e.printStackTrace();
            AppLog.e("Config init fail.", e);
            return -1;
        }
        AppLog.e("======= SERVICE START =======");
        return 0;
    }

    @Override
    protected ContractActiveRulePage reqContractActiveRule(TServiceCntl oCntl, ReqContractActiveRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new HandleContractActiveRule().reqContractActiveRule(option, pageOption);
    }

    @Override
    protected ContractRegisterRulePage reqContractRegisterRule(TServiceCntl oCntl, ReqContractRegisterRuleOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new HandleContractRegisterRule().query(option, pageOption);
    }

    @Override
    protected QuotationAccountPage reqQuotationAccount(TServiceCntl oCntl, ReqQuotationAccountOption option, org.soldier.platform.page.IndexedPageOption pageOption) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new HandleQuotationAccount().queryQuotationAccount(option, pageOption);
    }

    @Override
    protected void addContractActiveRule(TServiceCntl oCntl, ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleContractActiveRule().addContractActiveRule(rule);
    }

    @Override
    protected void addContractRegisterRule(TServiceCntl oCntl, ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleContractRegisterRule().addContractRegisterRule(rule);
    }

    @Override
    protected long addQuotationAccount(TServiceCntl oCntl, QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        return new HandleQuotationAccount().addQuotationAccount(account);
    }

    @Override
    protected void updateContractActiveRule(TServiceCntl oCntl, ContractActiveRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleContractActiveRule().updateContractActiveRule(rule);
    }

    @Override
    protected void updateContractRegisterRule(TServiceCntl oCntl, ContractRegisterRule rule) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleContractRegisterRule().updateContractRegisterRule(rule);
    }

    @Override
    protected void updateQuotationAccount(TServiceCntl oCntl, QuotationAccount account) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleQuotationAccount().updateQuotationAccount(account);
    }

    @Override
    protected void removeContractActiveRule(TServiceCntl oCntl, int sledCommodityId, ContractActiveType activeType, String fixedCode) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleContractActiveRule().delete(sledCommodityId, activeType, fixedCode);
    }

    @Override
    protected void removeContractRegisterRule(TServiceCntl oCntl, int sledCommodityId, QuotationPlatformEnv platformEnv, ContractActiveType activeType, String fixedCode) throws ErrorInfo, TException {
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        new HandleContractRegisterRule().delete(sledCommodityId, platformEnv, activeType, fixedCode);
    }

    @Override
    protected void removeQuotationAccount(TServiceCntl oCntl, long accountId) throws org.soldier.platform.svr_platform.comm.ErrorInfo, org.apache.thrift.TException {
        new HandleQuotationAccount().delete(accountId);
    }

    @Override
    protected AccountCommodityRegisterAbilityPage reqAccountCommodityRegisterAbility(TServiceCntl oCntl, ReqAccountCommodityRegisterAbilityOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        return new HandleAccountRegisterAbility().reqAccountCommodityRegisterAbility(option, pageOption);
    }

    @Override
    protected void addAccountCommodityRegisterAbility(TServiceCntl oCntl, AccountCommodityRegisterAbility ability) throws ErrorInfo, TException {
        new HandleAccountRegisterAbility().addAccountCommodityRegisterAbility(ability);
    }

    @Override
    protected void removeAccountCommodityRegisterAbility(TServiceCntl oCntl, long abilityId) throws ErrorInfo, TException {
        new HandleAccountRegisterAbility().removeAccountCommodityRegisterAbility(abilityId);
    }

    @Override
    protected void batAddAccountCommodityRegisterAbility(TServiceCntl oCntl, List<AccountCommodityRegisterAbility> abilities) throws ErrorInfo, TException {
        new HandleAccountRegisterAbility().batAddAccountCommodityRegisterAbility(abilities);
    }

    @Override
    protected void removeAccountCommodityRegisterAbilities(TServiceCntl oCntl, long accountId, int sledExchangeId) throws ErrorInfo, TException {
        new HandleAccountRegisterAbility().removeAccountCommodityRegisterAbilities(accountId, sledExchangeId);
    }

    @Override
    protected QuotationAccountSupportPage reqQuotationAccountSupport(TServiceCntl oCntl, ReqQuotationAccountSupportOption option, IndexedPageOption pageOption) throws ErrorInfo, TException {
        return new HandleAccountRegisterAbility().reqQuotationAccountSupport(option, pageOption);
    }

    @Override
    protected void setCommodityRegisterOrderIndex(TServiceCntl oCntl, int sledCommodityId, QuotationPlatformEnv platformEnv, int orderIndex, ContractActiveType activeType, String fixedCode) throws ErrorInfo, TException {
        if (activeType == null) {
            activeType = ContractActiveType.ACTIVE_MONTH;
        }
        if (fixedCode == null) {
            fixedCode = "";
        }
        new HandleContractRegisterRule().setCommodityRegisterOrderIndex(sledCommodityId, platformEnv, activeType, fixedCode, orderIndex);
    }

    @Override
    protected void notifySubscribeQuoteStateChange(TServiceCntl oCntl) throws ErrorInfo, TException {
        new HandleSQSTask().add();
    }

    @Override
    protected SubcribeQuoteStateTask reqLatestSQSTask(TServiceCntl oCntl) throws ErrorInfo, TException {
        return new HandleSQSTask().queryLatest();
    }

    @Override
    protected void removeEarlySQSTask(TServiceCntl oCntl, long taskId) throws ErrorInfo, TException {
        new HandleSQSTask().removeEarlySQSTask(taskId);
    }

    @Override
    public void destroy() {
    }
}
