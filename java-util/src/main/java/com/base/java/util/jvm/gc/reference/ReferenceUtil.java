package com.base.java.util.jvm.gc.reference;

import org.junit.Test;

public class ReferenceUtil {
    /**
     *
     * jdk 1.2之后,引入了强引用,软引用,弱引用,虚引用
     *
     * 在java.lang.ref包里
     *
     *
     *
     * 1,强引用(Strong Reference)
     *  强引用不会被GC回收,并且在java.lang.ref里也没有引用
     *  实际对应的类型
     *  Object obj = new Object(); obj不会被GC回收
     *
     *
     * 2,软引用(Soft Reference)
     *  软引用在jvm报告内存不足时才会被GC回收,否则不会回收,
     *  正是这种特性,caching和pooling中用处广泛
     *
     *
     */

    @Test
    public void softReference() {

    }

    /**
     *
     * 弱引用: WeakReference:
     *  当GC一旦发现了弱引用对象,将会释放WeakReference,
     *  弱引用使用方法与软引用类似,但回收策略不同
     *
     *  虚引用(Phantom Reference)
     *      当GC一旦发现了虚引用对象,将会被PhantomReference回收
     *  插入ReferenceQueue队列,
     */
}
