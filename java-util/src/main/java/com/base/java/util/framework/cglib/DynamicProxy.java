package com.base.java.util.framework.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    /**
     * Java 代理模式:
     *  定义: 给某个对象提供一个代理对象
     *
     *http://www.jianshu.com/p/6f6bb2f0ece9
     *
     *  代理的实现为:
     *      静态代理:代理类是在编译时就实现好的,也就是说java编译完成后代理类是一个实际的class文件
     *
     *
     *  动态代理
     *     代理类是在运行时生成的,也就是说Java编译完成之后并没有实际的class文件,而是在运行时动态生成的类字节码,并加载在jvm中
     *
     *
     *  java动态代理实现:
     *      委托类和委托对象:
     *          委托类是一个类,委托对象是委托类的实例
     *      代理类和代理对象:
     *          代理类是一个类,代理对象是代理类的实例
     *
     *
     *   java实现动态代理的步骤大概如下:
     *      1,定义一个委托类和公共接口
     *      2,自己定义一个类(调用处理器类,即实现InvocationHandler接口), 这个类的目的是指定运行时将生成的代理类需要完成的具体任务
     *          包括Preprocess和PostProcess,即代理类调用任何方法都会经过这个调用处理器类
     *      3,生成代理对象:
     *          当然也会生成代理类,需要指定1,委托对象
     *                                  2,实现一系列的接口
     *                                  3,调用处理器类的实例,因此可以看出一个代理对象对应一个委托对象,对应一个调用处理器实例
     *
     *
     *   java实现动态代理主要涉及以下几个类:
     *      java.lang.reflect.proxy 这个是生成代理类的主类,通过proxy类生成的代理类都集成
     *
     */


    /**
     * 真实的委托对象
     */
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }



    /**
     *
     * @param proxy     指代我们所代理的真实对象
     * @param method    指代的是我们所要调用的真实对象的某个方法的method对象
     * @param args      指代的是调用真实对象某个方法时接收的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         *在代理真实对象前我们可以添加一些自己的操作
         */
        System.err.println("before rent house");

        System.err.println("Method:" + method);


        //代理对象调用真实的对象的方法时,其会自动跳转到代理对象关联的handler对象的invoke方法来调用
        method.invoke(proxy, args);

        System.err.println("after rent house");
        return null;
    }
}
