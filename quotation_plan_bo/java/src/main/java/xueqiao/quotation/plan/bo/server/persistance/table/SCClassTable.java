package xueqiao.quotation.plan.bo.server.persistance.table;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.AutoExpandingBufferWriteTransport;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.soldier.base.StringFactory;
import org.soldier.base.sql.PreparedFields;
import org.soldier.platform.db_helper.TableHelper;

import com.google.common.base.Preconditions;

import xueqiao.quotation.account.thriftapi.ContractActiveType;
import xueqiao.quotation.account.thriftapi.QuotationPlatformEnv;
import xueqiao.quotation.plan.bo.SubscribeAccountClass;
import xueqiao.quotation.plan.bo.SubscribeCommodityClass;

public class SCClassTable extends TableHelper<SubscribeCommodityClass> {
    private static TJSONProtocol.Factory JSON_FACTORY = new TJSONProtocol.Factory();
    
    private int mIndex;
    public SCClassTable(Connection conn, int index) {
        super(conn);
        Preconditions.checkArgument(index >= 0 && index <= 1);
        this.mIndex = index;
    }
    
    public List<SubscribeCommodityClass> getAll() throws SQLException {
        return super.getItemList(super.prepareSqlQueryBuilder());
    }
    
    public int addSCClass(SubscribeCommodityClass scClass) throws SQLException {
        Preconditions.checkNotNull(scClass);
        Preconditions.checkArgument(scClass.getClassId() > 0);
        
        PreparedFields pf = new PreparedFields();
        pf.addLong("Fclass_id", scClass.getClassId());
        pf.addLong("Fcreate_timstampms", System.currentTimeMillis());
        
        if (scClass.isSetSledCommodityId()) {
            pf.addLong("Fsled_commodity_id", scClass.getSledCommodityId());
        }
        if (scClass.isSetSledCommodityType()) {
            pf.addLong("Fsled_commodity_type", scClass.getSledCommodityType());
        }
        if (scClass.isSetSledCommodityCode()) {
            pf.addString("Fsled_commodity_code", scClass.getSledCommodityCode());
        }
        if (scClass.isSetSledExchangeMic()) {
            pf.addString("Fsled_exchange_mic", scClass.getSledExchangeMic());
        }
        if (scClass.isSetPlatformEnv()) {
            pf.addByte("Fplatform_env", (byte)scClass.getPlatformEnv().getValue());
        }
        if (scClass.isSetActiveSubscribeNum()) {
            pf.addInt("Factive_subscribe_num", scClass.getActiveSubscribeNum());
        }
        if (scClass.isSetInActiveSubscribeNum()) {
            pf.addInt("Finactive_subscribe_num", scClass.getInActiveSubscribeNum());
        }
        if (scClass.isSetActiveMonths()) {
            pf.addString("Factive_months", intList2Str(scClass.getActiveMonths()));
        }
        if (scClass.isSetInactiveMonths()) {
            pf.addString("Finactive_months", intList2Str(scClass.getInactiveMonths()));
        }
        if (scClass.isSetSubscribeAccounts()) {
            try {
                pf.addString("Fsubscribe_accounts", scClassList2Json(scClass.getSubscribeAccounts()));
            } catch (TException e) {
                throw new SQLException(e.getMessage(), e);
            }
        }
        if (scClass.isSetActiveType()) {
            pf.addByte("Factive_type", (byte)scClass.getActiveType().getValue());
        }
        if (scClass.isSetFixedContractCode()) {
            pf.addString("Ffixed_contract_code", scClass.getFixedContractCode());
        }
        
        return super.insert(pf);
    }
    
