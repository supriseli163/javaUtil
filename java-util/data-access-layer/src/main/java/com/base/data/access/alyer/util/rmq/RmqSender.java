package com.base.data.access.alyer.util.rmq;

import io.netty.channel.Channel;

import java.sql.Connection;

public class RmqSender {
    private final static String EXCHAGE_NAME_KEY = "exchange_name";
    private final static String EXCHANGE_TYPE_KEY = "exchange_type";
    private final static String ROUTING_KEY = "routing_key";

    String uri = "";
    String exchangeName = "dal_sql";
    String exchangeType = "fanout";
    String routingKey = "";

    private volatile Connection autoRecoveryConn = null;
    private volatile Channel autoRecoeryChannel = null;

    void initUriAndExchange() {
        int index = uri.lastIndexOf("?");
        if(index != -1 && uri.contains(EXCHAGE_NAME_KEY) && uri.contains())
    }
}
