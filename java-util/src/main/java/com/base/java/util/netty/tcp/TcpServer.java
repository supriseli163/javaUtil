package com.base.java.util.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;

import java.util.Map;

public class TcpServer {
    private final ServerBootstrap bootstrap;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;

    public TcpServer(GroupConfig boss, GroupConfig worker) {
        this(boss, worker, true);
    }

    public TcpServer(GroupConfig boss, GroupConfig worker, boolean epollFirst) {
        bootstrap = ServerBootstrapFactory.newServerBootStrap(boss.getThreads(), worker.getThreads(), epollFirst);
        bossGroup = bootstrap.group();
        workerGroup = bootstrap.childGroup();
        bootBoss(boss);
        bootWorker(worker);
    }

    private void bootBoss(GroupConfig boss) {
        bootChannelHandler(boss, bootstrap::handler);
        bootOption(boss, bootstrap::option);
    }

    private void bootWorker(GroupConfig worker) {
        bootChannelHandler(worker, bootstrap::childHandler);
        bootOption(worker, bootstrap::childOption);
    }

    private void bootOption(GroupConfig group, OptionOp op) {
        for (Map.Entry<ChannelOption<?>, Object> entry : group.getOptions().entrySet()) {
            bootOption(entry.getKey(), entry.getValue(), op);
        }
    }

    private void bootChannelHandler(GroupConfig group, ChannelHandlerOp op) {
        ChannelHandler channelHandler = group.getChannelHandler();
        if (channelHandler != null) {
            op.execute(channelHandler);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void bootOption(ChannelOption<T> option, Object value, OptionOp op) {
        op.execute(option, (T) value);
    }

    public void start(int port) throws Exception {
        start(() -> {
            return bootstrap.bind(port);
        });
    }

    public void start(BindOp op) throws Exception {
        op.execute().sync();
    }

    @FunctionalInterface
    private interface ChannelHandlerOp {
        void execute(ChannelHandler channelHandler);
    }

    @FunctionalInterface
    private interface OptionOp {
        <T> void execute(ChannelOption<T> option, T value);
    }

    @FunctionalInterface
    private interface BindOp {
        ChannelFuture execute();
    }
}
