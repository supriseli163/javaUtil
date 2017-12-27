package com.base.java.util.algrothim.queue.ringbuffer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
public class SimpleRingBufferMonitor<T> {
    private volatile boolean stoped = false;
    private String name;
    private IBufferHandler<T> iBufferHandler;
    private RingBuffer<T> ringBuffer;
    private static final AtomicInteger id = new AtomicInteger(1);
    private WorkerThread workerThread;

    public SimpleRingBufferMonitor(String name, RingBuffer<T> ringBuffer, IBufferHandler<T> iBufferHandler) {
        this.ringBuffer = ringBuffer;
        this.iBufferHandler = iBufferHandler;
        this.name = name;
    }

    public SimpleRingBufferMonitor(RingBuffer<T> ringBuffer, IBufferHandler<T> iBufferHandler) {
        this("RingBuffer-Monitor" + id.getAndIncrement(), ringBuffer, iBufferHandler);
    }

    public void start() {
        if(stoped) {
            throw new IllegalStateException("");
        }
        stoped = false;
    }

    public void end() {
        if(!stoped) {
            throw new IllegalStateException("");
        }
        stoped = true;
        workerThread = null;
    }

    public class WorkerThread extends Thread {
        WorkerThread(String name) {
            super(name);
        }

        public void run() {
            boolean flag = iBufferHandler.enableBatchHandle();
            int time = 0;
            /**是否是出于批量处理模式*/
            if(flag) {
                while (!stoped) {
                    try {
                        List<T> valList = ringBuffer.popAll();
                        if(valList == null || valList.isEmpty()) {
                            time = sleepTime(time);
                        } else {
                            time = 0;
                            iBufferHandler.handle(valList);
                        }
                    } catch (Throwable throwable) {
                        // don't to block the pop method
                        System.err.println(throwable.getMessage());
                    }
                }
            } else {
                while (!stoped) {
                    try {
                        T val = ringBuffer.pop();
                        if(val == null) {
                            time = sleepTime(time);
                        } else {
                            time = 0;
                            iBufferHandler.handle(val);
                        }
                    } catch (Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
            }
        }
    }

    public static int sleepTime(int time) throws InterruptedException {
        Thread.sleep(time * 100);
        time = time < 16 ? time + 1 : 20;
        return time;
    }
}
