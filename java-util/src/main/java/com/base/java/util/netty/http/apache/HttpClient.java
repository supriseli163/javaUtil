package com.base.java.util.netty.http.apache;



import org.apache.commons.collections.CollectionUtils;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.logging.Logger;

public class HttpClient {
    private static final Logger logger = Logger.getLogger(HttpClient.class.getName());

    private static final Integer CONNECT_TIMEOUT = 3000;
    private static final Integer READ_TIMEOUT = 1000;

    private static final String HEADERA_ACCEPT_CHARSET = "Accept-Charset";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DELETE = "DELETE";

    public static HttpResponse execute(HttpRequest request) {
        String url = getUrl(request);
        return null;
    }

    public static String getUrl(HttpRequest request) {
        StringBuilder builder = new StringBuilder(256);
        if(request.getHost() != null && request.getHost().startsWith("http://")) {
            builder.append(request.getHost());
        } else {
            builder.append("http://").append(request.getHost());
        }
        builder.append(request.getParams());
        if(request.getParams() != null && request.getParams().isEmpty()) {
            boolean first = true;
            for(Map.Entry<String, Object> item : request.getParams().entrySet()) {
                if(first) {
                    builder.append("?").append(item.getKey()).append("=").append(String.valueOf(item.getValue()));
                    first = false;
                } else {
                    builder.append("&").append(item.getKey()).append("=").append(String.valueOf(item.getValue()));
                }
            }
        }
        return builder.toString();
    }

    public static void setRequestProperty(HttpURLConnection connection, final HttpRequest request) {
        Map<String, Object> headers = request.getHeaders();
        if(headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, Object> item : headers.entrySet()) {
                connection.setRequestProperty(item.getKey(), item.getValue().toString());
            }
        }
    }

    private static String map2Str(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> item : map.entrySet()) {
            sb.append("&");
            sb.append(item.getKey());
            sb.append("=");
            sb.append(String.valueOf(item.getValue()));
        }

        if(sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
