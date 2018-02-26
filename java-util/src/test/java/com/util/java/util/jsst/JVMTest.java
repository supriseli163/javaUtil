package com.util.java.util.jsst;

import org.junit.Test;

import java.lang.management.ManagementFactory;

public class JVMTest {
    @Test
    public void testJVMBean() {
        String jvmBean = ManagementFactory.getRuntimeMXBean().getName();
        String pid = jvmBean.split("@")[0];
        System.err.println(jvmBean);
        System.err.println(pid);
    }
}
