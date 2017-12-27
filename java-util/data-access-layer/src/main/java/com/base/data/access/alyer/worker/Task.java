package com.base.data.access.alyer.worker;

import java.util.logging.Logger;

public abstract class Task implements Runnable, Comparable<Task> {
    private static final Logger logger =  Logger.getLogger(Task.class.getName());

}
