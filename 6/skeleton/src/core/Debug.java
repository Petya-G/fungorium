package core;

public class Debug {
    public static void DBGFUNC(String msg) {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        System.out.println("DBG> " + stackTraces[2] + ": " + msg);
    }
}
