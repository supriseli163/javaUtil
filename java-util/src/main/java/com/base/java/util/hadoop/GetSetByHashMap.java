package com.base.java.util.hadoop;

import java.util.HashMap;
import java.util.Iterator;

public class GetSetByHashMap<K, E extends K> implements GSet<K, E> {
    public final HashMap<K, E> m;

    public GetSetByHashMap(int initialCapacity, float loadFactor) {
        m = new HashMap<K, E>(initialCapacity, loadFactor);
    }

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean contains(K key) {
        return m.containsKey(key);
    }

    @Override
    public E get(K key) {
        return m.get(key);
    }

    @Override
    public E put(E element) {
        if(element == null) {
            throw new UnsupportedOperationException("Null element is not supported");
        }
        return m.put(element,element);
    }

    @Override
    public void remove(K key) {
        m.remove(key);
    }

    @Override
    public void clear() {
        m.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return m.values().iterator();
    }
}
