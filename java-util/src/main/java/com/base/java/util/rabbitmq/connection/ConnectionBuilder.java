package com.base.java.util.rabbitmq.connection;

import com.rabbitmq.client.ConnectionFactory;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.Executors;

public class ConnectionBuilder {
    public static ConnectionFactory build(MsgQueueConf conf) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(conf.getUsername());
        factory.setPassword(conf.getPassword());
        factory.setVirtualHost(conf.getVirtualHost());
        factory.setConnectionTimeout(conf.getTcpConnectionTimeout());
        factory.setRequestedHeartbeat(10);
        return factory;
    }

    public static ConnectionFactory build(MsgQueueConf conf, int workers) {
        ConnectionFactory factory = build(conf);
        factory.setSharedExecutor(Executors.newFixedThreadPool(workers > 0 ? workers : (Runtime.getRuntime().availableProcessors() * 2),
                new DefaultThreadFactory("message queue io thread group")));
        return factory;
    }
}
