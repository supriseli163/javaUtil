package com.util.java.util.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JDKLockTest {
    /**
     * jdk Lock
     * <p>
     * Lock的一般用法:
     * Lock lock = new ReentrantLock()
     * lock.lock;
     * try {
     * //可能出现线程安全的操作
     * } finally {
     * //一定在finally中释放锁,也不能把获取锁在try中执行,因为有可能在获取锁的时候抛出异常
     * lock.unlock();
     * }
     */
    public class LockTest {
        //声明一个ReentrantLock
        private Lock lock;
        private final AtomicInteger integer = new AtomicInteger(100);
        double value = 0d;

        /**
         * 增加value的值
         */
        public void addValue(double v) throws InterruptedException {
            lock = new ReentrantLock();
            lock.lock();
            try {
                System.err.println("LockTest to addValue:" + v + " " + System.currentTimeMillis());
                Thread.sleep(1000);
                this.value += v;
                this.integer.incrementAndGet();
            } finally {
               lock.unlock();
            }
        }

        public double getValue() {
            return this.value;
        }
    }

    @Test
    public void testLock() throws ExecutionException, InterruptedException {
        final LockTest lockTest = new LockTest();
        Runnable task1 = () -> {
            try {
                lockTest.addValue(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> System.err.println("value:" + lockTest.getValue() + " " + System.currentTimeMillis() + "," + lockTest.integer);

        //new ExecutorServie
        ExecutorService cachedService = Executors.newCachedThreadPool();
        Future future = null;
        for(int i = 0; i < 3; i++) {
            //同一个任务执行三次
            future = cachedService.submit(task1);
        }
        //等待最后一个任务1被执行完
        future.get();
        //再执行任务2,输出结果
        future = cachedService.submit(task2);
        future.get();
        cachedService.shutdownNow();
    }
}
