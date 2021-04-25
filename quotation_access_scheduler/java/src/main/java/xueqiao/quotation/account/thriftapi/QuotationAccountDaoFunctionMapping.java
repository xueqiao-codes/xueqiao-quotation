package xueqiao.quotation.account.thriftapi;


import java.util.HashMap;
import java.util.Map; 

public class QuotationAccountDaoFunctionMapping {

  private static Map<String, Integer> sMapping = new HashMap<String, Integer>();

  static {
    putEntry("reqContractActiveRule",1);
    putEntry("reqContractRegisterRule",2);
    putEntry("reqQuotationAccount",3);
    putEntry("addContractActiveRule",4);
    putEntry("addContractRegisterRule",5);
    putEntry("addQuotationAccount",6);
    putEntry("updateContractActiveRule",7);
    putEntry("updateContractRegisterRule",8);
    putEntry("updateQuotationAccount",9);
    putEntry("removeContractActiveRule",10);
    putEntry("removeContractRegisterRule",11);
    putEntry("removeQuotationAccount",12);
    putEntry("reqAccountCommodityRegisterAbility",14);
    putEntry("addAccountCommodityRegisterAbility",15);
    putEntry("removeAccountCommodityRegisterAbility",16);
    putEntry("batAddAccountCommodityRegisterAbility",17);
    putEntry("removeAccountCommodityRegisterAbilities",19);
    putEntry("reqQuotationAccountSupport",18);
    putEntry("setCommodityRegisterOrderIndex",21);
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
