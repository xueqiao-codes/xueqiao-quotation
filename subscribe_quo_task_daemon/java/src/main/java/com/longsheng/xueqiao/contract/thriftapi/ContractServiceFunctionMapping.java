package com.longsheng.xueqiao.contract.thriftapi;


import java.util.HashMap;
import java.util.Map; 

public class ContractServiceFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("reqSledContractDetail",2);
    putEntry("reqSledCommodity",6);
    putEntry("reqSledExchange",7);
    putEntry("addCommodityMapping",8);
    putEntry("addSledExchange",9);
    putEntry("reqCommodityMapping",10);
    putEntry("addSledCommodity",11);
    putEntry("updateSledExchange",12);
    putEntry("updateSledCommodity",13);
    putEntry("updateCommodityMapping",14);
    putEntry("importCommodityMapFile",30);
    putEntry("reqCommodityMapFileInfo",31);
    putEntry("linkTechPlatformMapping",32);
    putEntry("reqTechPlatformCommodity",33);
    putEntry("addTechPlatformCommodity",34);
    putEntry("activeSledCommodity",35);
    putEntry("verifySledCommodity",36);
    putEntry("removeSledCommodity",37);
    putEntry("removeSledExchange",38);
    putEntry("changeSledCommodityState",39);
    putEntry("addDbLocking",40);
    putEntry("removeDbLocking",41);
    putEntry("reqDbLockingInfo",42);
    putEntry("syncContract",43);
    putEntry("removeCommodityMapFileInfo",44);
    putEntry("addSledTradeTimeConfig",60);
    putEntry("updateSledTradeTimeConfig",61);
    putEntry("reqSledTradeTimeConfig",62);
    putEntry("addSpecTradeTime",63);
    putEntry("updateSpecTradeTime",64);
    putEntry("reqSpecTradeTime",65);
    putEntry("reqSledCommoditySpecTradeTime",66);
    putEntry("syncTradeTime",67);
    putEntry("reqSledTradeTime",68);
    putEntry("addDstTransferConfig",69);
    putEntry("updateDstTransferConfig",70);
    putEntry("reqDstTransferConfig",71);
    putEntry("removeSpecTradeTime",72);
    putEntry("removeDstTransferConfig",73);
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
