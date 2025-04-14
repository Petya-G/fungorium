package model.core;

public class Debug {

    /**
     * Debuggolást segítő függvény, kiírja a hivóját és egy üzenetet
     *
     * @param msg A kiírandó üzenet
     */
    public static void DBGFUNC(String msg) {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        System.out.println("DBG> " + stackTraces[2] + ": " + msg);
    }
}
