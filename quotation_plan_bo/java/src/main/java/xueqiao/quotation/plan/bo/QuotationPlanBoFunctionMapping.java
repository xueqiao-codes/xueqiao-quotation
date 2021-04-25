package xueqiao.quotation.plan.bo;


import java.util.HashMap;
import java.util.Map; 

public class QuotationPlanBoFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("startGenPreviewSCClasses",1);
    putEntry("getGenPreviewState",2);
    putEntry("commitPreviewSCClasses",3);
    putEntry("querySubscribeContractItemPage",4);
    putEntry("getCurrentSCClasses",5);
    putEntry("getPreviewSCClasses",6);
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
