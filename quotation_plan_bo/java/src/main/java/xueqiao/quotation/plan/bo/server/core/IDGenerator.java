package xueqiao.quotation.plan.bo.server.core;

import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.id_maker.IdMakerFactory;

public class IDGenerator {
    private static IdMaker sClassAndItemIdMaker;
    
    public static void init() {
        sClassAndItemIdMaker = IdMakerFactory.getInstance().getIdMaker(300);
        if (sClassAndItemIdMaker == null) {
            throw new Error("Get IdMaker for type=300 failed!");
        }
    }
    
    public static long generateClassId() throws IdException {
        return sClassAndItemIdMaker.createId();
    }
    
    public static long generateItemId() throws IdException {
        return sClassAndItemIdMaker.createId();
    }
}
