package com.util.java.util.basic;

import org.testng.annotations.Test;

public class NCpuTest {
    @Test
    public void testCpuCount() {
        System.err.println(Runtime.getRuntime().availableProcessors());
        long maxMemory = 1908932608;
        System.err.println(maxMemory / (1024 * 1024));
    }
}
