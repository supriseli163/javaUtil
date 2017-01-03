package com.util.java.util;

import com.base.java.util.netty.HttpChannelInitializer;
import com.base.java.util.netty.NettyUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import org.junit.Test;

public class NettyTest {
    private static final int MASTER_GROUP_SIZE = 1;
    private static final int WORKER_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int SERVER_PORT = 8080;

    public void start() {
        ServerBootstrap bootstrap = NettyUtil.createBootstrap(MASTER_GROUP_SIZE, WORKER_GROUP_SIZE)
                .childHandler(new HttpChannelInitializer())
                .option(ChannelOption.SO_KEEPALIVE, true);

        new Thread(() -> {
           try {
               Channel channel = bootstrap.bind(SERVER_PORT).sync().channel();
               System.err.println(String.format("Http server start with port [%s].", SERVER_PORT));
               //channel.closeFuture().sync();
           } catch (InterruptedException ex) {
               throw new IllegalStateException("Http server port start failed.", ex);
           }
        }, String.format("http-server-%s", SERVER_PORT)).start();
    }

    @Test
    public void nettyRun() {
        start();
    }
}
