package com.base.java.util.netty;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

public class ConsoleCHannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final int MAX_BODY_MEGABYTES = 1024 * 1024; //1MB


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        //Base Http
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(MAX_BODY_MEGABYTES));

        //Heartbeta
        pipeline.addLast(new HeartbeatHandler());
    }

    private static class HeartbeatHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext context, FullHttpRequest request) throws Exception {
            HttpResponseStatus status;
            switch (request.getUri()) {
                case "/heartbeat":
                    status = HttpResponseStatus.OK;
                    break;
                default:
                    status = HttpResponseStatus.NOT_FOUND;
            }

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
            ChannelFuture future = context.writeAndFlush(response);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
