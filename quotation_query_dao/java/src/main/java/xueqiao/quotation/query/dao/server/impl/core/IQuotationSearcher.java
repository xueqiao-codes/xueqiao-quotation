package xueqiao.quotation.query.dao.server.impl.core;

import java.util.List;

import xueqiao.quotation.KLineQuotationMinuteItem;
import xueqiao.quotation.QuotationItem;
import xueqiao.quotation.race.common.RaceStoreConfig;

public interface IQuotationSearcher {
	
	public void init(RaceStoreConfig config) throws Exception;
	
	public void onConfigChanged(RaceStoreConfig config) throws Exception;
	
	public List<QuotationItem> getTicks(long uniqueContractId, long startTimestampS, long endTimestampS) throws Exception;
	
	public List<KLineQuotationMinuteItem> getKLineMinutes(long uniqueContractId, long startTimestampS, long endTimestampS) throws Exception;
}
