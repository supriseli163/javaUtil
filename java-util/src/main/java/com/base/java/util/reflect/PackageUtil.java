package com.base.java.util.reflect;


import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public final class PackageUtil {
    private static final ClassPath classPath;

    static {
        try {
            classPath = ClassPath.from(ClassLoaderUtil.getLoader());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot initialize ClassPath");
        }
    }

    private PackageUtil() {
    }

    public static Set<Class<?>> getTypes(String packageName) {
        return classPath.getTopLevelClassesRecursive(packageName).stream().map(ci -> TypeUtil.getType(ci.getName())).collect(Collectors.toSet());
    }

    public static Set<Class<?>> getTypes(Collection<String> packageNames) {
        Set<Class<?>> types = Sets.newHashSet();
        packageNames.forEach(packageName -> types.addAll(getTypes(packageName)));
        return types;
    }

    public static Set<Class<?>> getInterfaces(String packageName) {
        return getTypes(packageName).stream().filter(type -> type.isInterface() && !type.isAnnotation()).collect(Collectors.toSet());
    }

    public static Set<Class<?>> getInterfaces(Collection<String> packageNames) {
        return getTypes(packageNames).stream().filter(type -> type.isInterface() && !type.isAnnotation()).collect(Collectors.toSet());
    }

    public static Set<Class<?>> getTypesAnnotatedWith(String packageName, Class<? extends Annotation> annotationType) {
        return filterByAnnotatedWith(getTypes(packageName), annotationType);
    }

    public static <T> Set<Class<? extends T>> getTypesExtendedFrom(String packageName, Class<T> superType) {
        return filterByExtendFrom(getTypes(packageName), superType);
    }

    public static Set<Class<?>> filterByAnnotatedWith(Set<Class<?>> types, Class<? extends Annotation> annotationType) {
        return types.stream()
                .filter(type -> type.isAnnotationPresent(annotationType))
                .collect(Collectors.toSet());
    }

    public static <T> Set<Class<? extends T>> filterByExtendFrom(Set<Class<?>> types, Class<?> superType) {
        return types.stream().filter(type -> (superType.isAssignableFrom(type) && superType != type)).map(type -> (Class<? extends T>) type).collect(Collectors.toSet());
    }
}
