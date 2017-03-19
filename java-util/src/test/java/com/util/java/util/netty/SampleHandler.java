package com.util.java.util.netty;

import com.base.java.util.netty.http.OneResponseHandler;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;

import java.util.List;
import java.util.Map;

public class SampleHandler extends OneResponseHandler {
    @Override
    protected FullHttpResponse handle(HttpRequest httpRequest, Map<String, List<String>> params, ByteBuf byteBuf) throws Exception {
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, httpResponse.content().readableBytes());
        return httpResponse;
    }
}
