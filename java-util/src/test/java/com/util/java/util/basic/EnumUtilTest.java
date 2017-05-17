package com.util.java.util.basic;

import com.base.java.util.reflect.EnumUtil;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class EnumUtilTest {
    @Test
    public void testEnumUtil() {
        Enum valueEnum = EnumUtil.getEnum(NameEnum.class, "value", 1);
        assertEquals(valueEnum, NameEnum.XIAO_MING);
        Enum descriptionEnum = EnumUtil.getEnum(NameEnum.class, "description", "ming.xiao");
        assertEquals(descriptionEnum, NameEnum.XIAO_MING);
    }
}
