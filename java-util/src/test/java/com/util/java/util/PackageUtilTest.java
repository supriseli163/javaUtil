package com.util.java.util;

import com.base.java.util.reflect.PackageUtil;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class PackageUtilTest {

    @Test
    public void testFilterByExtendFrom() {
        //https://github.com/alibaba/fastjson/issues/1645
        String packageName = "com.base.java.util.json";
        Set setList = PackageUtil.getTypesExtendedFrom(packageName + ".serializer", JsonSerializer.class);
        assertNotNull(setList);
    }
}
