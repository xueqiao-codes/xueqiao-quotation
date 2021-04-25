package xueqiao.quotation.plan.bo.server.persistance.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.base.sql.SqlQueryBuilder.ConditionType;
import org.soldier.base.sql.SqlQueryBuilder.OrderType;
import org.soldier.platform.db_helper.TableHelper;
import org.soldier.platform.page.IndexedPageOption;

import com.google.common.base.Preconditions;

import xueqiao.quotation.account.thriftapi.DeploySet;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.plan.bo.QuerySubscribeContractItemOption;
import xueqiao.quotation.plan.bo.SubscribeContractItem;
import xueqiao.quotation.plan.bo.SubscribeContractItemPage;

public class SCItemTable extends TableHelper<SubscribeContractItem> {
    private int mIndex;
    
    public SCItemTable(Connection conn, int index) {
        super(conn);
        Preconditions.checkArgument(index >= 0 && index <= 1);
        this.mIndex = index;
    }
    
    public int getIndex() {
        return mIndex;
    }
    
    public SubscribeContractItemPage query(QuerySubscribeContractItemOption queryOption
            , IndexedPageOption pageOption) throws SQLException {
        Preconditions.checkNotNull(queryOption);
        Preconditions.checkNotNull(pageOption);
        Preconditions.checkArgument(pageOption.getPageIndex() >= 0);
        Preconditions.checkArgument(pageOption.getPageSize() > 0);
        
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        if (queryOption.isSetPlatformEnv()) {
            queryBuilder.addFieldCondition(ConditionType.AND, "Fplatform_env=?", (byte)queryOption.getPlatformEnv().getValue());
        }
        if (queryOption.isSetQuotationAccountIds() && !queryOption.getQuotationAccountIds().isEmpty()) {
            queryBuilder.addInFieldCondition(ConditionType.AND
                    , "Fquotation_account_id", queryOption.getQuotationAccountIds());
        }
        if (queryOption.isSetSledCommodityIds() && !queryOption.getSledCommodityIds().isEmpty()) {
            queryBuilder.addInFieldCondition(ConditionType.AND
                    , "Fsled_commodity_id", queryOption.getSledCommodityIds());
        }
        if (queryOption.isSetQuotationDeploySet()) {
            queryBuilder.addFieldCondition(ConditionType.AND
                    , "Fquotation_deploy_set=?", (byte)queryOption.getQuotationDeploySet().getValue());
        }
        queryBuilder.setOrder(OrderType.ASC, "Fsled_commodity_id");
        queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
        
        SubscribeContractItemPage resultPage = new SubscribeContractItemPage();
        if (pageOption.isNeedTotalCount()) {
            resultPage.setTotalCount(super.getTotalNum(queryBuilder));
        }
        resultPage.setResultList(super.getItemList(queryBuilder));
        
        return resultPage;
    }
    
    public int delSCItem(long itemId) throws SQLException {
        return super.delete("Fitem_id=?", itemId);
    }
    
    public int addSCItem(SubscribeContractItem scItem) throws SQLException {
        Preconditions.checkNotNull(scItem);
        Preconditions.checkArgument(scItem.isSetItemId() && scItem.getItemId() > 0);
        
        PreparedFields pf = new PreparedFields();
        pf.addLong("Fitem_id", scItem.getItemId());
        if (scItem.isSetClassId()) {
            pf.addLong("Fclass_id", scItem.getClassId());
        }
        if (scItem.isSetSledContractId()) {
            pf.addLong("Fsled_contract_id", scItem.getSledContractId());
        }
        if (scItem.isSetSledContractCode()) {
            pf.addString("Fsled_contract_code", scItem.getSledContractCode());
        }
        if (scItem.isSetSledCommodityId()) {
            pf.addLong("Fsled_commodity_id", scItem.getSledCommodityId());
        }
        if (scItem.isSetSledCommodityType()) {
            pf.addShort("Fsled_commodity_type", scItem.getSledCommodityType());
        }
        if (scItem.isSetSledCommodityCode()) {
            pf.addString("Fsled_commodity_code", scItem.getSledCommodityCode());
        }
        if (scItem.isSetSledExchangeMic()) {
            pf.addString("Fsled_exchange_mic", scItem.getSledExchangeMic());
        }
        if (scItem.isSetPlatformEnv()) {
            pf.addByte("Fplatform_env", (byte)scItem.getPlatformEnv().getValue());
        }
        if (scItem.isSetQuotationAccountId()) {
            pf.addLong("Fquotation_account_id", scItem.getQuotationAccountId());
        }
        if (scItem.isSetIsForActive()) {
            if (scItem.isIsForActive()) {
                pf.addByte("Fis_for_active", (byte)1);
            } else {
                pf.addByte("Fis_for_active", (byte)0);
            }
        }
        if (scItem.isSetQuotationDeploySet()) {
            pf.addByte("Fquotation_deploy_set", (byte)scItem.getQuotationDeploySet().getValue());
        }
        
        pf.addLong("Fcreate_timestampms", System.currentTimeMillis());
        
        return super.insert(pf);
    }

    @Override
    public SubscribeContractItem fromResultSet(ResultSet rs) throws Exception {
        SubscribeContractItem scItem = new SubscribeContractItem();
        scItem.setItemId(rs.getLong("Fitem_id"));
        scItem.setClassId(rs.getLong("Fclass_id"));
        scItem.setSledContractId(rs.getLong("Fsled_contract_id"));
        scItem.setSledContractCode(rs.getString("Fsled_contract_code"));
        scItem.setSledCommodityId(rs.getLong("Fsled_commodity_id"));
        scItem.setSledCommodityType(rs.getShort("Fsled_commodity_type"));
        scItem.setSledCommodityCode(rs.getString("Fsled_commodity_code"));
        scItem.setSledExchangeMic(rs.getString("Fsled_exchange_mic"));
        scItem.setPlatformEnv(QuotationPlatformEnv.findByValue(rs.getByte("Fplatform_env")));
        scItem.setQuotationAccountId(rs.getLong("Fquotation_account_id"));
        scItem.setIsForActive(rs.getByte("Fis_for_active") != 0);
        scItem.setCreateTimestampMs(rs.getLong("Fcreate_timestampms"));
        scItem.setQuotationDeploySet(DeploySet.findByValue(rs.getByte("Fquotation_deploy_set")));
        return scItem;
    }

    @Override
    protected String getTableName() throws SQLException {
        return "tsc_item_" + mIndex;
    }
    
}
