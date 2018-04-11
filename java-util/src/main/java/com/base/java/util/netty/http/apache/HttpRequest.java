package com.base.java.util.netty.http.apache;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.logging.Logger;

@Data
public class HttpRequest {
    private String host;
    private String path;
    private String method;

    private Map<String, Object> headers;
    private Map<String, Object> params;
    private Map<String, Object> entitys;
    private String entity;

    public HttpRequest() {
    }

    private HttpRequest(Builder builder) {
        if(builder.getHost() == null || builder.getHost().isEmpty()) {
            throw new NullPointerException("host is empty");
        }

        this.host = builder.getHost();
        this.path =  builder.getPath();
        if(StringUtils.isEmpty(builder.getMethod())) {
            this.method = builder.getMethod();
        } else {
            this.method = builder.getMethod();
        }
    }

    @Data
    public static class Builder {
        private String host;
        private String path;
        private String method;

        private Map<String, Object> headers = Maps.newHashMap();
        private Map<String, Object> params = Maps.newHashMap();
        private Map<String, Object> entitys = Maps.newHashMap();

        public Builder() {
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
