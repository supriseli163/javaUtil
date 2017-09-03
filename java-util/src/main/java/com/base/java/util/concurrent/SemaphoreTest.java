package com.base.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {
    /**
     * Semaphore翻译成字面意思为    信号量
     * Semaphore可以控制同时访问的线程个数,通过accquire()获取一个许可,如果没有就等待,而release()释放一个许可
     *
     *
     * CountDownLatch和CyclicBarrier都能够实现线程之间的等待,只不过他们的侧重点不同
     *
     * countDownLatch一般用于某个线程A等待若干其他线程执行完成任务之后,它才执行
     * 而
     */
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for(int i = 0 ; i < THREAD_COUNT; i ++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data" + atomicInteger.incrementAndGet());
                        //s.release();
                    } catch (InterruptedException ex) {

                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
