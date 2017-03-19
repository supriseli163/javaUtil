package com.base.java.util.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class HttpServerUrlHandler extends HttpServerHandler {
    @NonNull
    private final HttpRequestHandler defaultHandler;
    private final Map<String, HttpRequestHandler> handlers = new HashMap<>();

    public HttpServerUrlHandler register(HttpMethod method, String uri, HttpRequestHandler handler) {
        String key = genKey(method, uri);
        if(handlers.put(key, handler) != null) {
            throw new IllegalArgumentException("handler already registered for " + key + "!");
        }
        return this;
    }

    private String genKey(HttpMethod method, String uri) {
        return method.name() + "@" + uri.toLowerCase();
    }

    @Override
    protected void handle(ChannelHandlerContext ctx, HttpRequest httpRequest, ByteBuf byteBuf) throws Exception {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(httpRequest.getUri());
        String key = genKey(httpRequest.getMethod(), queryStringDecoder.path());
        handlers.getOrDefault(key, defaultHandler).handler(ctx, httpRequest, queryStringDecoder.parameters(), byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
