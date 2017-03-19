package com.base.java.util.framework.guice;

import com.google.inject.servlet.ServletModule;

public class MyServletConfig extends ServletModule {
    @Override
    public void configureServlets() {
//        serve("/*").with(MainServlet.class);
//        serve("/*").through();
    }
}
