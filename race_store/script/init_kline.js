
conn = new Mongo("mongodb://root:*@dds-uf645bbdd1c7cc941.mongodb.rds.aliyuncs.com:3717,dds-uf645bbdd1c7cc942.mongodb.rds.aliyuncs.com:3717/admin?replicaSet=mgset-4546049");

db = conn.getDB("KLine");

db.getCollectionNames().forEach(function(collectionName) {
    if (collectionName.startsWith("kline1")) {
        db[collectionName].drop();
    }
})

for (tableIndex = 0; tableIndex < 256; ++tableIndex) {
    var collectionName = "kline1m_"
    if (tableIndex < 10) {
        collectionName = collectionName + "000" + tableIndex;
    } else if (tableIndex >=10 && tableIndex < 100) {
        collectionName = collectionName + "00" + tableIndex;
    } else if (tableIndex >= 100 && tableIndex < 1000 ) {
        collectionName = collectionName + "0" + tableIndex;
    } else {
        collectionName = collectionName + tableIndex;
    }
    
    db[collectionName].insert({
        contractId : 1,
        platform : "init",
        contractSymbol : "init",
        kMinuteStartTimestampS : 0,
        shardKey : 0,
    });
    
    db[collectionName].ensureIndex({kMinuteStartTimestampS:-1, contractId:1}, {unique:true,dropDups:true});
}