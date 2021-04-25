package xueqiao.quotation.plan.bo.server.core;

import xueqiao.quotation.plan.bo.server.core.impl.PlannerDataProviderImpl;
import xueqiao.quotation.plan.bo.server.core.impl.PlannerImpl;

public class PlannerFactory {
    
    public static IPlanner createPlanner() {
        return new PlannerImpl();
    }
    
    public static IPlannerDataProvider createPlannerDataProvider() {
        return new PlannerDataProviderImpl();
    }
}
