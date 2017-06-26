package com.base.java.util.hadoop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A {@link GSet} is a set.
 * which support the {@link #(Object)}
 *
 * @param <K>
 * @param <E>
 */
public interface GSet<K, E extends K> extends Iterable<E> {
    static final Log LOG = LogFactory.getLog(GSet.class);

    /**
     * return the size of this set
     *
     * @return
     */
    int size();

    boolean contains(K key);

    E get(K key);

    E put(E element);

    void remove(K key);

    void clear();

}
