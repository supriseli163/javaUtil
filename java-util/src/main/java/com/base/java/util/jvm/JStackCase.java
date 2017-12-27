package com.base.java.util.jvm;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JStackCase {
    public static Executor executor = Executors.newFixedThreadPool(5);
    public static Object lock = new Object();

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        executor.execute(task1);
        executor.execute(task2);
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                calculate();
            }
        }

        public void calculate() {
            int i = 0;
            while (true) {
                i ++;
                System.err.print(String.format("Thread-%s:%s", Thread.currentThread().getId(), i));
            }
        }
    }
}
