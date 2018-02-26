package com.base.java.util.redis;

import java.util.Map;

public interface IRedisClient {
    int lock(String key, int timeout);

    void hm_del_key_and_set_string_batch(String key, Map<String, String> fieldsMap, int expireTime);

    String get(String key);

    <T> String setex(String key, T obj, int expireTime) throws Exception;

    Long delete(String key);
}
