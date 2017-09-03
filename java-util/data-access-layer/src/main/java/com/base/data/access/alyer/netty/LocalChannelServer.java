package com.base.data.access.alyer.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.Objects;
import java.util.logging.Logger;

public class LocalChannelServer {
    private static final Logger logger = Logger.getLogger(LocalChannelServer.class.getName());
    private static NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private Channel serverChannel = null;
    private static LocalChannelServer INSTANCE = new LocalChannelServer();

    private LocalChannelServer() {}

    public static LocalChannelServer getInstacnce() {
        return INSTANCE;
    }

    public void start() throws InterruptedException {
        if(Objects.isNull() && ) {

        }

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new LocalEventLoopGroup());
        serverBootstrap.channel(LocalChannelServer.class);
    }

    public void shutdown() {
        if(Objects.isNull(serverChannel)) {
            serverChannel.close().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    logger.info("local server has been closed");
                }
            });
            serverChannel = null;
        }
        eventLoopGroup.shutdownGracefully();
    }

    public boolean isOpen() {
        return serverChannel != null;
    }
}
