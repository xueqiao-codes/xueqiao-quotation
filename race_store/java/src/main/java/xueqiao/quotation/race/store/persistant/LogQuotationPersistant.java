package xueqiao.quotation.race.store.persistant;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.Category;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.soldier.base.StringFactory;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

import xueqiao.quotation.race.store.HashedKLineQuotationMinuteItem;
import xueqiao.quotation.race.store.HashedQuotationItem;

/**
 *  采用Log的方式记录行情
 * @author wangli
 *
 */
public class LogQuotationPersistant implements IQuotationPersistant {
    private static final TProtocolFactory PROTOCOL_FACTORY = new TJSONProtocol.Factory();
    
    private static LogQuotationPersistant sInstance;
    public static LogQuotationPersistant getInstance(File baseDir) throws IOException {
        if (sInstance == null) {
            synchronized(LogQuotationPersistant.class) {
                if (sInstance == null) {
                    sInstance = new LogQuotationPersistant(baseDir);
                }
            }
        }
        return sInstance;
    }
    
    private static class DataLogger extends Logger {

        public DataLogger(String name) {
            super(name);
        }
        
        public void logData(String dataMsg) {
            super.forcedLog(Category.class.getName(), Level.INFO, dataMsg, null);
        }
    }
    
    private DataLogger mQuotationLogger;
    private DataLogger mKLineLogger;
    
    private LogQuotationPersistant(File baseDir) throws IOException {
        StringBuilder dataBaseDirBuilder = new StringBuilder(64);
        dataBaseDirBuilder.append(baseDir.getAbsolutePath())
                   .append(File.separator).append("logstore")
                   .append(File.separator).append(InetAddress.getLocalHost().getHostName());
        
        File dataBaseDir = new File(dataBaseDirBuilder.toString());
        
        if (!dataBaseDir.exists()) {
            if (!dataBaseDir.mkdirs()) {
                throw new Error("mkdirs for " + dataBaseDir.getAbsolutePath() + " failed!");
            }
        }
        
        mQuotationLogger = createLogger("quotation.data"
                , dataBaseDir.getAbsolutePath() + File.separator + "quotation");
        mKLineLogger = createLogger("kline.data"
                , dataBaseDir.getAbsolutePath() + File.separator + "kline");
    }
    
    
    
    private static DataLogger createLogger(String name, String parentDir) throws IOException {
        Logger logger = LogManager.getLoggerRepository().getLogger(name
                    , new LoggerFactory() {
            @Override
            public Logger makeNewLoggerInstance(String name) {
                return new DataLogger(name);
            }   
        });
        
        logger.setAdditivity(false);
        logger.setLevel(Level.INFO);
        
        PatternLayout layout = new PatternLayout("[%d{yyyy-MM-dd HH:mm:ss,SSS}] %m%n");
        DailyRollingFileAppender appender = new DailyRollingFileAppender(  
                layout
                , parentDir + File.separator + name
                , "yyyy-MM-dd-HH");  
        appender.setBufferedIO(false);
        
        logger.addAppender(appender);
        
        return DataLogger.class.cast(logger);
    }
    
    @Override
    public void onStoreKLineMinuteItem(long uniqueContractId
            , HashedKLineQuotationMinuteItem item) throws Exception {
        mKLineLogger.logData(StringFactory.newUtf8String(ProtocolUtil.serialize2Bytes(PROTOCOL_FACTORY, item)));
    }

    @Override
    public void onStoreQuotationItem(long uniqueContractId, HashedQuotationItem item) throws Exception {
        mQuotationLogger.logData(StringFactory.newUtf8String(ProtocolUtil.serialize2Bytes(PROTOCOL_FACTORY, item)));
    }

    @Override
    public void destroy() {
    }
    
}
