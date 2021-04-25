package xueqiao.quotation.race.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class RaceStoreConfig {
	public static final String MONGODB_STORE_TYPE = "mongodb";
	public static final String HBASE_STORE_TYPE = "hbase";
	public static final String DROP_STORE_TYPE = "drop";
	public static final String LOG_STORE_TYPE = "log";
	
	private String storeType = MONGODB_STORE_TYPE;
	
	/**
	 *  MONGODB 存储配置类型
	 */
	private String mongoKLineClientURI = "";
	private String kLineDBName = "KLine";
	private int kLineTableCount = 256;
	private int parallelKLineWorkNum = 2;
	
	private String mongoQuotationClientURI = "";
	private String quotationDBName = "Quotation";
	private int quotationTableCount = 1024;
	private int parallelQuotationWorkNum = 4;
	
	/**
	 * HBASE相关配置
	 */
	private Map<String, String> kLineHbaseConfiguration = new HashMap<String, String>();
	private Map<String, String> quotationHBaseConfiguration = new HashMap<String, String>();
	
	/**
	 * Log的相关配置
	 */
	private String logBaseDir =  "/data/quotation";
	
	/**
	 *  存储忽略的平台列表
	 */
	private List<String> ignorePlatforms = new ArrayList<String>();
	
	public String toJsonString() {
		return new Gson().toJson(this);
	}
	
	public String getMongoKLineClientURI() {
		return mongoKLineClientURI;
	}

	public void setMongoKLineClientURI(String mongoKLineClientURI) {
		this.mongoKLineClientURI = mongoKLineClientURI;
	}

	public String getMongoQuotationClientURI() {
		return mongoQuotationClientURI;
	}

	public void setMongoQuotationClientURI(String mongoQuotationClientURI) {
		this.mongoQuotationClientURI = mongoQuotationClientURI;
	}

	public String getKLineDBName() {
		return kLineDBName;
	}

	public void setKLineDBName(String kLineDBName) {
		this.kLineDBName = kLineDBName;
	}

	public String getQuotationDBName() {
		return quotationDBName;
	}

	public void setQuotationDBName(String quotationDBName) {
		this.quotationDBName = quotationDBName;
	}

	public int getKLineTableCount() {
		return kLineTableCount;
	}

	public void setKLineTableCount(int kLineTableCount) {
		this.kLineTableCount = kLineTableCount;
	}

	public int getQuotationTableCount() {
		return quotationTableCount;
	}

	public void setQuotationTableCount(int quotationTableCount) {
		this.quotationTableCount = quotationTableCount;
	}

	public int getParallelKLineWorkNum() {
		return parallelKLineWorkNum;
	}

	public void setParallelKLineWorkNum(int parallelKLineWorkNum) {
		this.parallelKLineWorkNum = parallelKLineWorkNum;
	}

	public int getParallelQuotationWorkNum() {
		return parallelQuotationWorkNum;
	}

	public void setParallelQuotationWorkNum(int parallelQuotationWorkNum) {
		this.parallelQuotationWorkNum = parallelQuotationWorkNum;
	}

	public List<String> getIgnorePlatforms() {
		return ignorePlatforms;
	}

	public void setIgnorePlatforms(List<String> ignorePlatforms) {
		this.ignorePlatforms = ignorePlatforms;
	}
	
//	public static void main(String[] args) {
//		Gson gson = new Gson();
//		RaceStoreConfig config = null;
//		try {
//			config = gson.fromJson(new FileReader(new File("C:\\Users\\wileywang\\Desktop\\store.json")), RaceStoreConfig.class);
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		System.out.println(config.kLineHbaseConfiguration.get("hbase.master"));
//	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public Map<String, String> getKLineHbaseConfiguration() {
		return kLineHbaseConfiguration;
	}

	public void setKLineHbaseConfiguration(Map<String, String> kLineHbaseConfiguration) {
		this.kLineHbaseConfiguration = kLineHbaseConfiguration;
	}

	public Map<String, String> getQuotationHBaseConfiguration() {
		return quotationHBaseConfiguration;
	}

	public void setQuotationHBaseConfiguration(Map<String, String> quotationHBaseConfiguration) {
		this.quotationHBaseConfiguration = quotationHBaseConfiguration;
	}

    public String getLogBaseDir() {
        return logBaseDir;
    }

    public void setLogBaseDir(String logBaseDir) {
        this.logBaseDir = logBaseDir;
    }
}
