package com.base.java.util.reflect;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public final class MethodUtil {
    private static final Map<Method, Method> overriddenMap = Maps.newHashMap();
    private static Set<String> objectMethodNames = Sets.newHashSet();

    static {
        Sets.newHashSet(Object.class.getMethods()).forEach(method -> objectMethodNames.add(method.getName()));
    }

    private MethodUtil() {
    }

    private static synchronized void initializeOverridenMethod(Method method) {
        if (overriddenMap.containsKey(method)) {
            return;
        }

        Method overridden = null;

        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?>[] exceptionTypes = method.getExceptionTypes();

        Class<?> declaredClass = method.getDeclaringClass();
        Set<Class<?>> types = Sets.newHashSet(declaredClass.getInterfaces());
        if (declaredClass.getSuperclass() != null) {
            types.add(declaredClass.getSuperclass());
        }

        do {
            Set<Class<?>> newTypes = Sets.newHashSet();
            for (Class<?> type : types) {
                for (Method fMethod : type.getDeclaredMethods()) {
                    if (!fMethod.getName().equals(name)) {
                        continue;
                    }

                    if (!fMethod.getReturnType().isAssignableFrom(returnType)) {
                        continue;
                    }

                    Class<?>[] fParameterTypes = fMethod.getParameterTypes();
                    if (parameterTypes.length != fParameterTypes.length) {
                        continue;
                    }

                    // parameter types
                    boolean isParameterTypesAssignable = true;
                    for (int index = 0; index < parameterTypes.length; index++) {
                        if (!fParameterTypes[index].isAssignableFrom(parameterTypes[index])) {
                            isParameterTypesAssignable = false;
                            break;
                        }
                    }
                    if (!isParameterTypesAssignable) {
                        continue;
                    }

                    //exceptionTypes
                    boolean isExceptionTypesAssginable = true;
                    Set<Class<?>> fExceptionTypes = Sets.newHashSet(fMethod.getExceptionTypes());
                    for (Class<?> exceptionType : exceptionTypes) {
                        if (!fExceptionTypes.stream().anyMatch(et -> et.isAssignableFrom(exceptionType))) {
                            isExceptionTypesAssginable = false;
                            break;
                        }
                    }

                    if (!isExceptionTypesAssginable) {
                        continue;
                    }

                    //Founded
                    overridden = fMethod;
                    name = fMethod.getName();
                    returnType = fMethod.getReturnType();
                    exceptionTypes = fMethod.getExceptionTypes();
                }
                newTypes.addAll(Sets.newHashSet(type.getInterfaces()));
                if (type.getSuperclass() != null) {
                    newTypes.add(type.getSuperclass());
                }
            }
            types = newTypes;
        } while (!types.isEmpty());
        overriddenMap.put(method, overridden);
    }

    public static boolean isObjectMethod(Method method) {
        return objectMethodNames.contains(method.getName());
    }

    public static Method getOverridenMethod(Method method) {
        if (!overriddenMap.containsKey(method)) {
            initializeOverridenMethod(method);
        }
        return overriddenMap.get(method);
    }
}
