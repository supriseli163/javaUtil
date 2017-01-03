package com.util.java.util;

import com.base.java.util.config.Config;
import com.base.java.util.config.ConfigLoader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;


public class ConfigTest {

    private Config config;

    @BeforeTest
    public void beforeTest() {
        config = new ConfigLoader();
    }

    @Test
    public void testGetValue() {
        String env = config.getStringValue("env");
        assertEquals(env,"alpha");
        int port = config.getIntegerValue("port");
        assertEquals(port, 8080);
    }
}
