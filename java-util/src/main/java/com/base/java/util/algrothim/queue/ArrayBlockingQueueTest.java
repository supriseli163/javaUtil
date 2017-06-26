package com.base.java.util.algrothim.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBlockingQueueTest {
    private static AtomicInteger count = new AtomicInteger(0);

    static class Producer implements Runnable {
        @Override
        public void run() {
            produce();
        }

        private final ArrayBlockingQueue<Bread> queue;

        public Producer(ArrayBlockingQueue queue) {
            /**
             * put()方法是如果容器满了的话,就会把当前线程挂起
             * offer()方法是容器如果满了的话就会返回false
             */
            this.queue = queue;
        }

        void produce() {
            try {
                Bread bread = new Bread();
                queue.put(bread);
                System.err.println("Produce:" + bread);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    static class  Consumer implements Runnable {
        private final ArrayBlockingQueue<Bread> queue;

        public Consumer(ArrayBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                cunsume();
            }
        }

        public void cunsume() {
            /**
             * take()方法和put(0方法是对应的,从中拿出一个数据,如果拿不到则线程挂起
             * poll()方法和offer()方法是对应的,从中拿一个数据,如果没有则直接返回null.
             */
            try {
                Bread bread = queue.take();
                System.out.println("consumer:" + bread);
            } catch (InterruptedException ex) {
                ex.printStackTrace();;
            }
        }

    }

    @AllArgsConstructor
    @Data
    static class Bread {
        private int number;

        public Bread() {
            number ++;
        }
    }

    public static void main(String[] args) {
        int capacity = 10;
        ArrayBlockingQueue<Bread> queue = new ArrayBlockingQueue<Bread>(capacity);

        Producer producer1 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Producer producer2 = new Producer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();

    }
}
