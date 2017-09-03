package com.base.data.access.alyer.util;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class JobScheduler extends Thread {
    private static final Logger logger = Logger.getLogger(JobScheduler.class.getName());
    private int runningRounds = 0;
    private final String birthday = new SimpleFormatter().format(new Date());

    private ConcurrentLinkedDeque<JobScheduler>
}
