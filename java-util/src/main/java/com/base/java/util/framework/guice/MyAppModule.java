package com.base.java.util.framework.guice;

import com.google.inject.AbstractModule;

/**
 *
 * bind关系的建立有两种方式:
 *  class MyAppModule extend AbstractModule {
 *      @Override
 *      protected void configure() {
 *          bind.(Interface.class).to(InterfaceImpl.class);
 *      }
 *  }
 *
 *  class implement Module {
 *      @Override
 *      protected void configure() {
 *
 *      }
 *  }
 *
 */
public  class MyAppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogService.class).to(LogServiceIpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(Application.class).to(MyApp.class);
    }
}