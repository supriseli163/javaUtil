package com.util.java.util.concurrent.thread;

import java.util.Random;

public class ThreadLocalDemo extends Thread {

    public static void main(String[] args) {
        Thread threadOne = new ThreadLocalDemo();
        threadOne.start();
        System.err.println(threadOne.getId());

        Thread threadTwo = new ThreadLocalDemo();
        threadTwo.start();
        System.err.println(threadTwo.getId());
    }

    @Override
    public void run() {
        Context context = new Context();
        Random random = new Random();

        int age = random.nextInt(100);
        context.setTransactionId(String.valueOf(age));

        System.out.println("set thread [" +getName()+ "] contextid to " + String.valueOf(age));

        MyThreadLocal.set(context);

        try {
            Thread.sleep(100);
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        new BuinessService().businessMethod();
        MyThreadLocal.unset();
    }
}
