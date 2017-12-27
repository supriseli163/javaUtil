package com.base.java.util.rabbitmq.connection;
import lombok.Data;

import java.util.List;
@Data
public class MsgQueueConf {
    private static final int hand_shake_timeout = 100;
    private static final int tcp_connection_timeout = 100;

    private String username;
    private String password;
    private String virtualHost;
    private String exchangeName;
    private String routingKey;
    private List<String> urls;

    private String queueName;
    private boolean mandatory;
    private int handshakeTimeout = hand_shake_timeout;
    private int tcpConnectionTimeout = tcp_connection_timeout;

    public String msgInfoForProvider() {
        //expect the capacity
        StringBuilder msg = new StringBuilder(128);
        msg.append("username:").append(username).
                append(",virtualHost: ").append(virtualHost).
                append(",exchange:").append(exchangeName).
                append(",routingKey:").append(routingKey);
        return msg.toString();
    }

    public String msgInfoForConsumer() {
        StringBuilder msg = new StringBuilder(128);
        msg.append("username:").append(username).
                append(",virtualHost:").append(virtualHost)
                .append(",ququeName:").append(queueName);
        return msg.toString();
    }
}
