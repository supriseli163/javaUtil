package com.base.java.util.framework.cglib;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.err.println("Process!");
        subject.request();
        System.err.println("PostProcess!");
    }
}
