package com.base.java.util.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.epoll.Epoll;
import io.netty.handler.codec.http.HttpMethod;

public class ServerBootstrapFactory {
    public static ServerBootstrap newServerBootStrap(int boosThreads, int workerThreads, boolean epollFirst) {
        if(epollFirst && Epoll.isAvailable()) {
            return
        } else {
            return
        }
    }

    public static ServerBootstrap enwNioServerBootstrap(int bossThreads, int workerThreads) {
        NoiEventLoopG
    }

    public static ServerBootstrap newEpollServerbootStrap(int ) {

    }

    private String getKey(HttpMethod method, String uri) {
        return method.name() + "@" + uri.toLowerCase();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();;
        ctx.close();
    }
}


