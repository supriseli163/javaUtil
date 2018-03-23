package com.util.java.util.basic;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class EncodingTest {
    @Test
    public void testEncoding64() {
        long timeStamp = System.currentTimeMillis();
        String str64 = EncodingUtil.compressNumber(timeStamp);
        System.err.println(str64);
        long decodeTimeStamp = EncodingUtil.unCompressNumber(str64);
        assertEquals(timeStamp, decodeTimeStamp);
        //2049/9/26 19:46:20
        System.err.println(EncodingUtil.compressNumber(1885117580L));
    }

    @Test
    public void testGenerateUUId() {
        UUID uuid = UUID.randomUUID();
        System.err.println(uuid.version());
        //System.err.print(uuid.clockSequence());
        System.err.println(uuid.toString());
        System.err.println(new UUID(-5175412746561184071L,
                -6288008212960752420L).toString());
        System.err.println(TimeUtil.toLocalDateTime(EncodingUtil.randomUUID().timestamp()));
        System.err.println(TimeUtil.toLocalDateTime(EncodingUtil.randomUUID().clockSequence()));
        //System.err.println(UUID.randomUUID().variant());
        System.err.println(TimeBasedUUIDGenerator.generateId().timestamp() / 1000);
        System.err.println(TimeBasedUUIDGenerator.generateId().toString().length());
        System.err.println(TimeBasedUUIDGenerator.generateId().toString());
        System.err.println(TimeUtil.toLocalDateTime(TimeBasedUUIDGenerator.generateId().timestamp()));
    }

    @Test
    public void timeBasedUUID() {
        NoArgGenerator timeBasedGenerator = Generators.timeBasedGenerator();
        UUID firstUUID = timeBasedGenerator.generate();
        System.out.printf("1. First UUID is : %s ", firstUUID.toString().length());
        System.out.printf("\n2. Timestamp of UUID is : %d ", firstUUID.timestamp());
    }

    @Test
    public void testHex_64() {
        UUID uuid = TimeBasedUUIDGenerator.generateId();
        System.err.println(uuid.getMostSignificantBits() & 0xF000);
        System.err.println(Long.toBinaryString(2526193276L).length());
    }

    @Test
    public void verifyTimeStamp() {
        long expiredSeconds = 1000;
        String uuid = "Yzk2NDYzNWQtYmVjNi00Y2UyLWFmMjMjQxOD1dbfF5";
        System.err.println(UUIDUtil.isExpired(uuid, expiredSeconds));
    }
}
