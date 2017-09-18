package com.base.java.util.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br>http://www.cnblogs.com/dongguacai/p/5597706.html</>
 * <a herf></a>
 */
public class HashMapTest {

    public static class HashMapConcurrentMap extends Thread {
        static Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
        static AtomicInteger at = new AtomicInteger();

        public void run() {
            while (at.get() < 10000000) {
                map.put(at.get(), at.get());
                at.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        HashMapConcurrentMap t1 = new HashMapConcurrentMap();
        HashMapConcurrentMap t2 = new HashMapConcurrentMap();
        HashMapConcurrentMap t3 = new HashMapConcurrentMap();
        HashMapConcurrentMap t4 = new HashMapConcurrentMap();
        HashMapConcurrentMap t5 = new HashMapConcurrentMap();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
