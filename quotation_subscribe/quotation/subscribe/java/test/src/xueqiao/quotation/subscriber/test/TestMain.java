package xueqiao.quotation.subscriber.test;

import org.apache.commons.lang.StringUtils;
import org.soldier.platform.attr.AttrReporterLibraryLoader;

import xueqiao.quotation.quotation_itemConstants;
import xueqiao.quotation.subscriber.Topic;
import xueqiao.quotation.subscriber.api.SubscribeSystem;

public class TestMain {

	public static void main(String[] args)  {
		if (args.length < 2) {
			System.out.println("please input platform and contract");
			return ;
		}
		
		AttrReporterLibraryLoader.init();
		SubscribeSystem.init("subscriber_java_test");
		SubscribeSystem.getInstance().setCallback(new TestCallback());
//		SubscribeSystem.getInstance().addTopic(new Topic(quotation_itemConstants.PLATFORM_CTP, "au1712"));
//		SubscribeSystem.getInstance().addTopic(new Topic(quotation_itemConstants.PLATFORM_CTP, "bu1705"));
//		SubscribeSystem.getInstance().addTopic(new Topic(args[0], args[1]));
		
		String contractList = "11535,10511,10640,10514,11027,11288,11290,11935,11807,11290,11935,11807,11295,11808,11938";
		
		for (String contract : StringUtils.split(contractList, ",")) {
			if (contract != null) {
				SubscribeSystem.getInstance().addTopic(new Topic("SXQ", contract.trim()));
			}
		}
		
		while(true) {
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
