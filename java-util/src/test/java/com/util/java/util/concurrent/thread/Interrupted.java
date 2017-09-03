package com.util.java.util.concurrent.thread;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Interrupted {
    /**
     * 中断可以理解为一个标志位,他表示一个运行中的
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //sleepThread不停地尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        //busyThread不停的运行
        Thread busyThread = new Thread(new Thread(new BusyRunner()), "BusyThread");

        sleepThread.start();
        busyThread.start();

        //休眠5s,让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();

        busyThread.interrupt();

        System.err.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.err.println("BusyThread interrupted is " + busyThread.isInterrupted());
        //防止sleepThread和busyThread立刻腿出

        Thread.sleep(2);

    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true) {

            }
        }
    }

    /**
     * Thread中,join()方法是调用线程等待该线程完成之后,才能继续往下运行
     */
    @Test
    public void join() throws InterruptedException {
        System.err.println("main thread start");

        Thread t1 = new Thread("Thread-1");
        t1.start();
        t1.join();

        System.err.println("main end");

    }
}
