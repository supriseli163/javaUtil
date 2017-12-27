package com.base.java.util.concurrent;

import java.util.Map;

public class TreeNode{



    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        volatile V value;
        volatile Node<K,V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }


        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            throw  new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (hash != node.hash) return false;
            if (key != null ? !key.equals(node.key) : node.key != null) return false;
            if (value != null ? !value.equals(node.value) : node.value != null) return false;
            return next != null ? next.equals(node.next) : node.next == null;

        }
    }
}
