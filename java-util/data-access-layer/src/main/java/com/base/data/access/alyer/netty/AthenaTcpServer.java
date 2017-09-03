package com.base.data.access.alyer.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AthenaTcpServer {
    private static final Logger logger = Logger.getLogger(AthenaTcpServer.class.getName());

    private final Bootstrap bootstrap;
    private Map<String, Channel> bossChanel = new HashMap<>();
    private AtomicInteger listenedPortsCount = new AtomicInteger(0);

    public AthenaTcpServer(IChi) {

    }

    public void closedBossChannel() throws InterruptedException {
        for(Map.Entry<String, Channel> entry : bossChanel.entrySet()) {
            entry.getValue().close().sync();

        }
    }

    private void bootBoss(G ) {

    }

    public int getLastenedPortCount() {

    }

    @FunctionalInterface
    private interface BindOp {
        ChannelFuture execute();
    }

    @FunctionalInterface
    private interface OptionOp {
        <T> void execute(ChannelOption<T> option, T value);
    }

    @FunctionalInterface
    private interface ChannelHanlderOp {
        void execute(ChannelHandler channelHandler);
    }
}

