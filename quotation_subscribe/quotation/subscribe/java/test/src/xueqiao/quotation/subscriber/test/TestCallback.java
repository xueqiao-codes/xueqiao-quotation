package xueqiao.quotation.subscriber.test;

import org.soldier.platform.attr.AttrReporter;
import org.soldier.platform.attr.ReporterType;
import org.soldier.platform.attr.StringMap;

import xueqiao.quotation.QuotationItem;
import xueqiao.quotation.subscriber.Topic;
import xueqiao.quotation.subscriber.api.SubscribeSystem;

public class TestCallback implements SubscribeSystem.ICallback {
	int key = 0;
	
	public TestCallback() {
	    AttrReporter.keep(ReporterType.REPORTER_TYPE_THIRTYSECONDS
	       , AttrReporter.requireKey(ReporterType.REPORTER_TYPE_THIRTYSECONDS, "quotation.subscriber.test.java", new StringMap())
	       , 1);
		StringMap map = new StringMap();
		map.set("name", "subscriber_test_java");
		key = AttrReporter.requireKey(ReporterType.REPORTER_TYPE_THIRTYSECONDS, "quotation.subscriber.test.java", map);
	}
	
	@Override
	public void onReceivedQuotationItem(Topic topic, QuotationItem item) {
		// TODO Auto-generated method stub
		System.out.println("onReceivedItemData platform=" 
				+ topic.Platform() + ", contract=" + topic.contractSymbol()
				+ ", item.volumn=" + item.getVolumn()
			    + ", item=" + item);
	
		AttrReporter.inc(ReporterType.REPORTER_TYPE_THIRTYSECONDS, key, 1);
	}
}
