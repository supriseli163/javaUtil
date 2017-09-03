package com.base.data.access.alyer.util.rmq;

import com.base.data.access.alyer.NoThrow;
import com.base.data.access.alyer.netty.AthenaTcpServer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AuditSqlToRmq {
    private static final int SEND_THRES = 1000;
    private static final int PATTERN_SIZE = 1000;

    private static final long FLUASH_INTERVAL = TimeUnit.SECONDS.toMillis(10);
    private static RmqSender sender = new RmqSender();

    private final Queue<RmqSqlInfo> sqlInfoList = new ConcurrentLinkedDeque<>();
    private final AtomicInteger currentBufferSize = new AtomicInteger(0);

    private AuditSqlToRmq() {
        AthenaTcpServer
    }

    private static class INNER {
        private static final AuditSqlToRmq INSTANCE = new AuditSqlToRmq();
    }

    public static AuditSqlToRmq getInstance() {
        return INNER.INSTANCE;
    }

    public void reset() {
        if(currentBufferSize.get() != 0) {
            sqlInfoList.clear();
            currentBufferSize.getAndSet(0);
        }
        if(!patternSet.)
    }

    public void resetChannelOfRmqSender() {

    }

    private void sendSqlInfo(RmqSqlInfo sqlInfo) {
        NoThrow.call(() -> {
            String infoStr = J
        });
    }
}
