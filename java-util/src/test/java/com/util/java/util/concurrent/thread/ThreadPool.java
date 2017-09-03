package com.util.java.util.concurrent.thread;

public interface ThreadPool<Job extends Runnable> {
    /**执行一个Job,这个Job需要实现Runnbale*/
    default void execute(Job job) {};

    /**关闭线程池*/
    default void shutdown() {};

    /**增加该作者线程*/
    default void addWorkers(int num) {};

    /**减少该作者线程*/
    default void removeWorkers(int num) {};

    /**得到正在等待执行的任务数量*/
    default int getJobSize() {
        return 0;
    };
}
