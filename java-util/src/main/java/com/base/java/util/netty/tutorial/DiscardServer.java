package com.base.java.util.netty.tutorial;

import com.base.java.util.netty.http.HttpServerAggregator;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Discard any incoming data
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        /**
         * Netty NIO Event-driven model
         *
         * {@link NioEventLoopGroup} is a multithreaded event loop handles I/O operation.
         * Netty provides various EventLoop implementations for different kind of transports.
         * We are implementing a server-side application in this example,
         * and therefore two {@link NioEventLoopGroup} will be used.
         * The first one is boss group, accepts an incoming connection.
         * The second one is worker group, connection to the server.
         *      How manny Threads are used and how they mapped to the created {@link io.netty.channel.Channel} depends on
         * the {@link NioEventLoopGroup} implementation and may be even configurable via a constructor.
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    /**
                     * Use {@link NioServerSocketChannel} class which is used to instance a new {@link Channel} to accept
                     * incoming connection.
                     */
                    .channel(NioServerSocketChannel.class)
                    /**
                     * The handler specified here will always be evaluted by a new accepted {@link io.netty.channel.Channel}.
                     * The {@link ChannelInitializer} is a special handler that is purposed to help a use configure a new {@link io.netty.channel.Channel}.
                     * It is most likely that you want configure the {@link io.netty.channel.ChannelPipeline} of the new {@link io.netty.channel.Channel}.
                     * by adding some handlers such as {@link DiscardServerHandler} to implementation you network application.
                     * As the application gets complicated, it is likely that you will add more handlers to the pipeline and extract this
                     * anonymous class into a top level class eventually.
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpServerAggregator());
                        }
                    })
            .option(ChannelOption.SO_BACKLOG, 128)
                    /**
                     * option and childOption
                     * option is for the {@link NioServerSocketChannel} that accepts incoming connections,
                     * childOption is for the {@link io.netty.channel.Channel} accepted by parent {@link io.netty.channel.ServerChannel}
                     */
            .childOption(ChannelOption.SO_KEEPALIVE, true);

            //Bind and start to accept income connections
            ChannelFuture future = bootstrap.bind(port).sync();

            //Wait until the server socket
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}
