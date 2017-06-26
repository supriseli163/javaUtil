package com.util.java.util.concurrent.thread;

public class MyThreadLocal {
    /**
     * ThreadLocal的名称比较容易让人误解,会认为其是一个"本地线程", 其实ThreadLocal并不是一个Thread,而是Thread的局部变量
     *
     * 其设计的初衷是为了解决多线程编程中的资源共享问题
     * 提起这个,大家一般会想到synchronized,
     *
     * synchorized采取的是"以时间换空间"的策略
     * 为每个使用该变量的线程提供独立的变量副本,在本线程内部,它相当于一个"全局变量", 可以保证本线程任何时间操作的都是同一个对象.
     *
     *在本线程内部,它相当于一个全局变量,可以保证
     *
     *
     *
     */
    public static final ThreadLocal<Context> userThreadLocal = new ThreadLocal<Context>();

    public static void set(Context user) {
        userThreadLocal.set(user);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static Context get() {
        return (Context)userThreadLocal.get();
    }
}
