package com.base.java.util.rabbitmq;

import lombok.Data;

@Data
public class MQConfig {
    private String url;
    private String queue;
    private String user;
    private String password;
    private String virtualHost;
    private String exchange;
    private String routingKey;
    private int count;
    private boolean needBind;
    private boolean autoAck;
    private int prefetchCount;
    private int channelCount;
}
