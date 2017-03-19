package com.base.java.util.framework.guice;

public class LogServiceIpl implements LogService {

    @Override
    public void log(String msg) {
        System.err.println("-------Log:" + msg);
    }
}