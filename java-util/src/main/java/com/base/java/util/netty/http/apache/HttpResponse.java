package com.base.java.util.netty.http.apache;

import lombok.Data;

import java.util.Map;

@Data
public class HttpResponse {
    private int statusCode = 0;
    private Map<String, String> headers;
    private String entity;

    public String getHeader(String key) {
        if(key == null || key.isEmpty()) {
            return null;
        }
        return headers.get(key);
    }
}
