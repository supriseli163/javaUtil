package com.base.java.util.framework;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class EnvUtil {
    private static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            throw new IllegalStateException("get host info failed.");
        }
    }

    private static String getIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            throw new IllegalStateException("get host info failed.");
        }
    }
}
