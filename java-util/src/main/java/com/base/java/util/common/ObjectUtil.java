package com.base.java.util.common;

public final class ObjectUtil {
    private ObjectUtil() {}

    @SafeVarargs
    public static <T> T firstNonNull(T... objects) {
        for(T object : objects) {
            if(object != null) {
                return object;
            }
        }
        throw new NullPointerException();
    }
}
