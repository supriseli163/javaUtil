package com.util.java.util.lock;

import com.base.java.util.lock.TwinsLock;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
    @Test
    public void test() throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for(int i = 0; i < 10; i++) {
            Worker  w = new Worker();
            w.setDaemon(true);
            w.start();
            w.run();
        }

        //每隔一秒执行
        for(int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println();
        }



    }
}
