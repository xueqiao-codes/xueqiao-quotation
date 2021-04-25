package xueqiao.quotation.plan.bo.server.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Preconditions;

import xueqiao.quotation.account.thriftapi.ContractActiveRule;

public class ActiveRuleHelper {
    public static List<Integer> getActiveMonths(ContractActiveRule activeRule) {
        List<Integer> resultList = new ArrayList<>();
        for (int month = 1; month <= 12; ++month) {
            if (!activeRule.isSetActiveMonthMap()) {
                continue;
            }
            Boolean actived = activeRule.getActiveMonthMap().get(month);
            if (actived != null && actived) {
                resultList.add(month);
            }
        }
        return resultList;
    }
    
    public static List<Integer> getInactiveMonths(ContractActiveRule activeRule) {
        List<Integer> resultList = new ArrayList<>();
        for (int month = 1; month <= 12; ++month) {
            if (!activeRule.isSetActiveMonthMap()) {
                resultList.add(month);
                continue;
            }
            Boolean actived = activeRule.getActiveMonthMap().get(month);
            if (actived == null || !actived) {
                resultList.add(month);
            }
        }
        return resultList;
    }
    
    /**
     *  计算合约窗口
     */
    public static List<String> calContractWindow(int minWidowSize
            , List<Integer> refMonthList
            , int fromYear
            , int fromMonth) {
        Preconditions.checkArgument(minWidowSize > 0);
        Preconditions.checkArgument(!refMonthList.isEmpty());
        Preconditions.checkArgument(fromYear > 2000);
        Preconditions.checkArgument(fromMonth > 0);
        
        List<String> resultList = new ArrayList<>();
        
        int year = fromYear;
        while(true) {
            for (Integer refMonth: refMonthList) {
                if (year > fromYear || refMonth >= fromMonth) {
                    resultList.add(String.format("%02d%02d", year-2000, refMonth));
                }
            }
            
            if (resultList.size() >= minWidowSize) {
                break;
            }
            
            ++year;
        }
        
        return resultList;
    }
    
    public static void main(String[] args) {
        System.out.println(calContractWindow(10, Arrays.asList(3,10,11), 2018, 12));
    }
}
