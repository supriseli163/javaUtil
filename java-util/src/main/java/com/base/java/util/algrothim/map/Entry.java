package com.base.java.util.algrothim.map;

public class Entry<K, V> {
    public final int hashCode;
    public final K key;
    public V value;

    public final Entry<K, V> next;

    public Entry(K key, V value,int hashCode, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hashCode = hashCode;
    }
}
