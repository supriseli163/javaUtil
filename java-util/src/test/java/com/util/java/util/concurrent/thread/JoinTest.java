package com.util.java.util.concurrent.thread;

import com.google.protobuf.ServiceException;

public class JoinTest {
    /**
     * 如果一个线程A执行了thread.join()语句,其含义是:当前线程A等待thread线程终止之后才从thread.join()返回,线程Thread除了提供join()
     * 方法之外,还提供了join(long mills)和join(long mills, int nanos)两个具备超时特性的方法.
     *
     * 这两个超时方法表示,如果线程thread在给定的时间里没有被终止
     *
     *
     * 当线程终止时,会调用线程自身的notifyAll()方法,会通知所有等待在该线程对象上的线程,可以看到join()方法的逻辑结构与4.3.3节中描述的等待
     * /通知经典范式一致,即加锁,循环和处理逻辑.
     * @param args
     * @throws ServiceException
     */
    public static void main(String[] args) throws ServiceException {
        Thread previous = Thread.currentThread();
        for(int i = 0; i < 10; i ++) {
            Thread thread = new Thread(new Domio(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
    }

    static class Domio implements Runnable {
        private Thread thread;
        public Domio(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                thread.join();
            } catch (InterruptedException ex) {

            }
            System.out.print(Thread.currentThread().getStackTrace());
            System.out.print(Thread.currentThread().getName());
            System.out.print(Thread.currentThread().countStackFrames());
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
