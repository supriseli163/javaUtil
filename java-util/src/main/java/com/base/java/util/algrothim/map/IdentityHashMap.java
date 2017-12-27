package com.base.java.util.algrothim.map;

public class IdentityHashMap<K, V> {
    private final Entry<K, V>[] buckets;
    private final int indexMask;
    private final static int DEFAULT_SIZE = 8192;

    public IdentityHashMap() {
        this(DEFAULT_SIZE);
    }

    public IdentityHashMap(int tableSize) {
        this.indexMask = tableSize - 1;
        this.buckets = new Entry[tableSize];
    }

    public final V get(K key) {
        final int hash = System.identityHashCode(key);
        final int bucket = hash & indexMask;

        for(Entry<K, V> entry= buckets[bucket]; entry != null; entry = entry.next) {
            if(key.equals(entry.key)) {
                return (V)entry.value;
            }
        }
        return null;
    }

    public boolean put(K key, V value) {
        final int hashCode = System.identityHashCode(key);
        final int bucket = hashCode & indexMask;

        for(Entry<K, V> entry = buckets[bucket]; entry != null; entry = entry.next) {
            if(key == entry.key) {
                entry.value = value;
                return true;
            }
        }

        Entry<K, V> entry = new Entry<>(key, value, hashCode, buckets[bucket]);
        buckets[bucket] = entry;
        return true;
    }

    public Class findClass(String keyString) {
        for(int i = 0; i < buckets.length; i ++) {
            Entry bucket = buckets[i];
            if(bucket == null) {
                continue;
            }

            for(Entry<K, V> entry = bucket; entry != null; entry = entry.next) {
                Object key = bucket.key;
                if(key instanceof Class) {
                    Class clazz = (Class)key;
                    String clazzname = clazz.getName();
                    if(clazzname.equals(keyString)) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
