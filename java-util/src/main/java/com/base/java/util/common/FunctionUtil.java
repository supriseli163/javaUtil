package com.base.java.util.common;

import java.util.function.Function;
import java.util.function.Supplier;

public final class FunctionUtil {
    private FunctionUtil() {}

    public static <V> V get(V value, Function<V, V> valueFunction){
        return value == null ? null : valueFunction.apply(value);
    }

    public static <V> V get(V value, V defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static <V> V get(V value, Function<V, V> valueFunction, V defaultValue) {
        return value == null ? defaultValue : valueFunction.apply(value);
    }

    public static <V> V get(V value, Supplier<V> defaultValueSupplier) {
        return value == null ? defaultValueSupplier.get() : value;
    }

    public static <V> V get(V value, Function<V, V> valueFUnction, Supplier<V> defaultValueSupplier) {
        return value == null ? defaultValueSupplier.get() : valueFUnction.apply(value);
    }
}
