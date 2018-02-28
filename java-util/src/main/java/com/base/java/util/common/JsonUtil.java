package com.base.java.util.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public final class JsonUtil {
    private JsonUtil() {}

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String toJsonString(T t) throws JsonProcessingException {
        return getInstance().writeValueAsString(t);
    }

    public static <T> T toJsonObject(String s, Class<T> clazz) throws JsonParseException, JsonMappingException,IOException {
        return getInstance().readValue(s, clazz);
    }

    public static <T> T toJsonObject(String jsonString, Class<?> collectionType, Class<?> ... elemeType) throws IOException{
        JavaType javaType = constructParametricType(collectionType, elemeType);
        return getInstance().readValue(jsonString, javaType);
    }

    private static JavaType constructParametricType(Class<?> collectionType, Class<?>... elementType) {
        //return getInstance().getTypeFactory().constructParametricType(collectionType, collectionType, elementType);
        return null;
    }
    public static ObjectMapper getInstance() {
        if(null == mapper) {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        }
        return mapper;
    }
}
