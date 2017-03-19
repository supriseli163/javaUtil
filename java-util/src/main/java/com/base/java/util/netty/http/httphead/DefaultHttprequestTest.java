package com.base.java.util.netty.http.httphead;

import io.netty.handler.codec.http.*;
import org.junit.Test;

public class DefaultHttprequestTest {
    @Test
    public void testHeaderRemoval() {
        /**
         * HttMethod:
         *  Http GET Method means you can retrieve whatever information in the form of an entity.
         *  根据HTTP规范: GET 请求用户获取信息,而且是安全幂等的
         *      can be cached
         *
         *  根据HTTP规范: POST表示可能修改变服务器上的资源的请求
         *      can not be cached
         */
        HttpMessage m = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
        HttpHeaders h  = m.headers();

    }

}
