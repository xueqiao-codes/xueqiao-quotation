package xueqiao.quotation.race.store.persistant;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.soldier.base.logger.AppLog;

import xueqiao.quotation.race.store.HashedKLineQuotationMinuteItem;
import xueqiao.quotation.race.store.HashedQuotationItem;
import xueqiao.quotation.race.store.util.ProtocolUtil;

public class HBaseQuotationPersistant implements IQuotationPersistant {
	private static TProtocolFactory PROTOCOL_FACTORY = new TCompactProtocol.Factory();
	
	private Configuration conf = HBaseConfiguration.create();
	private String tableName;
	private String columnFamilyName;
	
	private Connection connection;
	
	public HBaseQuotationPersistant(Map<String, String> hbaseConfiguration
			, String tableName
			, String columnFamilyName) throws IOException {
		
		for (Entry<String, String> entry : hbaseConfiguration.entrySet()) {
			this.conf.set(entry.getKey(), entry.getValue());
		}
		
		this.tableName = tableName;
		this.columnFamilyName = columnFamilyName;
		this.connection = ConnectionFactory.createConnection(this.conf);
	}
	
	@Override
	public void onStoreKLineMinuteItem(long uniqueContractId, HashedKLineQuotationMinuteItem item) throws IOException  {
		if (AppLog.debugEnabled()) {
			AppLog.d("store KLineMinuteItem " + item + ", using uniqueContractId=" + uniqueContractId);
		}
		
		try (Table table = connection.getTable(TableName.valueOf(tableName))) {
			byte[] rowKey = new byte[16];
			int offset = Bytes.putLong(rowKey, 0, uniqueContractId);
			Bytes.putLong(rowKey, offset, item.getKMinuteStartTimestampS());
			
			ByteBuffer value = ProtocolUtil.serialize(PROTOCOL_FACTORY, item);
			Put put = new Put(rowKey);
			put.addColumn(Bytes.toBytes(columnFamilyName), Bytes.toBytes("detail"), Bytes.toBytes(value));
			
			table.put(put);
		} ;
	}

	@Override
	public void onStoreQuotationItem(long uniqueContractId, HashedQuotationItem item) throws IOException {
		if (AppLog.debugEnabled()) {
			AppLog.d("store QuotationItem " + item + ", using uniqueContractId=" + uniqueContractId);
		}
		
		try (Table table = connection.getTable(TableName.valueOf(tableName))) {
			byte[] rowKey = new byte[16];
			int offset = Bytes.putLong(rowKey, 0, uniqueContractId);
			Bytes.putLong(rowKey, offset, item.getRaceTimestampMs());
			
			ByteBuffer value = ProtocolUtil.serialize(PROTOCOL_FACTORY, item);
			Put put = new Put(rowKey);
			put.addColumn(Bytes.toBytes(columnFamilyName), Bytes.toBytes("detail"), Bytes.toBytes(value));
			
			table.put(put);
		};
	}

	@Override
	public void destroy() {
		IOUtils.closeQuietly(connection);
	}

}
