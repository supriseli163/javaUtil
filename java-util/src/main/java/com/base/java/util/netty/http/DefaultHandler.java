package com.base.java.util.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DefaultHandler extends OneResponseHandler {
    @NonNull
    private final HttpResponseStatus defaultStatus;

    @Override
    public FullHttpResponse handle(HttpRequest httpRequest, Map<String, List<String>> params, ByteBuf byteBuf) {
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, defaultStatus);
        httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, 0);
        return httpResponse;
    }
}
