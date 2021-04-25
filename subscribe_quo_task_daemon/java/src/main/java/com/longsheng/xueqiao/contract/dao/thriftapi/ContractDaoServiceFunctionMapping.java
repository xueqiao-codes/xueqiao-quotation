package com.longsheng.xueqiao.contract.dao.thriftapi;


import java.util.HashMap;
import java.util.Map; 

public class ContractDaoServiceFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("addTSledCommodity",1);
    putEntry("updateTSledCommodity",2);
    putEntry("reqTSledCommodity",3);
    putEntry("addTSledContract",4);
    putEntry("updateTSledContract",5);
    putEntry("reqTSledContract",6);
    putEntry("addTSledExchange",7);
    putEntry("updateTSledExchange",8);
    putEntry("reqTSledExchange",9);
    putEntry("reqTCommodityMap",12);
    putEntry("addTCommodityMap",14);
    putEntry("addSledExchangeMapping",15);
    putEntry("updateSledExchangeMapping",16);
    putEntry("reqSledExchangeMapping",17);
    putEntry("addSledCommodityTypeMapping",18);
    putEntry("updateSledCommodityTypeMapping",19);
    putEntry("reqSledCommodityTypeMapping",20);
    putEntry("updateTCommodityMap",21);
    putEntry("inactiveExpiredSledContract",22);
    putEntry("reqTSledCommodityChange",23);
    putEntry("addTSledCommodityChange",24);
    putEntry("removeTSledCommodityChange",25);
    putEntry("addCommodityMapFileInfo",26);
    putEntry("updateCommodityMapFileInfo",27);
    putEntry("reqCommodityMapFileInfo",28);
    putEntry("reqSyncMappingTask",32);
    putEntry("addSyncMappingTask",34);
    putEntry("removeSyncMappingTask",35);
    putEntry("addTechPlatformCommodity",40);
    putEntry("reqTechPlatformCommodity",41);
    putEntry("removeSledCommodity",42);
    putEntry("removeSledExchange",43);
    putEntry("addContractVersion",44);
    putEntry("removeContractVersion",45);
    putEntry("reqContractVersion",46);
    putEntry("updateContractVersion",47);
    putEntry("addDbLocking",50);
    putEntry("removeDbLocking",51);
    putEntry("reqDbLockingInfo",52);
    putEntry("addSledTradeTimeConfig",60);
    putEntry("updateSledTradeTimeConfig",61);
    putEntry("reqSledTradeTimeConfig",62);
    putEntry("addSpecTradeTime",63);
    putEntry("updateSpecTradeTime",64);
    putEntry("reqSpecTradeTime",65);
    putEntry("reqSledCommoditySpecTradeTime",66);
    putEntry("reqSledTradeTime",67);
    putEntry("batAddSledTradeTime",68);
    putEntry("addDstTransferConfig",69);
    putEntry("updateDstTransferConfig",70);
    putEntry("reqDstTransferConfig",71);
    putEntry("removeSpecTradeTime",72);
    putEntry("removeDstTransferConfig",73);
    putEntry("batUpdateSledTradeTimeConfigs",74);
    putEntry("addCommoditySource",75);
    putEntry("updateCommoditySource",76);
    putEntry("reqCommoditySource",77);
    putEntry("addCommoditySourceAccount",78);
    putEntry("updateCommoditySourceAccount",79);
    putEntry("reqCommoditySourceAccount",80);
    putEntry("addSledTradingSession",81);
    putEntry("updateSledTradingSession",82);
    putEntry("reqSledTradingSession",83);
    putEntry("removeSledTradingSession",84);
    putEntry("clearAllTechPlatformCommodity",85);
  }

  public static int getUniqueNumber(String functionName) {
    Integer number = sMapping.get(functionName);
    if (number == null) {
      return -1;    }
    return number.intValue();
  }

  private static void putEntry(String functionName, int uniqueNumber) {
    sMapping.put(functionName, uniqueNumber);
  }

}
