package com.base.java.util.designpattern;

public class Singleton {
    /**
     * volatile关键字可能会屏蔽掉虚拟机中一些必要的代码优化,
     * 所以运行效率并不是很高
     * 因此一般建议,没有特别的需要,不要使用,也就是说,虽然可以使用"双重检查加锁"机制来实现线程安全的机制,但并不建议大量使用,可以根据具体情况来选用
     *
     *
     *
     * 类级内部类:
     *  类级内部类指的是,有static修饰的成员式内部类,如果没有static修饰的成员式内部类被称为对象级内部类
     *
     *  类级内部类相当于外部类的static成分,他的对象与外部类对象间不存在依赖关系,因此可以直接创建.
     *
     */
    private volatile static Singleton instance = null;
    private Singleton() {}

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
