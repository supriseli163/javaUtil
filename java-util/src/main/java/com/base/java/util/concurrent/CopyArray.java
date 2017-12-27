package com.base.java.util.concurrent;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class CopyArray {
    /**
     * CopyOnWriteArrayList 读的时候不需要加锁,如果读的时候有多个线程正在向CopyOnWriteArrayList添加数据,读还是会读到旧的数据,因为你写的时候
     * 不会锁住旧的CopyOnWriteArrayList.
     *
     *
     * CopyOnWriteArrayList具有以下特性
     *  1.它最适合有以下特征的应用程序,List大小通常保持很小,只读操作远多于可变操作,需要在遍历期间防止线程间的冲突
     *  2.支持高效率并发且是线程安全的
     *  3.因为通常需要复制整个基础数组,所以可变操作(add(), set(),和remove()等等)的开销很大
     *  4.迭代器支持hasNext(),next()等不可变操作,但不支持可变remove()等操作
     *  5.使用迭代器进行遍历的速度很快,并且不会和其他线程发送冲突,在构造迭代器时,迭代器依赖于不变的数组快照
     *
     */
    private static List arrayList = new CopyOnWriteArrayList<>();

    public static class CopyOnWriteMap<K,V> implements Map<K,V>, Cloneable {
        private volatile Map<K, V> internalMap;

        public CopyOnWriteMap() {
            internalMap = new HashMap<K, V>();
        }

        public V put(K key, V value) {
            synchronized (this) {
                Map<K, V> newMap = new HashMap<K, V>(internalMap);
                V val = newMap.put(key, value);
                internalMap = newMap;
                return val;
            }
        }

        @Override
        public V remove(Object key) {
            return null;
        }

        @Override
        public int size() {
            return internalMap.size();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        public V get(Object key) {
            return internalMap.get(key);
        }

        public void putAll(Map<? extends K, ? extends V> newData) {
            synchronized (this) {
                Map<K, V> newMap = new HashMap<K, V>(internalMap);
                newMap.putAll(newData);
                internalMap = newMap;
            }
        }

        @Override
        public void clear() {

        }

        @Override
        public Set<K> keySet() {
            return null;
        }

        @Override
        public Collection<V> values() {
            return null;
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return null;
        }
    }
 }
