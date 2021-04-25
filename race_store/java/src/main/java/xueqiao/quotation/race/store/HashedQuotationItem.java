package xueqiao.quotation.race.store;

import xueqiao.quotation.QuotationItem;

public class HashedQuotationItem extends QuotationItem {

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
