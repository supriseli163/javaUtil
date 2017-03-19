package com.base.java.util.framework.cglib;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.err.println("request");
    }
}
