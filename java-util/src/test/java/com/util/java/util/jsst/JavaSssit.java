package com.util.java.util.jsst;

/**
 * Java动态性的两种常见的实现方式:
 * 1.字节码操作
 * 2.反射
 *
 *
 *
 * 运行时操作字节码可以让我们实现如下功能
 * --动态生成新的类
 * --动态改变某个类的结构(添加,删除,修改   新的属性/方法)
 *
 * 常见字节码操作类库
 * Byte Code Engineerining Libray(BCEL),这是Apache Software Foundation的jakarta项目的一部分,BCEL是Java classworking广泛使用的一种框架,他可以让您深入JVM汇编语言进行类哦的细节.
 * BCEL与Javassit有不同的处理字节码的方法,BCEL在实际的JVm指令层次上进行操作BCEL拥有广泛丰富的JVM指令级支持,而Javassit锁强调的是源代码级别的操作
 *
 * ASM
 *  是一个轻量级java自己码操作框架,直接涉及到JVM底层的操作和指令
 * CGLIB
 *  是一个强大的,高性能,高质量的code生成类库,基于ASM实现
 * javaSssit
 *  是一个开元的分析,编辑创建java字节码的类库,性能交ASM差,跟
 *
 *
 *http://chengjianxiaoxue.iteye.com/blog/2183767
 */
public class JavaSssit {

}
