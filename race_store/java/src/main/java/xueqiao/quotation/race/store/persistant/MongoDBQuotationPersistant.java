package xueqiao.quotation.race.store.persistant;

import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.util.Json;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import xueqiao.quotation.race.store.HashedKLineQuotationMinuteItem;
import xueqiao.quotation.race.store.HashedQuotationItem;

public class MongoDBQuotationPersistant implements IQuotationPersistant {
	private MongoClient client;
	private String dbName;
	private int tableCount;
	
	public MongoDBQuotationPersistant(String uri
			, String dbName
			, int tableCount) {
		this.client = new MongoClient(new MongoClientURI(uri));
		this.dbName = dbName;
		this.tableCount = tableCount;
	}
	
	@Override
	public void onStoreKLineMinuteItem(long uniqueContractId, HashedKLineQuotationMinuteItem item) {
		String jsonItem = Json.toJson(item);
		
		String collectionName = String.format("kline1m_%04d", uniqueContractId % tableCount);
		
		if (AppLog.debugEnabled()) {
			AppLog.d("onStoreItem KLineQuotationMinuteItem, " + jsonItem + ", contractId=" 
						+ uniqueContractId);
		}
	
		MongoDatabase db = client.getDatabase(dbName);
		
		MongoCollection<Document> collection = db.getCollection(collectionName);
	
		Document record = Document.parse(Json.toJson(item));
		record.append("contractId", uniqueContractId);
		
		Bson filter = Filters.eq("contractId", uniqueContractId);
		filter = Filters.and(filter, Filters.eq("kMinuteStartTimestampS", item.getKMinuteStartTimestampS()));
	
		UpdateOptions options = new UpdateOptions();
		options.upsert(true);
	
		collection.replaceOne(filter, record, options);
	}

	@Override
	public void onStoreQuotationItem(long uniqueContractId, HashedQuotationItem item) {
		String jsonItem = Json.toJson(item);
	
		if (AppLog.debugEnabled()) {
			AppLog.d("onStoreItem QuotationItem, " + jsonItem + ", contractId=" + uniqueContractId);
		}
	
		MongoDatabase db = client.getDatabase(dbName);
		
		String collectionName = String.format("quotation_%04d", uniqueContractId % tableCount);
	
		MongoCollection<Document> collection = db.getCollection(collectionName);
	
		Document record = Document.parse(Json.toJson(item));
		record.append("contractId", uniqueContractId);
		record.append("raceAt", new Date(item.getRaceTimestampMs()));
	
		collection.insertOne(record);
	}
	
	public void destroy() {
		IOUtils.closeQuietly(client);
	}

}
