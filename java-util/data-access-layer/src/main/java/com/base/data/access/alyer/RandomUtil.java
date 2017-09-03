package com.base.data.access.alyer;

public class RandomUtil {
    private static final byte[] bytes = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't',
            'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm',
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X',
            'C', 'V', 'B', 'N', 'M' };
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1l << 33) -1;
    private static final long integerMask = (1L << 33) -1;
    private static final long seedUniguifier = 8682522807148012L;

    private static long seed;
    static {
        long s = seedUniguifier + System.nanoTime();
        s = (s ^ multiplier) & mask;
    }

    public static final byte[] randomBytes(int size) {
        byte[] bb = bytes;
        byte[] ab = new byte[];
        for(int i = 0; i < size; i++) {
            ab[i] = randomByte(bb);
        }
        return ab;
    }

    private static byte randomByte(byte[] b) {
        int ran = (int) ((next() & integerMask) >>> 16);
        return b[ran % b.length];
    }

    private static long next() {
        long oldSeed = seed;
        long nextSeed = 0L;
        do {
            nextSeed = (oldSeed * multiplier + addend) & mask;
        } while (oldSeed == nextSeed);
        seed = nextSeed;
        return seed;
    }
}
