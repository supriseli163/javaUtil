package com.base.java.util.common;

import com.google.common.collect.Sets;
import java.util.List;
public final class ListUtil {
    private ListUtil() {}

    public static <E> boolean hasDuplicated(List<E> list) {
        return Sets.newHashSet(list).size() != list.size();
    }
}
