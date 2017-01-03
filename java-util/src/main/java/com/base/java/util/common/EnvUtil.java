package com.base.java.util.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class EnvUtil {
    private static final String DEFAULT_HOSTNAME = "default";
    private static final String DEFAULT_IPADDRESS = "127.0.0.1";

    public static String getHostName() {
        String hostName;
        try {
            hostName = String.valueOf(InetAddress.getLocalHost().getHostName().toLowerCase().replace(".", "_"));
        } catch (UnknownHostException e) {
            hostName = DEFAULT_HOSTNAME;
        }
        return StringUtil.isBlank(hostName) ? DEFAULT_HOSTNAME : hostName;
    }

    public static String getIpAddress() {
        String ipAddress;
        try {
            ipAddress = String.valueOf(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            ipAddress = DEFAULT_IPADDRESS;
        }
        return ipAddress;
    }
}
