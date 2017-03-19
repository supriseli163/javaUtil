package com.util.java.util.netty;

import com.base.java.util.netty.http.DefaultHandler;
import com.base.java.util.netty.http.HttpServer;
import com.base.java.util.netty.http.HttpServerUrlHandler;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

public class SampleServer {
    public static void  main(String[] args) throws Exception{
        ChannelHandler urlHandler = new HttpServerUrlHandler(new DefaultHandler(HttpResponseStatus.BAD_REQUEST)).register(HttpMethod.POST, "/",
                new SampleHandler());

        new HttpServer(100, ch -> ch.pipeline().addLast(urlHandler)).start(9999);
    }
}
