package com.base.java.util.common;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public final class HashUtil {
    private HashUtil() {}

    public static String md5(String content) {
        return Hashing.md5().hashString(content, Charsets.UTF_8).toString();
    }

    public static String sha1(String content) {
        return Hashing.sha1().hashString(content, Charsets.UTF_8).toString();
    }
}
