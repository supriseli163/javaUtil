package com.base.java.util.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;

/**
 * Aggregator
 * 1,使聚集
 * 2,总计
 */
public class HttpServerAggregator extends ChannelInboundHandlerAdapter {
    private HttpRequest httpRequest;
    private ByteBuf byteBuf;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest) {
            httpRequest = (HttpRequest) msg;
            byteBuf = Unpooled.buffer();
        }

        if(msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf buf = httpContent.content();
            byteBuf.writeBytes(buf);
            buf.release();

            if(httpContent instanceof LastHttpContent) {
                try {
                    ctx.fireChannelRead(new Object[]{ httpRequest, byteBuf } );
                } finally {
                    httpRequest = null;
                    byteBuf = null;
                }
            }
        }
    }
}
