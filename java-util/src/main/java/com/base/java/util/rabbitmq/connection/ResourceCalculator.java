package com.base.java.util.rabbitmq.connection;

import io.netty.util.internal.SystemPropertyUtil;

public class ResourceCalculator {
    private static int default_connections = Runtime.getRuntime().availableProcessors() * 2;

    public int connections() {
        return Math.max(1, SystemPropertyUtil.getInt("soa.rabbitmq.connections", default_connections));
    }
}
