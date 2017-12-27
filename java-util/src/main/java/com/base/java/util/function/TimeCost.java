package com.base.java.util.function;

import java.util.concurrent.ExecutionException;

public class TimeCost<T> {
    public T execute(Execution<? extends T> execution, Callback callback) throws ExecutionException {
        long begin = System.currentTimeMillis();
        try {
            return execution.execute();
        } catch (Exception ex) {
            throw new ExecutionException(ex);
        }finally {
            long end = System.currentTimeMillis();
            callback.handleTimeCost(end - begin);
        }
    }

    @FunctionalInterface
    public static interface Execution<T> {
        T execute() throws Exception;
    }

    @FunctionalInterface
    public static interface Callback {
        void handleTimeCost(long timeCost);
    }
}
