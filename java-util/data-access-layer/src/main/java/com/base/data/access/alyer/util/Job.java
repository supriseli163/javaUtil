package com.base.data.access.alyer.util;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Job implements Delayed {
    private static final Logger logger = Logger.getLogger(Job.class.getName());
    public final JobMethod jobMethod;
    private final JobScheduler jobScheduler;
    private volatile long intervalInMilli;
    public final String name;
    protected long nextTimsStampInMill = System.currentTimeMillis();

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
