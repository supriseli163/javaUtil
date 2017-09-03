package com.base.data.access.alyer.util;

import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class QPSCounter {
    private static final Logger logger = Logger.getLogger(QPSCounter.class.getName());
    private static final int SMALL_PERIOD = 100;

    /**记录1分钟内的QPS*/
    private static final QPSCounter QPS_COUNTER = new QPSCounter(60 * 1000 / SMALL_PERIOD);

    private static QPSCounter getInstance() {
        return QPS_COUNTER;
    }

    public static class WindowQPS {
        private final ConcurrentHashMap<Integer, AtomicInteger> qps = new ConcurrentHashMap<>();
        private final long from;
        private long to;

        private WindowQPS(long from) {
            this.from = from;
        }

        @Override
        public String toString() {
            if(this == EMPTY_QPS) {
                return "EMPTY_WindowQPS";
            }
            return "WindowQPS [qps" + qps + ", from" + from + ", to=" + to + "]";
        }

        public static final WindowQPS EMPTY_QPS = new WindowQPS(0);
    }

    QPSCounter(int length) {
        this.
    }

    public static AtomicInteger f(AtomicInteger f) {
        f.incrementAndGet();
        return f;
    }

    private void incrQSP(Scheduler)  {

    }

    public int getQps(int schedId, int fetchPerdoi) {
        if(fetchPerdoi < 0) {
            return -1;
        }


    }

    private void appendOnePerriodQPS() {
        long now = System.currentTimeMillis();
        if(now - )
    }




}
