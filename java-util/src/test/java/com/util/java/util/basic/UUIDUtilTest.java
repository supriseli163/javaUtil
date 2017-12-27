package com.util.java.util.basic;

import com.base.java.util.common.UUIDUtil;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class UUIDUtilTest {
    @Test
    public void generateUUID() {
        String uuId = UUIDUtil.generate();
        System.err.println(uuId.length());
        boolean verifyResult = UUIDUtil.verify(uuId);
        assertTrue(verifyResult);
    }
}
