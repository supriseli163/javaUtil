package com.util.java.util.basic;

public class VolatileTest {
    public volatile static int count = 0;

    public static void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count ++;
    }

    public static void main(String[] args) {
        for(int i =0; i < 1000; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    VolatileTest.inc();
                    System.out.println(count);
                }
            }).start();
        }

        System.out.println("运行结果:Counter.count" + VolatileTest.count);
    }
}
