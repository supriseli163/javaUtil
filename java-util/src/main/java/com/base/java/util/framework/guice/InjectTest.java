package com.base.java.util.framework.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.BeforeClass;
import org.junit.Test;

public class InjectTest {
    /**
     * Google Guice 三种注入方式:
     *  Constructor inject
     *  Method inject
     *  Field inject
     */

    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new MyAppModule());
    }

    @Test
    public void testMyApp() {
        Application myApp = injector.getInstance(Application.class);
        myApp.work();
    }

    /**
     *
     *
     * Guice Servlet集成:
     *  要在Servlet项目中集成Guice,首先需要安装Guice Servlet扩展:
     *  compile group: 'com.google.inject.extensions', name: 'guice-servlet', version: '4.1.0'
     */
}
