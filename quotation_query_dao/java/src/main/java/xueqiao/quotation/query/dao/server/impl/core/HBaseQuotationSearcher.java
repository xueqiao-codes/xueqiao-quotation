package xueqiao.quotation.query.dao.server.impl.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

import xueqiao.quotation.KLineQuotationMinuteItem;
import xueqiao.quotation.QuotationItem;
import xueqiao.quotation.race.common.RaceStoreConfig;

public class HBaseQuotationSearcher implements IQuotationSearcher {
	private static TProtocolFactory PROTOCOL_FACTORY = new TCompactProtocol.Factory();
	
	private Connection kLineConnection;
	private Connection quotationConnection;
	
	private Connection createConnection(Map<String, String> hBaseConfig) throws IOException {
		Configuration conf = HBaseConfiguration.create();
		
		for (Entry<String, String> entry : hBaseConfig.entrySet()) {
			conf.set(entry.getKey(), entry.getValue());
		}
		
		return ConnectionFactory.createConnection(conf);
	}
	
	public void init(RaceStoreConfig config) throws IOException {
		kLineConnection = createConnection(config.getKLineHbaseConfiguration());
		quotationConnection = createConnection(config.getQuotationHBaseConfiguration());
	}
	
	public void onConfigChanged(RaceStoreConfig config) {
	}
	
	public List<QuotationItem> getTicks(long uniqueContractId, long startTimestampS, long endTimestampS) throws IOException {
		try (Table t = quotationConnection.getTable(TableName.valueOf("quotation"))) {
			Scan scan = new Scan();
			scan.setMaxVersions();
			
			byte[] rowKeyStart = new byte[16];
			byte[] rowKeyEnd = new byte[16];
			
			int offset = Bytes.putLong(rowKeyStart, 0, uniqueContractId);
			Bytes.putLong(rowKeyStart, offset, startTimestampS*1000);
			
			offset = Bytes.putLong(rowKeyEnd, 0, uniqueContractId);
			Bytes.putLong(rowKeyEnd, offset, endTimestampS*1000 + 1000);
			
			scan.setStartRow(rowKeyStart);
			scan.setStopRow(rowKeyEnd);
			
			try (ResultScanner rs = t.getScanner(scan)) {
				List<QuotationItem> resultList = new ArrayList<QuotationItem>();
				
				for (Result r : rs) {
					List<Cell> columnCells = r.getColumnCells(Bytes.toBytes("content"), Bytes.toBytes("detail"));
					for (int index = columnCells.size() - 1; index >= 0; --index) {
						Cell columnCell = columnCells.get(index);
						resultList.add(ProtocolUtil.unSerialize(PROTOCOL_FACTORY
								, columnCell.getValueArray()
								, columnCell.getValueOffset()
								, columnCell.getValueLength()
								, QuotationItem.class));
					}
				}
				
				return resultList;
			}
		}
	}
	
	public List<KLineQuotationMinuteItem> getKLineMinutes(long uniqueContractId, long startTimestampS, long endTimestampS) throws IOException {
		try (Table t = kLineConnection.getTable(TableName.valueOf("kline1m"))) {
			Scan scan = new Scan();
			scan.setMaxVersions();
			
			byte[] rowKeyStart = new byte[16];
			byte[] rowKeyEnd = new byte[16];
			
			int offset = Bytes.putLong(rowKeyStart, 0, uniqueContractId);
			Bytes.putLong(rowKeyStart, offset, startTimestampS);
			
			offset = Bytes.putLong(rowKeyEnd, 0, uniqueContractId);
			Bytes.putLong(rowKeyEnd, offset, endTimestampS + 60);
			
			scan.setStartRow(rowKeyStart);
			scan.setStopRow(rowKeyEnd);
			
			try (ResultScanner rs = t.getScanner(scan)) {
				List<KLineQuotationMinuteItem> resultList = new ArrayList<KLineQuotationMinuteItem>();
				
				for (Result r : rs) {
					List<Cell> columnCells = r.getColumnCells(Bytes.toBytes("content"), Bytes.toBytes("detail"));
					for (int index = columnCells.size() - 1; index >= 0; --index) {
						Cell columnCell = columnCells.get(index);
						resultList.add(ProtocolUtil.unSerialize(PROTOCOL_FACTORY
								, columnCell.getValueArray()
								, columnCell.getValueOffset()
								, columnCell.getValueLength()
								, KLineQuotationMinuteItem.class));
					}
				}
				
				return resultList;
			}
		}
	}
}
