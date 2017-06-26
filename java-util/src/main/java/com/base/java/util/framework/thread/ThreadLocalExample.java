package com.base.java.util.framework.thread;

public class ThreadLocalExample {
    public static class MyRunnable implements Runnable {
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();


        @Override
        public void run() {
            threadLocal.set((int)(Math.random() * 10D));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {

            }
            System.out.print(threadLocal.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable staredRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread();
        Thread thread2 = new Thread();

        thread1.start();
        thread2.start();

        //wait for thread 1 to terminate
        thread1.join();
        //wait for thread 2 to terminate
        thread2.join();
    }
}
