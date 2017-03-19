package com.base.java.util.common;

import com.google.common.collect.Sets;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public final class MemorySizeUtil {
    private MemorySizeUtil() {}

    public static int estimateSize(Object... objects) {
        return (new Estimator(objects)).estimate() - BYTES_EMPTY_ARRAY;
    }

    /**
     * Inner implementation
     */
    private static final Map<Class<?>, Integer> primitiveBytesMap =  new HashMap<Class<?>, Integer>(){{
        put(boolean.class, 1);
        put(byte.class, 1);
        put(char.class, 2);
        put(short.class, 2);
        put(int.class, 4);
        put(float.class, 4);
        put(double.class, 8);
        put(long.class, 8);
    }};

    private static final int BYTES_POIINTER = 4;
    private static final int BYTES_CLASS = 8;
    private static final int BYTES_EMPTY_ARRAY = 16;

    private static class Estimator {
        private Object object;
        private Set<Object> visited = Sets.newHashSet();
        private Stack<Object> stack = new Stack<>();

        public Estimator(Object object) {
            this.object = object;
        }

        public int estimate() {
            int result = estimateTop(this.object);
            while (!stack.isEmpty()) {
                result += estimateTop(stack.pop());
            }
            visited.clear();
            return result;
        }


        private int estimateTop(Object object) {
            //this will not cause a memory leak since unused interned Strings will be thrown away
            if(visited.contains(object) || (object instanceof String && object == ((String)object))) {
                return 0;
            }

            visited.add(object);
            Class clazz = object.getClass();
            if(clazz.isArray()) {
                return estimateArray(object);
            }

            int result = 0;
            do {
                for(Field field : clazz.getDeclaredFields()) {
                    if (!Modifier.isStatic(field.getModifiers())) {
                        if (field.getType().isPrimitive()) {
                            result += primitiveBytesMap.get(field.getType());
                        }
                    } else {
                        result += BYTES_POIINTER;
                        field.setAccessible(true);
                        try {
                            stack.add(field.get(object));
                        } catch (IllegalAccessException ex) {
                            //Do nothing
                        }
                    }
                }

                clazz = clazz.getSuperclass();
            } while (clazz != null);

            result += BYTES_CLASS;
            System.gc();
            return (int)Math.ceil(result / 8.0) * 8;
        }

        private int estimateArray(Object object) {
            int result = BYTES_EMPTY_ARRAY;
            int length = Array.getLength(object);

            if(length != 0) {
                Class elementClazz = object.getClass().getComponentType();
                if(elementClazz.isPrimitive()) {
                    result += length * primitiveBytesMap.get(elementClazz);
                } else {
                    for(int index = 0; index < length; index++) {
                        result += BYTES_POIINTER + estimateTop(Array.get(object, index));
                    }
                }
            }
            return result;
        }
    }
}
