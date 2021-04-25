package xueqiao.quotation.race.store.persistant;

import java.io.File;

import org.soldier.base.logger.AppLog;

import com.google.common.base.Preconditions;

import xueqiao.quotation.race.common.RaceStoreConfig;

public class QuotationPersistantFactory {
	
	public static enum PersistantItemType {
		QuotationItem{
			@Override
			public IQuotationPersistant create(RaceStoreConfig config) throws Exception {
				if (RaceStoreConfig.MONGODB_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
					return new MongoDBQuotationPersistant(config.getMongoQuotationClientURI()
							, config.getQuotationDBName()
							, config.getQuotationTableCount());
				} else if (RaceStoreConfig.HBASE_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
					return new HBaseQuotationPersistant(config.getQuotationHBaseConfiguration(), "quotation", "content");
				} else if (RaceStoreConfig.DROP_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
					return new DropPersistant();
				} else if (RaceStoreConfig.LOG_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
				    return LogQuotationPersistant.getInstance(new File(config.getLogBaseDir()));
				}
				  
				return null;
			}
		},
		
		KLineQuotationMinuteItem{
			@Override
			public IQuotationPersistant create(RaceStoreConfig config) throws Exception {
				if (RaceStoreConfig.MONGODB_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
					return new MongoDBQuotationPersistant(config.getMongoKLineClientURI()
							, config.getKLineDBName()
							, config.getKLineTableCount());
				} else if (RaceStoreConfig.HBASE_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
					return new HBaseQuotationPersistant(config.getKLineHbaseConfiguration(), "kline1m", "content");
				} else if (RaceStoreConfig.DROP_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
					return new DropPersistant();
				} else if (RaceStoreConfig.LOG_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
				    return LogQuotationPersistant.getInstance(new File(config.getLogBaseDir()));
				}
				return null;
			}
		};
		
		public abstract IQuotationPersistant create(RaceStoreConfig config) throws Exception ;
	};
	
	public static IQuotationPersistant create(PersistantItemType persistantType, RaceStoreConfig config) {
		Preconditions.checkArgument(persistantType != null);
		Preconditions.checkArgument(config != null);
		IQuotationPersistant persistant = null;
		try {
			persistant = persistantType.create(config);
		} catch (Throwable e) {
			AppLog.e(e.getMessage(), e);
		}
		if (persistant == null) {
			throw new IllegalArgumentException("Unable to create persistant for persistantType=" 
					+ persistantType + ", config=" + config.toJsonString());
		}
		return persistant;
	}
}