    @Override
    public SubscribeCommodityClass fromResultSet(ResultSet rs) throws Exception {
        SubscribeCommodityClass resultClass = new SubscribeCommodityClass();
        resultClass.setClassId(rs.getLong("Fclass_id"));
        resultClass.setSledCommodityId(rs.getLong("Fsled_commodity_id"));
        resultClass.setSledCommodityType(rs.getShort("Fsled_commodity_type"));
        resultClass.setSledCommodityCode(rs.getString("Fsled_commodity_code"));
        resultClass.setSledExchangeMic(rs.getString("Fsled_exchange_mic"));
        resultClass.setPlatformEnv(QuotationPlatformEnv.findByValue(rs.getByte("Fplatform_env")));
        resultClass.setActiveMonths(str2ListInt(rs.getString("Factive_months")));
        resultClass.setInactiveMonths(str2ListInt(rs.getString("Finactive_months")));
        resultClass.setActiveSubscribeNum(rs.getInt("Factive_subscribe_num"));
        resultClass.setInActiveSubscribeNum(rs.getInt("Finactive_subscribe_num"));
        resultClass.setSubscribeAccounts(scClassListFromJson(rs.getString("Fsubscribe_accounts")));
        resultClass.setCreateTimestampMs(rs.getLong("Fcreate_timstampms"));
        resultClass.setActiveType(ContractActiveType.findByValue(rs.getByte("Factive_type")));
        resultClass.setFixedContractCode(rs.getString("Ffixed_contract_code"));
        return resultClass;
    }

    @Override
    protected String getTableName() throws SQLException {
        return "tsc_class_" + mIndex;
    }
    
    private static String intList2Str(List<Integer> intList) {
        if (intList == null || intList.isEmpty()) {
            return "";
        }
        
        return StringUtils.join(intList.toArray(), ",");
    }
    
    private static List<Integer> str2ListInt(String str) {
        List<Integer> resultList = new ArrayList<>();
        String[] splits = StringUtils.split(str, ",");
        if (splits == null || splits.length <= 0) {
            return resultList;
        }
        
        for (String split : splits) {
            resultList.add(NumberUtils.createInteger(split));
        }
        
        return resultList;
    }
    
    private List<List<SubscribeAccountClass>> scClassListFromJson(String json) throws TException {
        if (StringUtils.isEmpty(json)) {
            return new ArrayList<>();
        }
        
        TMemoryInputTransport inTransport = new TMemoryInputTransport(
                json.getBytes(Charset.forName("UTF-8")));
        TProtocol inProtocol = JSON_FACTORY.getProtocol(inTransport);
        
        TList outerList = inProtocol.readListBegin();
        List<List<SubscribeAccountClass>> resultList = new ArrayList<>(outerList.size + 1);
        for (int index = 0; index < outerList.size; ++index) {
            TList innerList = inProtocol.readListBegin();
            
            List<SubscribeAccountClass> innerClassList = new ArrayList<>(innerList.size + 1);
            for (int innerIndex = 0; innerIndex < innerList.size; ++innerIndex) {
                SubscribeAccountClass accountClass = new SubscribeAccountClass();
                accountClass.read(inProtocol);
                innerClassList.add(accountClass);
            }
            resultList.add(innerClassList);
            
            inProtocol.readListEnd();
        }
        inProtocol.readListEnd();
        
        return resultList;
    }
    
    private String scClassList2Json(List<List<SubscribeAccountClass>> classes) throws TException {
        if (classes == null) {
            return "";
        }
        
        AutoExpandingBufferWriteTransport outTransport = new AutoExpandingBufferWriteTransport(256, 2.0);
        TProtocol outProtocol = JSON_FACTORY.getProtocol(outTransport);
        
        outProtocol.writeListBegin(new TList(TType.LIST, classes.size()));
        for (int index = 0; index < classes.size(); ++index) {
            List<SubscribeAccountClass> classList = classes.get(index);
            outProtocol.writeListBegin(new TList(TType.STRUCT, classList.size()));
            for (int innerIndex = 0; innerIndex < classList.size(); ++innerIndex) {
                classList.get(innerIndex).write(outProtocol);
            }
            outProtocol.writeListEnd();
        }
        outProtocol.writeListEnd();
        
        return StringFactory.netUtf8String(outTransport.getBuf().array(), 0, outTransport.getPos());
    }
    
}
