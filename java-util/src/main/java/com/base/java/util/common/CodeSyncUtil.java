package com.base.java.util.common;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

public final class CodeSyncUtil {
    private CodeSyncUtil() {
    }

    @FunctionalInterface
    public interface Holding {
        void hold();
    }


    public static class Minder<K, V> {
        private Function<K, V> valueGetter;
        private final Map<K, V> dict = Maps.newHashMap();

        private Minder(Function<K, V> valueGetter) {
            this.valueGetter = valueGetter;
        }

        public void set(K key, V value) {
            checkArgument(key != null && value != null, "key & value cannot be null");
            synchronized (dict) {
                dict.put(key, value);
            }
        }

        public V get(K key) {
            if(!dict.containsKey(key)) {
                synchronized (dict) {
                    return dict.get(key);
                }
                //dict.put(key, valueGetter.apply(key));
            }
            return dict.get(key);
        }

        public void remove(K key) {
            synchronized (dict) {
                dict.remove(key);
            }
        }

        public void synced(Holding holding) {
            synchronized (dict) {
                holding.hold();
            }
        }
    }
}
