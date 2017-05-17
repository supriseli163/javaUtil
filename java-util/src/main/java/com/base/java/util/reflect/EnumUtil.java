package com.base.java.util.reflect;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Map;

public final class EnumUtil {
    private static final Map<Class<? extends Enum>, Map<String, Map<Object, Enum>>> enumRefMap = Maps.newHashMap();

    private EnumUtil() {
    }

    private static boolean hasRef(Class<? extends Enum> enumType, String fieldName) {
        return (enumRefMap.containsKey(enumType) && enumRefMap.get(enumType).containsKey(fieldName));
    }

    private static synchronized void indexEnumRef(Class<? extends Enum> enumType, String fieldName) {
        if (hasRef(enumType, fieldName)) {
            return;
        }

        if (!enumRefMap.containsKey(enumType)) {
            enumRefMap.put(enumType, Maps.newHashMap());
        }

        Map<Object, Enum> exactMap = enumRefMap.get(enumType).get(fieldName);
        if (exactMap == null) {
            try {
                Field field = enumType.getDeclaredField(fieldName);
                field.setAccessible(true);
                exactMap = Maps.newHashMap();
                for (Enum enumConstants : enumType.getEnumConstants()) {
                    Object fieldValue = field.get(enumConstants);
                    if (exactMap.containsKey(fieldValue)) {
                        throw new IllegalArgumentException(String.format("Duplicate value of field:[%s] on [%s]", fieldName, enumType));
                    }
                    exactMap.put(fieldValue, enumConstants);
                }
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                throw new IllegalArgumentException(String.format("Enum Type: [%s] has no field:[%s]", enumType, fieldName), ex);
            }
            enumRefMap.get(enumType).put(fieldName, exactMap);
        }
    }

    public static <T extends Enum> T getEnum(Class<? extends Enum> enumType, String fieldName, Object value) {
        if (!hasRef(enumType, fieldName)) {
            indexEnumRef(enumType, fieldName);
        }
        return (T) enumRefMap.get(enumType).get(fieldName).get(value);
    }
}
