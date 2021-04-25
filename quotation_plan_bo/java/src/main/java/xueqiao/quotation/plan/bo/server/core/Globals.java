package xueqiao.quotation.plan.bo.server.core;

public class Globals {
    public static String getDalSetRoleName() {
        return "role_xueqiao_quotplan";
    }
    
    private static TaskThread sGenerateThread;
    public static TaskThread getPlanGenerateThread() {
        if (sGenerateThread == null) {
            synchronized(Globals.class) {
                if (sGenerateThread == null) {
                    sGenerateThread = new TaskThread();
                }
            }
        }
        return sGenerateThread;
    }
}
