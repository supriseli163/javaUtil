package com.util.java.util.jsst;

import org.junit.Test;

public class OOM {
    static final int SIZE=2*1024*1024;

    @Test
    public void testMain(String[] a) {
        int[] i = new int[SIZE];
    }
}
