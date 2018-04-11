package com.util.java.util;

import com.base.java.util.framework.EnvUtil;
import org.testng.annotations.Test;

public class EnvUtilTest {
    @Test
    public void testGetIpAddressAndHostName() {
        System.err.println(EnvUtil.getIpAddress());
        System.err.println(EnvUtil.getHostName());
    }
}
