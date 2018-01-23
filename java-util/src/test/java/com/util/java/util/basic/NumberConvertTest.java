package com.util.java.util.basic;

import com.base.java.util.common.HexConvert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class NumberConvertTest {
    @Test
    public void testHexConvert() {
        long number = 1000L;
        String numberStr = HexConvert._10_to_N(number, 62);
        assertEquals(number, HexConvert.N_to_10(numberStr, 62));
    }
}
