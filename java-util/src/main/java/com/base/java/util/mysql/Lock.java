package com.base.java.util.mysql;

import java.util.concurrent.TimeUnit;

public interface Lock {
    void lock();

    void tryLock();

    void tryLock(TimeUnit timeUnit, long time) throws InterruptedException;
}
