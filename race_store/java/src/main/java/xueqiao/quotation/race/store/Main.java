package xueqiao.quotation.race.store;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.attr.AttrReporterFactory;
import org.soldier.watcher.file.FileWatcherModule;
import org.soldier.watcher.file.IFileWatcherListener;

import com.google.gson.Gson;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import xueqiao.quotation.race.common.RaceStoreConfig;

public class Main {
	
	private static class QuotationRaceStore implements IFileWatcherListener {
		private static File CONFIG_FILE = new File("/data/configs/qconf/xueqiao/quotation/race/store");
		
		private KLineStorage kStorage;
		private QuotationStorage qStorage;
		
		private volatile RaceStoreConfig currentConfig;
		
		public QuotationRaceStore() {
		    String CONFIG_FILE_PATH_ENV = System.getenv("CONFIG_FILE");
		    if (StringUtils.isNotEmpty(CONFIG_FILE_PATH_ENV)) {
		        CONFIG_FILE = new File(CONFIG_FILE_PATH_ENV);
		    }
		    
			RaceStoreConfig config = null;
			synchronized(this) {
				FileWatcherModule.Instance().addWatchFile(CONFIG_FILE, this);
				config = readConfig();
				if (config == null) {
					AppLog.f("read race store config failed!");
				}
				
				kStorage = new KLineStorage(config);
				qStorage = new QuotationStorage(config);
				
				currentConfig = config;
			}
		}
		
		public void start() {
			kStorage.startStoring();
			qStorage.startStoring();
		}
		
		private RaceStoreConfig readConfig() {
			Gson gson = new Gson();
			
			int retryTimes = 3;
			while((retryTimes--) > 0) {
				FileReader reader = null;
				try {
					reader = new FileReader(CONFIG_FILE);
					return gson.fromJson(reader, RaceStoreConfig.class);
				} catch (Throwable e) {
					AppLog.e(e.getMessage(), e);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						break;
					}
				} finally {
					IOUtils.closeQuietly(reader);
				}
			}
			return null;
		}
		
		public static interface CLibrary extends Library {
		    void exit(int ret);
		    void printf(String format, Object... args);
		}
		
		private void killMe() {
		    CLibrary instance = (CLibrary)
		            Native.load(Platform.isWindows() ? "msvcrt" : "c" ,CLibrary.class);
		    instance.exit(-1);
		}
		
		@Override
		public void onHandleFileChanged(File filePath) {
			RaceStoreConfig newConfig = null;
			synchronized(this) {
				newConfig = readConfig();
			}
			
			if (newConfig == null) {
				return ;
			}
			
			if (!currentConfig.getStoreType().equals(newConfig.getStoreType())) {
				AppLog.w("restart for store type changed!");
				killMe();
				return ;
			}
			
			if (RaceStoreConfig.MONGODB_STORE_TYPE.equalsIgnoreCase(currentConfig.getStoreType())) {
				if (!currentConfig.getMongoKLineClientURI().equals(newConfig.getMongoKLineClientURI())
					|| !currentConfig.getMongoQuotationClientURI().equals(newConfig.getMongoQuotationClientURI())) {
					// need restart
					AppLog.w("restart for mongodb client uri changed");
					killMe();
					return ;
				}
			} else if (RaceStoreConfig.HBASE_STORE_TYPE.equalsIgnoreCase(currentConfig.getStoreType())) {
				if (!currentConfig.getKLineHbaseConfiguration().equals(newConfig.getKLineHbaseConfiguration())
					|| !currentConfig.getQuotationHBaseConfiguration().equals(newConfig.getQuotationHBaseConfiguration())) {
					AppLog.w("restart for hbase configuration changed!");
					killMe();
					return ;
				}
			}
			
			kStorage.setWorkNum(newConfig.getParallelKLineWorkNum());
			qStorage.setWorkNum(newConfig.getParallelQuotationWorkNum());
				
			kStorage.setIgnorePlatforms(newConfig.getIgnorePlatforms());
			qStorage.setIgnorePlatforms(newConfig.getIgnorePlatforms());
				
			currentConfig = newConfig;
		}
	}
	
	public static void main(String[] args) {
		AppLog.init("apps/quotation_race_store");
		
		try {
			QuotationRaceStore store = new QuotationRaceStore();
			store.start();
		
			AttrReporterFactory.thirtySecs().keep(
					AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.keepalive", null), 1);
			while(true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Throwable e) {
			AppLog.e(e.getMessage(), e);
			Runtime.getRuntime().exit(-1);
		}
		
	}
	
		
}
