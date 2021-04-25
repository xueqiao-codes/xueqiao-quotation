

conn = new Mongo("mongodb://root:*@dds-uf645bbdd1c7cc941.mongodb.rds.aliyuncs.com:3717,dds-uf645bbdd1c7cc942.mongodb.rds.aliyuncs.com:3717/admin?replicaSet=mgset-4546049");

db = conn.getDB("Quotation");

db.getCollectionNames().forEach(function(collectionName) {
    if (collectionName.startsWith("quotation_")) {
        db[collectionName].drop();
    }
})

for (tableIndex = 0; tableIndex < 1024; ++tableIndex) {
    var collectionName = "quotation_"
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
        raceTimestampMs : NumberLong(1506654594000),
        shardKey : 0,
        raceAt : new Date()
    });
    
    db[collectionName].ensureIndex({raceTimestampMs:-1, contractId:1}, {unique:false});
    db[collectionName].createIndex({raceAt:1}, {expireAfterSeconds:1296000})
}
