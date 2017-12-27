package com.base.java.util.framework.thread;

import java.util.concurrent.ThreadFactory;

public enum  DaemonThreadFactory implements ThreadFactory {
    INSTANCE,

    ;

    @Override
    public Thread newThread(final Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(false);
        return thread;
    }
}
