package com.base.java.util.designpattern;

public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {}

    /**
     * 懒汉式单例实现里对静态工厂方法使用了同步化,以处理多线程的环境.
     * 懒汉式单其实就是一种比较形象的称谓,既然懒,那么在创建对象实例的时候就不会着急,会一直等到马上要使用对象实例的时候才会创建.
     * 因此在装载对象的时候不会创建对象实例
     * @return
     */
    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
