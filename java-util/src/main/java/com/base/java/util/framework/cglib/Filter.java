package com.base.java.util.framework.cglib;

public interface Filter {
    public default boolean enter() throws Exception {
        return true;
    };

    public default boolean exit() throws Exception {
        return true;
    }
}
