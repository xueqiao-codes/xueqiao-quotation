package xueqiao.quotation.race.store;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.attr.AttrReporterFactory;

import xueqiao.quotation.race.common.RaceStoreConfig;
import xueqiao.quotation.race.store.persistant.IQuotationPersistant;
import xueqiao.quotation.race.store.persistant.QuotationPersistantFactory;
import xueqiao.quotation.race.store.persistant.QuotationPersistantFactory.PersistantItemType;

public class QuotationStorage extends AbstractStorage<HashedQuotationItem> {
	private static int ATTR_QUOTATION_READ_TOTAL_COUNT_KEY 
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.quotation.item.read.total.count", null);
	private static int ATTR_QUOTATION_UPDATE_TOTAL_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.quotation.item.update.total.count", null);
	private static int ATTR_QUOTATION_UPDATE_FAILED_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.quotation.item.update.failed.count", null);
	private static int ATTR_QUOTATION_UPDATE_TIMEUS_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.quotation.item.update.timeus", null);
	private static int ATTR_QUOTATION_READ_MEMCACHEQ_TOTAL_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.quotation.memcacheq.read.total.count", null);
	private static int ATTR_QUOTATION_READ_MEMCACHEQ_FAILED_COUNT_KEY
		= AttrReporterFactory.thirtySecs().requireKey("quotation.race.store.quotation.memcacheq.read.failed.count", null);
	
	private IQuotationPersistant persistant;
	
	public QuotationStorage(RaceStoreConfig config) {
		super("quotation", HashedQuotationItem.class, config.getParallelQuotationWorkNum());
		
		persistant = QuotationPersistantFactory.create(PersistantItemType.QuotationItem, config);
		
		AppLog.w("Init QuotationStorage, persistant =" + persistant.getClass().getName());
		setIgnorePlatforms(config.getIgnorePlatforms());
	}
	
	@Override
	protected void onReadItem(HashedQuotationItem item) {
		AttrReporterFactory.thirtySecs().inc(ATTR_QUOTATION_READ_TOTAL_COUNT_KEY, 1);
	}

	@Override
	protected boolean onStoreItem(HashedQuotationItem item) {
		try {
			List<String> currentIgnorePlatforms = ignorePlatforms;
			if (currentIgnorePlatforms.contains(item.getPlatform())) {
				if (AppLog.debugEnabled()) {
					AppLog.d("ignore item platform=" + item.getPlatform() + ", contractSymbols=" + item.getContractSymbol()
							+ ", ignorePlatforms=" + StringUtils.join(currentIgnorePlatforms, ","));
				}
				return true;
			}
			
			AttrReporterFactory.thirtySecs().inc(ATTR_QUOTATION_UPDATE_TOTAL_COUNT_KEY, 1);
			long startNanoTimestamp = System.nanoTime();
			
			long contractId = getUniqueContractId(item.getPlatform(), item.getContractSymbol());
			persistant.onStoreQuotationItem(contractId, item);
			
			AttrReporterFactory.thirtySecs().average(ATTR_QUOTATION_UPDATE_TIMEUS_KEY
							, (System.nanoTime() - startNanoTimestamp)/1000);
			return true;
		} catch (Throwable e) {
			AttrReporterFactory.thirtySecs().inc(ATTR_QUOTATION_UPDATE_FAILED_COUNT_KEY, 1);
			AppLog.e(e.getMessage(), e);
		}
		
		return false;
	}
	
	@Override
	public void onReadMemcacheqStart() {
		AttrReporterFactory.thirtySecs().inc(ATTR_QUOTATION_READ_MEMCACHEQ_TOTAL_COUNT_KEY, 1);
	}

	@Override
	public void onReadMemcacheqFailed() {
		AttrReporterFactory.thirtySecs().inc(ATTR_QUOTATION_READ_MEMCACHEQ_FAILED_COUNT_KEY, 1);
	}
	
	
}
