package com.base.data.access.alyer.util.common;

public class NaturalOrderComparator implements Comparable<String> {

    int compareRight(String a, String b) {
        int bias = 0;
        int ia = 0;
        int ib = 0;

        for(;ia++; ib++) {
            char ca = charAt(a, ia);
            char cb =
        }
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    static char charAt(String s, int i) {
        if(i >= s.length()) {
            return 0;
        } else {
            return s.charAt(i);
        }
    }
}
