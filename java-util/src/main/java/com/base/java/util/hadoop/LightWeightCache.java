package com.base.java.util.hadoop;

import java.util.Iterator;

/**
 * A low memory footprint {@link GSet} implementation,
 * which uses an array for sorting the elements and linked
 * lists for collision resolution.
 *
 * No rehash will be performed.
 * Therefore, the internal array will never be resized.
 *
 * This class does support null element.
 *
 * This class is not thread safe.
 *
 * @param <K> Key type for looking up the elements.
 * @param <E> Element Type, which must be
 *            (1) a subclass of K, and
 *            (2) implement {}
 */
public class LightWeightCache<K, E extends K> implements GSet<K, E> {


    public interface LinkedElement {
        /**
         * Set the next element
         *
         * @param linkedElement
         */
        public void setNext(LinkedElement linkedElement);

        /**
         * Get the next element
         *
         * @return
         */
        public LinkedElement getNext();
    }

    //static final int

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public E get(K key) {
        return null;
    }

    @Override
    public E put(E element) {
        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
