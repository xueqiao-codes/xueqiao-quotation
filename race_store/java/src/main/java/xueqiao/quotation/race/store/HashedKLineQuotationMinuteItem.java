package xueqiao.quotation.race.store;

import xueqiao.quotation.KLineQuotationMinuteItem;

public class HashedKLineQuotationMinuteItem extends KLineQuotationMinuteItem {
	
	@Override
	public int hashCode() {
		StringBuilder builder = new StringBuilder(64);
		builder.append("/");
		builder.append(this.getPlatform());
		builder.append("/");
		builder.append(this.getContractSymbol());
		return builder.toString().hashCode();
	}
}
