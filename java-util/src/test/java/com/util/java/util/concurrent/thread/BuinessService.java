package com.util.java.util.concurrent.thread;

public class BuinessService {
    public void businessMethod() {
        Context context = MyThreadLocal.get();
        System.out.print(context.getTransactionId());
    }
}
