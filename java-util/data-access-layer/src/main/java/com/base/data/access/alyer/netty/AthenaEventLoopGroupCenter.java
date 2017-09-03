package com.base.data.access.alyer.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Objects;

public class AthenaEventLoopGroupCenter {
    /**连接debug页面的master和worker页面共用该EventLoopGroup*/
    /**
     *
     * https://segmentfault.com/a/1190000007403873
     * http://www.cnblogs.com/Anker/p/3265058.html
     *
     * Epoll EventLoopGroup和NioEventLoopGroup:
     *   if you are running on linux, you can use EpollEventLoopGroup and so get better performacne,
     * less GC and have more advanced features that are only available on linux.
     *   Netty provides the following platforms in specific JNI transports:
     *      Linux(since 4.0.16)
     *      MacOS/BSD(since 4.4.11)
     *
     * Reactor三种模型
     *
     *
     */
    private static final EventLoopGroup debugServerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(1) : new NioEventLoopGroup(1);

    /**所有连接客户端和服务端(包括心跳)的boss和worke共用该eventLoopGroup,在未初始化的是偶debug*/
    private static EventLoopGroup serverGroup = debugServerGroup;
    private static EventLoopGroup clientGroup = debugServerGroup;
    private static EventLoopGroup asyncGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(2) : new NioEventLoopGroup(2);

    /**
     * LocalEventLoopGroup
     * {@link MultithreadEventLoopGroup} which must be used for the local transport.
     */
    private static final EventLoopGroup localWorkGroup = new LocalEventLoopGroup(1);
    /**
     * http://blog.csdn.net/summerhust/article/details/18260117
     *
     *
     * http://www.cnblogs.com/Anker/p/3265058.html
     * EpollSocketChannel
     *  Epoll采用边缘触发(Edge Trigger和Level Trigger)
     * NioServerSocketChannel 分别接受新的连接
     */
    private static final Class<? extends Channel> CHANNEL_CLASS = Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class;

    private static final Class<? extends ServerChannel> SERVER_CHANNEL_CLASS = Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;

    public static EventLoopGroup getSeverWorkerGroup() {
        Objects.requireNonNull(serverGroup, "serverGroup is null, please make sure AthenaEventLoopGroupCenter.init() is called");
        return serverGroup;
    }

    public static EventLoopGroup getClientWorkerGroup() {
        Objects.requireNonNull(clientGroup, "clientGroup is null, please make sure AthenaEventLoopGroupCenter.init() is called");
        return clientGroup;
    }

    public static EventLoopGroup getAsyncWorkerGroup() {
        Objects.requireNonNull(asyncGroup, "asyncGroup is null, please make sure AthenaEventLoopGroupCenter.init() is called");
        return asyncGroup;
    }

    public static EventLoopGroup getLocalWorkerGroup() {
        return localWorkGroup;
    }

    public static EventLoopGroup getDebugServerGroup() {
        return debugServerGroup;
    }

    public static void init(int workerThreads) {
        serverGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(workerThreads) : new NioEventLoopGroup(workerThreads);
        clientGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(workerThreads) : new NioEventLoopGroup(workerThreads);
    }

    // 用于客户端的channel class
    public static Class<? extends Channel> getChannelClass() {
        return CHANNEL_CLASS;
    }

    public static ServerBootstrap newServerBootstrap(EventLoopGroup eventLoopGroup) {
        return new ServerBootstrap().group(eventLoopGroup).channel(SERVER_CHANNEL_CLASS);
    }
}
