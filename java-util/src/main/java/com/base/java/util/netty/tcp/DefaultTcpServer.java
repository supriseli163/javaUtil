package com.base.java.util.netty.tcp;

import io.netty.channel.ChannelOption;

public class DefaultTcpServer extends TcpServer {
    public DefaultTcpServer(int workerThreads, IChInitializer initialzer) {
        super(new GroupConfig().setThreads(1).setOption(ChannelOption.SO_BACKLOG, 128),
                new GroupConfig().setThreads(workerThreads).setOption(ChannelOption.SO_KEEPALIVE, true)
                        .setOption(ChannelOption.TCP_NODELAY, true)
                        .setChannelHandler(new ChInitailzer(initialzer)));
    }

}
