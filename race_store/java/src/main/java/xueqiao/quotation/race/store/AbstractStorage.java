package xueqiao.quotation.race.store;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.thrift.TBase;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.soldier.base.HashAlgorithms;
import org.soldier.base.StringFactory;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;
import org.soldier.platform.zookeeper.IConfProvider.ConfException;
import org.soldier.platform.zookeeper.ZooKeeperManager;
import org.soldier.platform.zookeeper.ZooKeeperManagerFactory;

import net.qihoo.qconf.Qconf;
import net.qihoo.qconf.QconfException;

public abstract class AbstractStorage<T extends TBase> extends Thread implements MemcacheqReader.Listener{
	private static final int CONTRACT_ID_MAKER_TYPE = 100;
	private static final String CONTRACT_PATH_SUFFIX = "xueqiao/quotation/contract/ids";
	
	private static class WorkThread extends Thread {
		private BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
		private volatile boolean ending = false;
		
		public WorkThread(String name) {
			super(name);
			this.setDaemon(true);
		}
		
		public void startWork() {
			this.ending = false;
			this.start();
		}
		
		public void stopWork() {
			this.ending = true;
			this.interrupt();
		}
		
		public void postTask(Runnable r) throws InterruptedException {
			queue.put(r);
		}
		
		@Override
		public void run() {
			while(!ending) {
				try {
					Runnable r = queue.poll(100, TimeUnit.MILLISECONDS);
					if (r != null) {
						r.run();
					}
				} catch (Throwable e) {
					AppLog.e(e.getMessage(), e);
				}
			}
			
			Runnable r = queue.poll();
			if (r != null) {
				try {
					r.run();
				} catch (Throwable e) {
					AppLog.e(e.getMessage(), e);
				}
			}
		}
	}
	
	private MemcacheqReader<T> reader;
	private volatile boolean ending = false;
	private Class<T> itemClass;
	private List<WorkThread> workThreads = new ArrayList<WorkThread>();
	protected ZooKeeperManager quotationZooKeeperManager;
	
	protected volatile List<String> ignorePlatforms = new ArrayList<String>();
	
	private static AtomicInteger sLastWorkerId = new AtomicInteger(0);
	
	protected AbstractStorage(
			String queueKey
			, Class<T> itemClass
			, int workNum) {
		try {
			this.quotationZooKeeperManager = ZooKeeperManagerFactory.Global().get("quotation");
		} catch (Exception e) {
			AppLog.e(e.getMessage(), e);
			throw new IllegalStateException("can not init quotation zookeeper");
		}
			
		this.itemClass = itemClass;
		this.setName("Storage-" + itemClass.getSimpleName() + "-Master");
		this.reader = new MemcacheqReader<T>(queueKey, itemClass);
		this.reader.setListener(this);
		setWorkNum(workNum);
	}
	
	public void setIgnorePlatforms(List<String> platforms) {
		if (platforms == null) {
			this.ignorePlatforms = new ArrayList<String>();
			return ;
		}
		
		this.ignorePlatforms = platforms;
	}
	
	public void startStoring() {
		setDaemon(true);
		ending = false;
		start();
	}
	
	public void stopStoring() {
		ending = true;
		interrupt();
		reader.destroy();
	}
	
	public void setWorkNum(int workNum) {
		if (workNum < 0) {
			workNum = 1;
		}
		
		synchronized(this) {
			if (workThreads.size() == workNum) {
				return ;
			}
			
			if (workThreads.size() < workNum) {
				int needAddThreads = workNum - workThreads.size();
				for (int i = 0; i < needAddThreads; ++i) {
					WorkThread workThread = new WorkThread("Storage-" + itemClass.getSimpleName() 
						+ "-Worker" + sLastWorkerId.getAndIncrement());
					workThread.startWork();
					workThreads.add(workThread);
				}
			} else {
				int needRemoveThreads = workThreads.size() - workNum;
				for (int i = 0; i < needRemoveThreads; ++i) {
					WorkThread workThread = workThreads.get(workThreads.size() - 1);
					workThread.stopWork();
					workThreads.remove(workThread);
				}
			}
		}
		
	}
	
	@Override
	public void run() {
		while(!ending) {
			final T item = reader.readItem();
			try {
				if (item == null) {
					Thread.sleep(1);
				}
				
				onReadItem(item);
				
				synchronized(this) {
					WorkThread workThread = workThreads.get(Math.abs(item.hashCode() % workThreads.size()));
					workThread.postTask(new Runnable() {
						@Override
						public void run() {
							while(!ending){
								try {
									if (onStoreItem(item)) {
										break;
									}
									Thread.sleep(50);
								} catch (Throwable e) {
									AppLog.e(e.getMessage(), e);
								}
							}
						}
					});
				}
			} catch (Throwable e) {
				AppLog.e(e.getMessage(), e);
			}
		}
	}
	
	private String getQConfName() throws ConfException {
		return ZooKeeperManagerFactory.Global().getQconfIDCName("quotation");
	}
	
	/**
	 * 构建行情条目的对应合约的唯一ID
	 * @throws IOException 
	 * @throws ConfException 
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	protected long getUniqueContractId(String platform, String contractSymbols) throws Exception {
		int contractHashCode = HashAlgorithms.JSHash(contractSymbols);
		
		StringBuilder pathBuilder = new StringBuilder(128);
		pathBuilder.append(CONTRACT_PATH_SUFFIX).append("/").append(platform)
				   .append("/").append(contractHashCode%1024)
				   .append("/").append(URLEncoder.encode(contractSymbols, "UTF-8"));
		
		long contractId = 0;
		String contractValue = null;
		try {
			contractValue = Qconf.getConf(pathBuilder.toString(), getQConfName());
		} catch (QconfException e) {
			AppLog.i("QconfException " + e.getMessage() + " for " + pathBuilder.toString());
		}
		
		if (contractValue == null) {
			String zookeeperNodePath = "/" + pathBuilder.toString();
			String platformNodePath = "/" + CONTRACT_PATH_SUFFIX + "/" + platform;
			String sliceNodePath = platformNodePath + "/" + String.valueOf(contractHashCode%1024);
			
			ZooKeeper zookeeper = quotationZooKeeperManager.getZooKeeper();
			
			if (null == zookeeper.exists(platformNodePath, false)) {
				zookeeper.create(platformNodePath, "".getBytes("UTF-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			
			if (null == zookeeper.exists(sliceNodePath, false)) {
				zookeeper.create(sliceNodePath, "".getBytes("UTF-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			
			Stat stat = zookeeper.exists(zookeeperNodePath, false);
			if (stat == null) {
				IdMaker maker = IdMakerFactory.getInstance().getIdMaker(CONTRACT_ID_MAKER_TYPE);
				contractId = maker.createId();
				
				zookeeper.create(zookeeperNodePath, String.valueOf(contractId).getBytes("UTF-8")
						, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			} else {
				contractValue = StringFactory.newUtf8String(zookeeper.getData(zookeeperNodePath, false, null));
			}
		} 
		
		if (contractValue != null && contractId <= 0) {
			contractId = NumberUtils.toLong(contractValue.trim());
			if (contractId <= 0) {
				throw new Exception("unexpected contractId");
			}
		}
		
		return contractId;
	}
	
	protected abstract void onReadItem(final T item);
	protected abstract boolean onStoreItem(final T item);
}
