package com.base.java.util.netty.tutorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {
    private ByteBuf content;
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        content = ctx.alloc().directBuffer(4).writeZero(4);
        System.err.println("send message:" + ctx.toString());
        generateTraffic();
        Channel channel = ctx.channel();
        System.err.print(channel.eventLoop().parent());
        System.err.print(channel.config());
        System.err.print(ctx.name());
    }

    long counter;
    private void generateTraffic() {
        ctx.writeAndFlush(content.duplicate().retain()).addListener(traccifGenerator);
    }

    private final ChannelFutureListener traccifGenerator = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if(future.isSuccess()) {
                generateTraffic();
            } else {
                future.cause().printStackTrace();
                future.channel().close();
            }
        }
    };

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
