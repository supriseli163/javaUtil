package com.base.java.util.cglb;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AroundAdvice implements MethodInterceptor{
    /**
     * CGLIb
     *
     *
     * Spring AOP:框架对APO代理类的处理原则是:
     *  如果目标对象的实现类实现了接口,Spring AOP将会采用jdk动态代理来生成AOP动态代理来生成AOP代理类;
     *  如果目标对象的实现类没有实现接口,Spring AOP将会将会采用CGLIB来生成AOP代理类
     *  不过这个选择过程对开发者完全透明,开发者也无需关心
     *
     *
     *
     * Code Generation Library, 简单来说,就是一个代码生成类库,他可以在运行时动态生成某个类的子类
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("执行目标方法之前,模拟开始事物.......");
        //执行目标方法,并保存目标方法执行后的返回值
        Object rvt = proxy.invokeSuper(obj, new String[]{"被改变"});
        System.out.println("执行目标方法之后,模拟事物结束.....");
        return rvt + "新增的内容";
    }
}
