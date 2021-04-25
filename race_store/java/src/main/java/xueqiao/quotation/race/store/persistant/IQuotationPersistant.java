package xueqiao.quotation.race.store.persistant;

import xueqiao.quotation.race.store.HashedKLineQuotationMinuteItem;
import xueqiao.quotation.race.store.HashedQuotationItem;

public interface IQuotationPersistant {
	
	public void onStoreKLineMinuteItem(long uniqueContractId, HashedKLineQuotationMinuteItem item) throws Exception;
	
	public void onStoreQuotationItem(long uniqueContractId, HashedQuotationItem item) throws Exception ;
	
	public void destroy();
}
