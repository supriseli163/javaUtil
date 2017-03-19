package com.base.java.util.jvm.gc;

public class ReferenceCountingGc {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSIze = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
