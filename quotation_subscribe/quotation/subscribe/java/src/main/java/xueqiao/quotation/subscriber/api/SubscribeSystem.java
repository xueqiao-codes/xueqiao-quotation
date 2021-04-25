package xueqiao.quotation.subscriber.api;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.soldier.base.logger.AppLog;

import xueqiao.quotation.QuotationItem;
import xueqiao.quotation.subscriber.ISubscribeCallback;
import xueqiao.quotation.subscriber.QuotationSubscriberSwig;
import xueqiao.quotation.subscriber.Topic;

public class SubscribeSystem extends ISubscribeCallback {
	private static SubscribeSystem sInstance;
	
	public static void init(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name should not be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name should not be empty");
		}
		
		if (sInstance == null) {
			synchronized(SubscribeSystem.class) {
				if (sInstance == null) {
					QuotationSubscriberLibraryLoader.init();
					sInstance = new SubscribeSystem(name);
				}
			}
		}
	}
	
	public interface ICallback {
		public void onReceivedQuotationItem(Topic topic, QuotationItem item);
	}
	
	public interface IReceivedItemDataCallback {
		public void onReceivedItemData(Topic topic, byte[] data);
	}
	
	public static SubscribeSystem getInstance() {
		return sInstance;
	}
	
	public static void destroy() {
		if (sInstance != null) {
			synchronized(SubscribeSystem.class) {
				if (sInstance != null) {
					QuotationSubscriberSwig.destroy();
					sInstance = null;
				}
			}
		}
	}
	
	private ICallback mCallback;
	private String mName;
	
	protected SubscribeSystem(String name) {
		mName = name;
		QuotationSubscriberSwig.init(mName, this);
		Runtime.getRuntime().addShutdownHook(new Thread() {  
            public void run() {  
                QuotationSubscriberSwig.destroy();
            }  
        });  
	}
	
	public void addTopic(Topic topic) {
		if (topic == null) {
			throw new IllegalArgumentException("topic should not be null");
		}
		if (topic.Platform() == null || topic.Platform().isEmpty()) {
			throw new IllegalArgumentException("topic's platform should not be null or empty");
		}
		if (topic.contractSymbol() == null || topic.contractSymbol().isEmpty()) {
			throw new IllegalArgumentException("topic's contractSymbol should not be null or empty");
		}
		QuotationSubscriberSwig.addTopic(topic);
	}
	
	public void removeTopic(Topic topic) {
		if (topic == null) {
			throw new IllegalArgumentException("topic should not be null");
		}
		if (topic.Platform() == null || topic.Platform().isEmpty()) {
			throw new IllegalArgumentException("topic's platform should not be null or empty");
		}
		if (topic.contractSymbol() == null || topic.contractSymbol().isEmpty()) {
			throw new IllegalArgumentException("topic's contractSymbol should not be null or empty");
		}
		
		QuotationSubscriberSwig.removeTopic(topic);
	}
	
	public void setCallback(ICallback callback) {
		this.mCallback = callback;
	}
	
	public ICallback getCallback() {
		return mCallback;
	}
	
	@Override
	public void onReceivedItemData(Topic topic, byte[] data) {
		try {
			TMemoryInputTransport transport = new TMemoryInputTransport(data);
			TCompactProtocol protocol = new TCompactProtocol(transport);
			QuotationItem item = new QuotationItem();
			try {
				item.read(protocol);
			} catch (TException e) {
				e.printStackTrace();
			}
			if (mCallback != null) {
				mCallback.onReceivedQuotationItem(topic, item);
			}
		} catch (Throwable e) {
			AppLog.e(e.getMessage(), e);
		}
	}
}
