package com.base.java.util.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerBootstrapFactory {
    public static ServerBootstrap newServerBootStrap(int boosThreads, int workerThreads, boolean epollFirst) {
        if (epollFirst && Epoll.isAvailable()) {
            return newEpollServerBootStrap(boosThreads, workerThreads);
        } else {
            return newNioServerBootstrap(boosThreads, workerThreads);
        }
    }

    public static ServerBootstrap newNioServerBootstrap(int bossThreads, int workerThreads) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(bossThreads);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(workerThreads);
        return new ServerBootstrap().group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
    }

    public static ServerBootstrap newEpollServerBootStrap(int bossThreads, int workerThreads) {
        EpollEventLoopGroup bossGroup = new EpollEventLoopGroup(bossThreads);
        EpollEventLoopGroup workerGroup = new EpollEventLoopGroup(workerThreads);
        return new ServerBootstrap().group(bossGroup, workerGroup).channel(EpollServerSocketChannel.class);
    }
}


