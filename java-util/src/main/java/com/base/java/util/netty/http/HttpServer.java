package com.base.java.util.netty.http;

import com.base.java.util.netty.tcp.DefaultTcpServer;
import com.base.java.util.netty.tcp.IChInitializer;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServer extends DefaultTcpServer {
    public HttpServer(int workerThreads, final IChInitializer initializer) {
        super(workerThreads, new IChInitializer() {
            @Override
            public void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new HttpServerCodec());
                channel.pipeline().addLast(new HttpServerAggregator());
                initializer.initChannel(channel);
            }
        });
    }
}
