package com.util.java.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimitTest {

    /**
     * 1.漏桶算法
     *  漏桶算法
     */
    static class Task implements Callable<Integer> {
        String str;

        public Task(String str) {
            this.str = str;
        }


        @Override
        public Integer call() throws Exception {
            System.out.println(System.currentTimeMillis()/1000 + "-------" + Thread.currentThread().getName() + "-call execute.." + str);
            TimeUnit.SECONDS.sleep(10);
            return 7;
        }
    }

    @Test
    public void testRateLimiter() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        /**每秒钟不超过3个任务被提交*/
        RateLimiter limiter = RateLimiter.create(3.0);

        for(int i = 0; i < 10; i++) {
            limiter.acquire();  //请求rateLimiter,超过permits会被阻塞
        }
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task(String.format("is %s", limiter.getRate())));
    }
}
