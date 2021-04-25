package xueqiao.quotation.race.store.persistant;

import org.soldier.base.logger.AppLog;

import xueqiao.quotation.race.store.HashedKLineQuotationMinuteItem;
import xueqiao.quotation.race.store.HashedQuotationItem;

public class DropPersistant implements IQuotationPersistant {

	@Override
	public void onStoreKLineMinuteItem(long uniqueContractId, HashedKLineQuotationMinuteItem item) throws Exception {
		if (AppLog.infoEnabled()) {
			AppLog.i("drop HashedKLineQuotationMinuteItem /" + item.getPlatform() + "/" + item.getContractSymbol());
		}
	}

	@Override
	public void onStoreQuotationItem(long uniqueContractId, HashedQuotationItem item) throws Exception {
		if (AppLog.infoEnabled()) {
			AppLog.i("drop HashedQuotationItem /" + item.getPlatform() + "/" + item.getContractSymbol());
		}
	}

	@Override
	public void destroy() {
	}

}
