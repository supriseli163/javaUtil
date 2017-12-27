package com.base.java.util.concurrent.task;

public interface TaskExecutor {
    Object execute(Task task) throws Throwable;
}
