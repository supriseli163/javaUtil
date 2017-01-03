package com.base.java.util.json;

import com.base.java.util.reflect.InvokeUtil;
import com.base.java.util.reflect.PackageUtil;
import com.base.java.util.reflect.TypeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.reflect.TypeToken;

import java.io.IOException;

/**
 * this json util mainly user the fast jackson package
 */
public final class JsonUtil {
    private JsonUtil() {}

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        String currentPackagePath = JsonUtil.class.getPackage().getName();

        SimpleModule module = new SimpleModule();
        PackageUtil.getTypesExtendedFrom(currentPackagePath + ".serializer", JsonSerializer.class)
                .forEach(type -> module.addSerializer(TypeUtil.getFirsetGenericType(type), InvokeUtil.newInstance(type)));
        PackageUtil.getTypesExtendedFrom(currentPackagePath + ".deserializer", JsonDeserializer.class)
                .forEach(type -> module.addDeserializer(TypeUtil.getFirsetGenericType(type), InvokeUtil.newInstance(type)));
        mapper.registerModule(module);
    }

    private static <T> T read(String json, JavaType javaType) {
        try {
            return json != null ? mapper.readValue(json, javaType) : null;
        } catch (IOException ex) {
            return null;
        }
    }

    public static <T> T read(String json, TypeToken typeToken) {
        return read(json, mapper.constructType(typeToken.getType()));
    }

    public static <T> T read(String json, Class<T> type) {
        return read(json, mapper.constructType(type));
    }

    public static <T> T convert(Object fromJson, JavaType javaType) {
        try {
            return fromJson != null ? mapper.convertValue(fromJson, javaType) : null;
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static <T> T convert(Object fromJson, Class<T> type) {
        return convert(fromJson, mapper.constructType(type));
    }

    public static String writeAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException(String.format("Fail to convert Object [%s] to json string", object.getClass().getName()), ex);
        }
    }
}
