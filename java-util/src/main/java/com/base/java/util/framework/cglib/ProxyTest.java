package com.base.java.util.framework.cglib;

import org.junit.Test;

import java.lang.reflect.*;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProxyTest {
    @Test
    public void testProxy() {
        //我们要代理的真实对象
        Subject realSubject = new RealSubject();

        //我们要代理那个对象,就把这个对象传递进去,最后是通过该真实对象来调用该方法的
        InvocationHandler handler = new DynamicProxy(realSubject);

        /**
         * 通过proxy的newStance方法创建的代理对象是在jvm运行是动态生成的一个对象,
         * 它并不是我们的InvocationHandler类型的,也不是我们定义的那组接口类型,而是在运行时动态生成的一个对象
         */

        /**
         * {@link java.lang.reflect.Proxy} provides static method for creating dynamic proxy classes, and instances
         * and it's also the superclass of all dynamic proxy classes created by these methods
         */
        Subject subject = (Subject) java.lang.reflect.Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

        Class<?> proxyClass = java.lang.reflect.Proxy.getProxyClass(Subject.class.getClassLoader(), Subject.class);

        assertTrue(Proxy.isProxyClass(proxyClass));

        System.err.println(proxyClass.getConstructors());

        InvocationHandler invocationHandler = Proxy.getInvocationHandler(subject);
        assertEquals(invocationHandler, handler);

//        System.err.println(handler.getClass().getClassLoader());
//        System.err.println(subject.getClass().getName());
//        System.err.println(subject.getClass());

        //subject.request();
    }
}
