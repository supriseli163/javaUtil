package com.util.java.util.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class JDKConcurrentTest {

    private CountDownLatch latch = new CountDownLatch(3);

    /**
     * ConcurrentHashMap实现了concurrentMap
     */
    @Test
    public void testConcurrentHashMap() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("key", "value");
        Object value = concurrentHashMap.get("key");
        assertEquals(value, "value");
    }

    @Test
    public void testCountDownLatch() {
        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();
    }

    @Test
    public void testExecutorService() throws ExecutionException, InterruptedException {
        /**
         * Executor.newFixedThreadPool();
         *          newCached
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        /**
         * Lambda表达式展开:
         *  executorService.execute(new Runnable() {
         *  @Override
         *  public void run() {
         *      System.err.println("asynchronous task")
         *   }
         *  });
         *
         * 任务委派机制:将System.err.println("asynchronous task")任务委派给executorService
         */
        executorService.execute(() -> System.err.println("asynchronous task"));

        /**
         * 如果需要得知任务的执行结果,可以使用回调callback
         * executorService.submit
         */
        Future voidFuture = executorService.submit(() -> System.err.println("asynchronous task"));
        /**
         * return null if the task has finished correctly.
         */
        Object voidResult = voidFuture.get();
        assertNull(voidResult);

        /**
         * submit(callable)方法除了类似于submit的类型参数之外
         * callable的结构可以通过submit(callback)方法返回future对象进行获取
         */
        Future future = executorService.submit(() -> {
            System.err.println("asynchronous task");
            return "CONCURRENT";
        });
        Object result = future.get();
        assertEquals(result, "CONCURRENT");
        executorService.shutdown();
    }

    /**
     * Atomic,提供原子性操作
     *
     */
    @Test
    public void testAtomInteger() {
        AtomicInteger number = new AtomicInteger(123);
        int expectedValue = 123;
        int newValue = 234;
        /**
         * 将expectedValue 与number值进行比较,如果二者相等,则把newValue值赋给number
         */
        number.compareAndSet(expectedValue, newValue);
        System.err.println(number);
        assertEquals(newValue, number.get());
        assertEquals(235, number.incrementAndGet());
    }


    /**
     * ThreadPoolExecutor
     * corePoolSize
     * maximumPoolSize
     */
    @Test
    public void testThreadPoolExecutor() {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keeperAliveTime = 5000;

        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keeperAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()
                );
        threadPoolExecutor.execute(() -> {
            System.err.println("asynchronous task");
        });

        threadPoolExecutor.shutdown();
        boolean isShutDown = threadPoolExecutor.isShutdown();
        boolean isTerminated = threadPoolExecutor.isTerminated();
        assertTrue(isShutDown);
        assertFalse(isTerminated);
    }

    /**
     *
     */
    @Test
    public void testScheduledThreadPoolExecutor() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule((Callable) () -> {
            System.err.println("schedule thread pool");
            return "scheduledExecutorService";
        }, 5, TimeUnit.SECONDS);
        Object result = scheduledFuture.get();
        assertEquals(result, "scheduledExecutorService");
    }


    public class Waiter implements Runnable {
        CountDownLatch latch = null;

        public Waiter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                ;
            }
            System.err.println("Waiter Released");
        }
    }

    public class Decrementer implements Runnable {
        CountDownLatch latch = null;

        public Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                this.latch.countDown();

                Thread.sleep(1000);
                this.latch.countDown();

                Thread.sleep(1000);
                this.latch.countDown();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
