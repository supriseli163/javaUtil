package com.base.java.util.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final int MAX_BODY_MEGABYTES = 1024 * 1034 * 100;    //100MB
    private static final int CORS_MAX_SECONDS = 60 * 60 * 24 * 2;   //2 days
    private static final int IDLE_TIMEOUT_SECONDS = 60; //1 miniute

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        //Base http
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(MAX_BODY_MEGABYTES));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new CorsHandler(CorsConfig.withAnyOrigin().maxAge(CORS_MAX_SECONDS).build()));

        //Idle breaker
        pipeline.addLast(new IdleStateHandler(0, 0, IDLE_TIMEOUT_SECONDS, TimeUnit.SECONDS));

        //Invocation Handler
    }
}
