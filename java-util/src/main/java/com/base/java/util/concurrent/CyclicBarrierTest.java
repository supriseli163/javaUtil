package com.base.java.util.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    /**
     * CyclicBarrier的字面意思是可循环使用(Cyclic)的屏障(Barrier)
     *
     * 他要做的事情是:让所有线程到达一个屏障(也可以加做同步点)时被阻塞,知道最后一个线程到达屏障时,
     * 屏障才会打开门,所有被屏障拦截的线程才会继续执行
     *
     *
     * 1.CyclicBarrier初始化时规定一个数目,然后计算调用了CyclicBarrier.await()进入线程等待的线程数,当线程数到达了这个数目时,所有
     * 进入zha
     *
     */

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception ex) {

                }
                System.err.println(1);
            }
        }).start();

        try {
            c.await();
        }catch (Exception ex) {

        }
        System.err.println(2);
    }
}
