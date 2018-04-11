package com.base.java.util.jvm;

public abstract class ShutDownUtil {
    protected void startShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public abstract void close() throws Exception;
}
