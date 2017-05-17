package com.base.java.util.designpattern;

public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    /**
     * 饿汉式单例
     * 饿汉式单例是典型的时间换空间:
     *  当装载的时候救护创建类的实例,不管用不用,先创建出来,然后每次调用的时候,就不需要判断,节省了运行时间
     * @return
     */
    public static EagerSingleton getInstance() {
        return instance;
    }
}
