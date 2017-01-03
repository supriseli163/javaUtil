package com.base.java.util.reflect;

import com.google.common.base.MoreObjects;

import java.net.URL;

public final class ClassLoaderUtil {
    private static ClassLoader loader = MoreObjects.firstNonNull(Thread.currentThread().getContextClassLoader(),
            ClassLoaderUtil.class.getClassLoader());

    private ClassLoaderUtil() {
    }

    public static ClassLoader getLoader() {
        return loader;
    }

    public static URL getResourceUrl(String resoucePath) {
        return loader.getResource(resoucePath);
    }
}
