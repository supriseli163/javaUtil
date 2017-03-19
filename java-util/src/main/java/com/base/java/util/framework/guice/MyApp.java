package com.base.java.util.framework.guice;

import com.google.inject.Inject;

public class MyApp implements Application {
    private UserService userService;
    private LogService logService;

    @Inject
    public MyApp(UserService userService, LogService logService) {
        this.logService = logService;
        this.userService = userService;
    }

    @Override
    public void work() {
        userService.process();
        logService.log("程序正常运行!!!");
    }
}
