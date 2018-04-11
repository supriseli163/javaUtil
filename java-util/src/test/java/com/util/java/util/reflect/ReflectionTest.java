package com.util.java.util.reflect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.Reflection;
import sun.reflect.MethodAccessor;


import java.lang.reflect.Method;
public class ReflectionTest {
    /**
     * 反射是指在运行时，调用Reflect Api来根据对象或类获得类的
     * 构造函数，修饰符，属性，函数，实例化或者调用该类的方法
     */
    public static void main(String arg) {
        Class<?> clazz = Reflection.getCallerClass();

    }

    /**
     * 反射获取Field, Method,
     * @param obj
     * @param methodName
     * @param paramTypes
     * @param params
     * @return
     * @throws Exception
     */
    public static Object invokePrivateMethod(Object obj, String methodName, Class<?>[] paramTypes,
                                             Object[] params) throws Exception {
        Object value = null;
        Class<?> cls = obj.getClass();
        /**注意不要使用getMethod, 因为getMethod()返回的都是public方法*/
        Method method = cls.getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);

        value = method.invoke(obj, params);
        return value;
    }
}
