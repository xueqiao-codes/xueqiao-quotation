package xueqiao.quotation.race.store;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.attr.AttrReporterFactory;

import xueqiao.quotation.race.common.RaceStoreConfig;
import xueqiao.quotation.race.store.persistant.IQuotationPersistant;
import xueqiao.quotation.race.store.persistant.QuotationPersistantFactory;
import xueqiao.quotation.race.store.persistant.QuotationPersistantFactory.PersistantItemType;

public class KLineStorage extends AbstractStorage<HashedKLineQuotationMinuteItem> {
	private static int ATTR_KLINE_1_READ_TOTAL_COUNT_KEY 
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.kline1.item.read.total.count", null);
	private static int ATTR_KLINE_1_UPDATE_TOTAL_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.kline1.item.update.total.count", null);
	private static int ATTR_KLINE_1_UPDATE_FAILED_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.kline1.item.update.failed.count", null);
	private static int ATTR_KLINE_1_UPDATE_TIMEUS_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.kline1.item.update.timeus", null);
	private static int ATTR_KLINE_1_READ_MEMCACHEQ_TOTAL_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.kline1.memcacheq.read.total.count", null);
	private static int ATTR_KLINE_1_READ_MEMCACHED_FAILED_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.kline1.memcacheq.read.failed.count", null);
	
	private IQuotationPersistant persistant;
	
	public KLineStorage(RaceStoreConfig config) {
		super("kline_1", HashedKLineQuotationMinuteItem.class, config.getParallelKLineWorkNum());
		
		persistant = QuotationPersistantFactory.create(PersistantItemType.KLineQuotationMinuteItem, config);
		
		AppLog.w("Init KLineStorage, persistant =" + persistant.getClass().getName());
		
		setIgnorePlatforms(config.getIgnorePlatforms());
	}
	
	@Override
	protected void onReadItem(HashedKLineQuotationMinuteItem item) {
		AttrReporterFactory.thirtySecs().inc(ATTR_KLINE_1_READ_TOTAL_COUNT_KEY, 1);
	}

	@Override
	protected boolean onStoreItem(HashedKLineQuotationMinuteItem item) {
		try {
			List<String> currentIgnorePlatforms = ignorePlatforms;
			if (currentIgnorePlatforms.contains(item.getPlatform())) {
				if (AppLog.debugEnabled()) {
					AppLog.d("ignore item platform=" + item.getPlatform() + ", contractSymbols=" + item.getContractSymbol()
							+ ", ignorePlatforms=" + StringUtils.join(currentIgnorePlatforms, ","));
				}
				return true;
			}
			
			AttrReporterFactory.thirtySecs().inc(ATTR_KLINE_1_UPDATE_TOTAL_COUNT_KEY, 1);
			
			long startNanoTimestamp = System.nanoTime();
			long contractId = getUniqueContractId(item.getPlatform(), item.getContractSymbol());
			
			persistant.onStoreKLineMinuteItem(contractId, item);
			
			AttrReporterFactory.thirtySecs().average(ATTR_KLINE_1_UPDATE_TIMEUS_KEY, (System.nanoTime() - startNanoTimestamp)/1000);
			
			return true;
		} catch (Throwable e) {
			AttrReporterFactory.thirtySecs().inc(ATTR_KLINE_1_UPDATE_FAILED_COUNT_KEY, 1);
			AppLog.e(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public void onReadMemcacheqStart() {
		AttrReporterFactory.thirtySecs().inc(ATTR_KLINE_1_READ_MEMCACHEQ_TOTAL_COUNT_KEY, 1);
	}

	@Override
	public void onReadMemcacheqFailed() {
		AttrReporterFactory.thirtySecs().inc(ATTR_KLINE_1_READ_MEMCACHED_FAILED_COUNT_KEY, 1);
	}
	
}
