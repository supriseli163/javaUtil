package com.base.java.util.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import java.util.List;
import java.util.Map;

public interface HttpRequestHandler {
    void handler(ChannelHandlerContext ctx, HttpRequest httpRequest, Map<String, List<String>> params, ByteBuf byteBuf) throws Exception;

    default String getParam(Map<String, List<String>> params, String key) {
        List<String> paramList = params.get(key);
        if(paramList == null || paramList.isEmpty()) {
            return null;
        } else {
            return paramList.get(0);
        }
    }
}
