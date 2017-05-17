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


        //动态类加载与重载
        /**
         * 类加载器
         * 所有java应用中的类都是被java.lang.classloader类的一系列子类加载的
         * 因此想要动态加载类的话也必须使用java.lang.classLoader的子类
         */


        /**
         * 类加载顺序:
         *  1,检查这个类是否已加载
         *  2,如果没有被加载,则首先调用父类加载器
         *  3,如果父类加载器不能加载这个类,则尝试加载这个类
         */
        ClassLoader  classLoader = ClassLoaderTest.class.getClassLoader();
        try {
            Class aClass = classLoader.loadClass("java.sql.Array");
            System.out.println("aClass.getName() =" + aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

