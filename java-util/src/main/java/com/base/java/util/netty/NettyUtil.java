package com.base.java.util.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public final class NettyUtil {
    private static final int CONSOLE_MASTER_GROUP_SIZE = 1;
    private static final int CONSOLE_WORKER_GROUP_SIZE = 1;

    private NettyUtil() {
    }

    public static ServerBootstrap createBootstrap(int masterGroupSize, int workerGroupSize) {
        EventLoopGroup masterGroup, workerGroup;
        Class<? extends ServerSocketChannel> socketChanelType;
        if (Epoll.isAvailable()) {
            masterGroup = new EpollEventLoopGroup(masterGroupSize);
            workerGroup = new EpollEventLoopGroup(workerGroupSize);
            socketChanelType = EpollServerSocketChannel.class;
        } else {
            masterGroup = new NioEventLoopGroup(masterGroupSize);
            workerGroup = new NioEventLoopGroup(workerGroupSize);
            socketChanelType = NioServerSocketChannel.class;
        }

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(masterGroup, workerGroup);
        bootstrap.channel(socketChanelType);
        return bootstrap;
    }

    public static ServerBootstrap createConsoleBootStrap() {
        return createBootstrap(CONSOLE_MASTER_GROUP_SIZE, CONSOLE_WORKER_GROUP_SIZE);
    }
}
