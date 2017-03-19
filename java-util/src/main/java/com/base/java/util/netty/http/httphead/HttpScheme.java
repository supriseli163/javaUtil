package com.base.java.util.netty.http.httphead;

/**
 * a common schemes used for the HTTP protocol as defined by rfc7230
 */
public final class HttpScheme {

    private static final HttpScheme HTTP = new HttpScheme(80, "http");
    private static final HttpScheme HTTPS = new HttpScheme(443, "https");

    private final int port;
    private final String name;

    public HttpScheme(int port , String name) {
        this.port = port;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public int port() {
        return port;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof HttpScheme)) {
            return false;
        }

        HttpScheme other = (HttpScheme) obj;
        return other.port == port && other.name().equals(name);
    }

    @Override
    public int hashCode() {
        return port * 31 + name.hashCode();
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
