package com.util.java.util;

import com.base.java.util.netty.NettyUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;

public class NettyTest {
    private static final int MASTER_GROUP_SIZE = 1;
    private static final int WORKER_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int SERVER_PORT = 8080;

    public void start() {
        ServerBootstrap bootstrap = NettyUtil.createBootstrap(MASTER_GROUP_SIZE, WORKER_GROUP_SIZE);

        new Thread(() -> {
           try {
               Channel channel = bootstrap.bind();
           }
        });
    }
}
