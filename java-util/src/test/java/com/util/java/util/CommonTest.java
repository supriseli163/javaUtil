package com.util.java.util;

import com.base.java.util.common.EnvUtil;
import com.base.java.util.config.Config;
import com.base.java.util.config.ConfigLoader;
import com.base.java.util.json.JsonUtil;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class CommonTest {
    InnerClassA innerClassA = new InnerClassA();

    @Data
    public static class InnerClassA {
        private String a;
        private List<Integer> b;
        private Map<String, Integer> c;
    }

    @Data
    public static class InnerClassB {
        InnerClassA classA;
        private Object object;
    }

    public void beforTest() {

    }

    @Test
    public void testFromObjectToJson() {
        InnerClassB innerClassB = new InnerClassB();

        innerClassB.setClassA(innerClassA);
        innerClassB.setObject(new Object());

        String json = JsonUtil.writeAsString(innerClassA);
        System.err.println(json);
        String expectedJson = "{\"a\":\"a\",\"b\":[1,2,3,4],\"c\":{\"a\":4}}";
        assertEquals(json,expectedJson);
    }

    @Test
    public void testFromReadConfig() {
        final String CONF_PATH = "CONF_PATH";
        Config config = new ConfigLoader();
        System.setProperty(CONF_PATH, "config");
        config.getStringValue("env");
    }

    @Test
    public void testHostName() {
        System.err.println(EnvUtil.getHostName());
        System.err.println(EnvUtil.getIpAddress());
    }
}
