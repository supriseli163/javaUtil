package com.util.java.util.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class MQUtil {
    private static Connection connection;
    private static ConnectionFactory connectionFactory;
    private static final String LOCAL_HOST = "127.0.0.1";
    private static final int MQ_PORT = 5672;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(LOCAL_HOST);
        connectionFactory.setPort(MQ_PORT);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Channel getChannel() {
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
