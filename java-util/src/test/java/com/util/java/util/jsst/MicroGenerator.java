package com.util.java.util.jsst;

import javassist.ClassPool;

public class MicroGenerator {
    /**
     * Test for java Virtual Machine & Out of Memory
     *
     * https://plumbr.io/outofmemoryerror/permgen-space
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100_000_000; i++) {
            generate("eu.plumbr.demo.main" + i);
        }
    }

    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }
}
