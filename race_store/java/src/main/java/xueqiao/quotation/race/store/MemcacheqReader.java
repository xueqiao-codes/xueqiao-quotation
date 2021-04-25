package xueqiao.quotation.race.store;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.protocol.TCompactProtocol;
import org.soldier.base.logger.AppLog;

import net.spy.memcached.MemcachedClient;

public class MemcacheqReader<T extends TBase> {
	public static interface Listener {
		public void onReadMemcacheqStart();
		public void onReadMemcacheqFailed();
	}
	
	private MemcachedClient client;
	private TDeserializer deserializer;
	private String queueKey;
	private Class<T> itemClass;
	private Listener listener;
	
	public MemcacheqReader(String queueKey, Class<T> itemClass) {
		this.queueKey = queueKey;
		this.itemClass = itemClass;
		
		String hostAddr = System.getenv("HOST_ADDR");
		try {
		    if (StringUtils.isEmpty(hostAddr)) {
		        client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 22201));
		    } else {
		        client = new MemcachedClient(new InetSocketAddress(hostAddr, 22201));
		    }
		 } catch (IOException e) {
             AppLog.f(e.getMessage(), e);
         }
		
		deserializer = new TDeserializer(new TCompactProtocol.Factory());
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	public void destroy() {
		client.shutdown();
	}
	
	public T readItem() {
		T item = null;
		while(item == null) {
			try {
				if (listener != null) {
					listener.onReadMemcacheqStart();
				}
				
				String itemValue = (String)client.get(queueKey);
				if (itemValue == null || itemValue.isEmpty()) {
					Thread.sleep(10);
					continue;
				}
				
				byte[] packet = Base64.decodeBase64(itemValue);
				if (packet == null) {
					AppLog.e("decodeBase64 Failed for Item Value=" + itemValue
							+ ",Class=" + itemClass.getName());
					continue;
				}
			
				item = itemClass.newInstance();
				deserializer.deserialize(item, packet);
			} catch (Throwable e) {
				AppLog.e(e.getMessage(), e);
				if (listener != null) {
					listener.onReadMemcacheqFailed();
				}
				
				item = null;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		return item;
	}
	
}
