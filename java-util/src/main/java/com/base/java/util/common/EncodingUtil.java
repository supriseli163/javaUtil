package com.base.java.util.common;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import com.sun.deploy.net.URLEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class EncodingUtil {
    private EncodingUtil() {}

    public static String encodeBase64(String string) {
        return BaseEncoding.base64().encode(string.getBytes());
    }

    public static String decodeBase64(String string) {
        return new String(BaseEncoding.base64().decode(string));
    }

    public static String encodeUrlCOmponent(String string) {
        try {
            return URLEncoder.encode(string, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static String decodeUrlCOmponent(String string) {
        try {
            return URLDecoder.decode(string, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
