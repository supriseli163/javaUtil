package com.util.java.util.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.base.java.util.json.JsonUtil;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class JsonUtilTest {


    @Test
    public void testEnum() {
        TestDateClass testDateClass = new TestDateClass();
        testDateClass.setName("testDateClass");
        testDateClass.setTestDateTime(LocalDateTime.now());

        String test = "{\"name\":\"test\",\"testDateTime\":\"2016-05-11 17:32:20.897\"}";


        System.err.println(JSON.toJSONString(JSONObject.parseObject(test, TestDateClass.class), SerializerFeature.PrettyFormat));
        System.err.println(JSON.toJSONString(JSON.parseObject(test).toJavaObject(TestDateClass.class), SerializerFeature.PrettyFormat));

        System.err.print(JsonUtil.read(test, TestDateClass.class).toString());
    }
}
