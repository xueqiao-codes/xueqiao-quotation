package xueqiao.quotation.account.config;

import net.qihoo.qconf.QconfException;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.comm.PlatformArgs;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.quotation.account.thriftapi.QuotationAccountDaoVariable;

import java.sql.Connection;
import java.util.Properties;

public class ConfigurationProperty {

    private String roleName;

    private static ConfigurationProperty config;

    private IdMaker quotationAccountIdMaker;

    private IdMaker qaSupportAbilityIdMaker;


    private ConfigurationProperty() {
    }

    public static ConfigurationProperty instance() {
        if (config == null) {
            synchronized (ConfigurationProperty.class) {
                if (config == null) {
                    config = new ConfigurationProperty();
                }
            }
        }
        return config;
    }

    public void init(Properties props) throws IdException, ErrorInfo {
        this.roleName = props.getProperty("contract_dao_role", "role_xueqiao_quotation");
        int quotationAccountIdmakerNum = Integer.parseInt(props.getProperty("quotationAccountIdMaker", "217"));
        int qaSupportAbilityIdMakerNum = Integer.parseInt(props.getProperty("quotationAccountSupportAbilityIdMaker", "218"));
        this.quotationAccountIdMaker = IdMakerFactory.getInstance().getIdMaker(quotationAccountIdmakerNum);
        this.qaSupportAbilityIdMaker = IdMakerFactory.getInstance().getIdMaker(qaSupportAbilityIdMakerNum);

        System.out.println("roleName=" + roleName);
        System.out.println("quotationAccountIdmakerNum=" + quotationAccountIdmakerNum);
        System.out.println("qaSupportAbilityIdMakerNum=" + qaSupportAbilityIdMakerNum);

        if (this.quotationAccountIdMaker == null) {
            throw new IdException("quotationAccountIdMaker create failed!");
        }

        if (roleName == null || "".equals(roleName)) {
            throw new IllegalArgumentException("role name not found.");
        }
        if (this.qaSupportAbilityIdMaker == null){
            throw new IdException("qaSupportAbilityIdMaker create failed!");
        }

        new DBQueryHelper<Void>(getDataSource(null)) {
            @Override
            protected Void onQuery(Connection connection) throws Exception {
                return null;
            }
        }.query();
    }

    public IdMaker getQuotationAccountIdMaker() {
        return this.quotationAccountIdMaker;
    }

    public String getRoleName() {
        return roleName;
    }

    public DalSetDataSource getDataSource(String serviceName) {
        if (StringUtils.isEmpty(serviceName)) {
            TServiceCntl oCntl = new TServiceCntl(QuotationAccountDaoVariable.serviceKey, "reqQuotationAccount", new PlatformArgs());
            serviceName = oCntl.getDalSetServiceName();
        }
        return new DalSetDataSource(roleName, serviceName, false, 0);
    }

    public IdMaker getQaSupportAbilityIdMaker() {
        return qaSupportAbilityIdMaker;
    }
}
