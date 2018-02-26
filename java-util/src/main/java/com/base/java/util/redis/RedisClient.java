package com.base.java.util.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class RedisClient implements IRedisClient {
    private static Log logger = LogFactory.getLog(RedisClient.class);

    @Override
    public int lock(String key, int timeout) {
        return 0;
    }

    @Override
    public void hm_del_key_and_set_string_batch(String key, Map<String, String> fieldsMap, int expireTime) {

    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public <T> String setex(String key, T obj, int expireTime) throws Exception{
//        if(null == obj || StringUtils.isBlank(key)) {
//            throw new Exception("SEARCH_KEY_NULL", "查询参数为空");
//        }
//        String ret = null;
//        String afterSearialize = null;
//        try {
//            afterSearialize =
//        }
        return null;
    }

    @Override
    public Long delete(String key) {
        return null;
    }
}
