package com.base.java.util.netty.tutorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel
 *
 * Discard protocol:
 *  抛弃协议
 *  作用就是接收到什么就抛弃什么,他对调试网络状态有一定的用处
 *  如果服务器实现了抛弃协议,服务器就会在TCP端口检测到抛弃协议,在建立连接后被检测到请求后
 *  就直接把接收到的数据直接抛弃,知道用户中断连接.
 *  而基于UDP协议的抛弃服务和基于TCP差不多
 *
 *  {@link ChannelInboundHandlerAdapter}是 {@link io.netty.channel.ChannelInboundHandler}的实现,
 *  {@link io.netty.channel.ChannelInboundHandler} 提供了多个事件handler method.
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 监听输入事件,并对其做出响应
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in =  (ByteBuf)msg;


        /***
         * Echo protocol
         *
         *
         */

        try {
            /**
             * The inefficient loop can actually be simplified to :
             * System.out.println(in.toString(io.netty.util.CharsetUtils.US_ASCII))
             */
            while (in.isReadable()) {
                System.out.println((char)in.readByte() + msg.toString()

                );
                Thread.sleep(100);
            }
        } finally {
            //release
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //Close the connection when an exception is raised
        cause.printStackTrace();
        ctx.close();
    }
}
