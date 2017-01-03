package com.base.java.util.proxy;

import com.base.java.util.reflect.InvokeUtil;
import com.google.common.collect.ObjectArrays;
import com.google.common.reflect.Invokable;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import static com.google.common.base.Preconditions.checkArgument;

public final class ProxyUtil {
    private ProxyUtil() {}

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> superClass, MethodInterceptor interceptor) {
        return create(superClass, new Class[0], interceptor);
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> superClass, Class<?>[] interfaces, MethodInterceptor interceptor) {
        checkArgument(superClass != null, "Must provide superclass.");

        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(interceptor);

        if(superClass.isInterface()) {
            enhancer.setInterfaces(ObjectArrays.concat(superClass, interfaces));
        } else {
            Invokable constructor = InvokeUtil.getConstructor(superClass);
            if(constructor != null) {
                constructor.setAccessible(true);
            }
            enhancer.setSuperclass(superClass);
            enhancer.setInterfaces(interfaces);
        }
        return (T)enhancer.create();
    }

    public static boolean isProxy(Class<?> type) {
        return type.getName().contains("EnhancerBy");
    }

    public static Class<?> getRealType(Class<?> type) {
        while (isProxy(type)) {
            type = type.getSuperclass();
        }
        return type;
    }
}
