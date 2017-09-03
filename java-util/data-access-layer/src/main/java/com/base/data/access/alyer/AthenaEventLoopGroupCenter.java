package com.base.data.access.alyer;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;

public class AthenaEventLoopGroupCenter {
    private static final EventLoopGroup debugServerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(1) :
}
