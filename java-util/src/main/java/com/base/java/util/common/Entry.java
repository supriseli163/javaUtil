package com.base.java.util.common;

public class Entry<K, V> {
    private final int hashCode;
    private final K key;
    private V value;

    public final Entry<K, V> next;

    public Entry(int hashCode, K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hashCode = hashCode;
    }
}
