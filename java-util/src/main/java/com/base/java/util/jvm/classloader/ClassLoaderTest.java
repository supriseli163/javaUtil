package com.base.java.util.jvm.classloader;

/**
 * BootStrap ClassLoader
 * Extension ClassLoader
 * APP ClassLoader
 * 关系:
 *  BootStrap ClassLoader由JVM启动,然后初始化sun.misc.Launcher
 *  sun.misc.Launcher初始化Extension ClassLoader, AppClassLoader
 *  BootStrap ClassLoader是Extension CLassLoader的parent,
 *  Extension ClassLoader是App ClassLoader的parent.
 *  但这不并不是继承关系,只是语义上的定义.
 *
 *  所以调用appClassLoader.getParent -> extensionClassLoader.getParent -> bootStrapClassLoader.getParent -> null
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.err.println("current loader--->" + loader);
        System.err.println("parent loader---->" + loader.getParent());
        System.err.println("grandParent loader-->" + loader.getParent().getParent());
    }
}


/**
 * 自定义classLoader
 * 自定义classLoader需要继承ClassLoader抽象类,重写findClass方法,这个方法定义了ClassLoader查找class的方法
 * 主要可以拓展的方法有:
 *  findClass 定义查找class的方式
 *  defineClass 将类文件字节码加载为jvm中的class
 *  findResource 定义查找资源的方式
 *
 *
 */

