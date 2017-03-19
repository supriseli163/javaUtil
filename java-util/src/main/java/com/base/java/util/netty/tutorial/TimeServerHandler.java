package com.base.java.util.netty.tutorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * The protocol to implement in this section is the time protocol.
 * It is different from the previous examples in that it sends a message,
 * which contains a 32-bit integer, without receiving any request and close connection once
 * the message is sent.
 * In this example, you will learn how to construct and send a message,
 * and close the connection on completion.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * channelActive method will be called as soon as a connection is established,
     * channelRead method will be called as soon as received any data
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**
         * alloc a 4 bytes buf size = int has 2 ^ 32.
         *
         * To send a new message, we need to allocate a new buffer which will contain the message.
         * We are going write a 32-bit message, and therefore we need a {@link ByteBuf} whose capacity is
         * at least 4 bytes. Get the current {@link io.netty.buffer.ByteBufAllocator} via {@link ChannelHandlerContext.alloc()}
         * and allocate a new buffer.
         *
         */
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert f == future;
                ctx.close();
            }
        });
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
