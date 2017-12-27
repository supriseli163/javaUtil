package com.util.java.util.concurrent;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;

public class TrafficShaper {
    public static class RateLimitException extends Exception {
        private static final long serialVersionUID = 1L;
        private String resource;

        public String getResource() {
            return resource;
        }

        public RateLimitException(String resoucre) {
            this.resource = resoucre;
        }

        public synchronized  Throwable fillInStackTrace() {
            return this;
        }

        private static final Map<String, RateLimiter> resourceLimiterMap = Maps.newConcurrentMap();

        public static void updateResourceQps(String resource, double qps) {
            RateLimiter rateLimiter = resourceLimiterMap.get(resource);
        }
    }

}
