package com.base.java.util.cglb;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;


public class CGLBTest {
    public <T> T newProxyInstance(Class<T> clazz) {
        return newProxyInstance(clazz, new MyInterceptor(), new MyFilter());
    }

    /**
     * 创建一个动态代理类
     *
     * @param superClass
     * @param methodCb
     * @param callbackFilter
     * @param <T>
     * @return
     */
    public static <T> T newProxyInstance(Class<T> superClass, Callback methodCb, CallbackFilter callbackFilter) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superClass);
        enhancer.setCallbacks(new Callback[]{methodCb, NoOp.INSTANCE});
        enhancer.setCallbackFilter(callbackFilter);

        return (T)enhancer.create();
    }

    class MyInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return null;
        }
    }

    class MyFilter implements CallbackFilter {

        @Override
        public int accept(Method method) {
            //Do some thing
            return 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CGLBTest cglbTest = new CGLBTest();
        int count = 1;
        while (true) {
            cglbTest.newProxyInstance(Object.class);
            Thread.sleep(100);
            System.out.println(count++);
        }
    }

}
