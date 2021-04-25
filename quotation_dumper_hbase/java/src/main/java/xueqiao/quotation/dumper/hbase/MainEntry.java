package xueqiao.quotation.dumper.hbase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.soldier.base.StringFactory;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

import xueqiao.quotation.KLineQuotationMinuteItem;
import xueqiao.quotation.QuotationItem;

public class MainEntry {
    private static TProtocolFactory HBASE_PROTOCOL_FACTORY = new TCompactProtocol.Factory();
    private static TProtocolFactory DUPM_PROTOCOL_FACTORY = new TJSONProtocol.Factory();
    
    private static Connection createConnection(Map<String, String> hBaseConfig) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        
        for (Entry<String, String> entry : hBaseConfig.entrySet()) {
            conf.set(entry.getKey(), entry.getValue());
        }
        
        return ConnectionFactory.createConnection(conf);
    }
    
    private static void dumpKLine(Map<String, String> hBaseConfig) throws Exception {
        Connection conn = createConnection(hBaseConfig);
        
        try (Table t = conn.getTable(TableName.valueOf("kline1m"))) {
            Scan scan = new Scan();
            scan.setMaxVersions();
            
            try (ResultScanner rs = t.getScanner(scan)) {
                for (Result r : rs) {
                    List<Cell> columnCells = r.getColumnCells(Bytes.toBytes("content"), Bytes.toBytes("detail"));
                    for (int index = columnCells.size() - 1; index >= 0; --index) {
                        Cell columnCell = columnCells.get(index);
                        KLineQuotationMinuteItem item = ProtocolUtil.unSerialize(HBASE_PROTOCOL_FACTORY
                                , columnCell.getValueArray()
                                , columnCell.getValueOffset()
                                , columnCell.getValueLength()
                                , KLineQuotationMinuteItem.class);
                        if ("SCTP".equalsIgnoreCase(item.getPlatform())) {
                            continue;
                        }
                        
                        sKlineLogger.info(
                             StringFactory.newUtf8String(ProtocolUtil.serialize2Bytes(DUPM_PROTOCOL_FACTORY, item)));
                    }
                }
                
            }
        }
        
    }
    
    private static void dumpQuotation(Map<String, String> hBaseConfig) throws Exception {
        Connection conn = createConnection(hBaseConfig);
        try (Table t = conn.getTable(TableName.valueOf("quotation"))) {
            Scan scan = new Scan();
            scan.setMaxVersions();
            
            try (ResultScanner rs = t.getScanner(scan)) {
                
                for (Result r : rs) {
                    List<Cell> columnCells = r.getColumnCells(Bytes.toBytes("content"), Bytes.toBytes("detail"));
                    for (int index = columnCells.size() - 1; index >= 0; --index) {
                        Cell columnCell = columnCells.get(index);
                        QuotationItem item = ProtocolUtil.unSerialize(HBASE_PROTOCOL_FACTORY
                                , columnCell.getValueArray()
                                , columnCell.getValueOffset()
                                , columnCell.getValueLength()
                                , QuotationItem.class);
                        if ("SCTP".equalsIgnoreCase(item.getPlatform())) {
                            continue;
                        }
                        
                        sQuotationLogger.info(
                             StringFactory.newUtf8String(ProtocolUtil.serialize2Bytes(DUPM_PROTOCOL_FACTORY, item)));
                    }
                }
            }
        }
    }
    
    private static Logger createLogger(String name, String parentDir) throws IOException {
        Logger logger = LogManager.getLoggerRepository().getLogger(name);
        
        logger.setAdditivity(false);
        logger.setLevel(Level.INFO);
        
        PatternLayout layout = new PatternLayout("%m%n");
        DailyRollingFileAppender appender = new DailyRollingFileAppender(  
                layout
                , parentDir + File.separator + name
                , "yyyy-MM-dd-HH-mm");  
        appender.setBufferedIO(false);
        
        logger.addAppender(appender);
        
        return logger;
    }
    
    private static File KLineDataDir = new File("/quotation_data/kline");
    private static File QuotationDataDir = new File("/quotation_data/quotation");
    
    private static Logger sKlineLogger;
    private static Logger sQuotationLogger;
    
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("please input the hbase.zookeeper.quorum");
            return ;
        }
        
        if (!KLineDataDir.exists()) {
            KLineDataDir.mkdirs();
        }
        if (!QuotationDataDir.exists()) {
            QuotationDataDir.mkdirs();
        }
        
        sKlineLogger = createLogger("kline.data", KLineDataDir.getAbsolutePath());
        sQuotationLogger = createLogger("quotation.data", QuotationDataDir.getAbsolutePath());
        
        Map<String, String> hBaseConfig = new HashMap<>();
        hBaseConfig.put("hbase.zookeeper.quorum", args[0]);
        
        Thread dumpQuotationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dumpQuotation(hBaseConfig);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        dumpQuotationThread.start();
        
        Thread dumpKLineThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    dumpKLine(hBaseConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });
        dumpKLineThread.start();
        
        dumpQuotationThread.join();
        dumpKLineThread.join();
    }
}
