package com.base.java.util.concurrent.task;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public interface Task extends Callable<Object> {
    Method getMathod();

    default long getTimeoutInMills() {
        return 0;
    }

    CallStatus getStatus();

    void setstatus(CallStatus status);

    boolean supportFallback();

    Object callFallback() throws Throwable;

    void cancle();

}
