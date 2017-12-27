package com.base.java.util.concurrent.task;

public enum  CallStatus {
    //非业务异常
    crit,
    //业务异常
    user_exec,
    //超时
    timeout,
    //熔断
    sick,
    //软超时
    soft_timeout,
    //降级
    off,
    //授权认证失败
    no_authority,
    //没有可用线程
    no_available_thread,

    ;
}
