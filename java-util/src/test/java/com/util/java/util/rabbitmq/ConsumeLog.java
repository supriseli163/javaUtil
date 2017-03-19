package com.util.java.util.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumeLog {
    private static final String EXCHANGE_NAME_INFO = "logs_info";
    private static final String EXCHANGE_NAME_ERROR = "logs_error";
    private static final String QUQUE_NAME = "log_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = MQUtil.getChannel();
        channel.queueDeclare(QUQUE_NAME, true, false, false, null);

        //bing queue, subscribe info level logs
        channel.queueBind(QUQUE_NAME, EXCHANGE_NAME_INFO, "");
        //bing queue, subscribe error level logs
        channel.queueBind(QUQUE_NAME, EXCHANGE_NAME_ERROR, "");

        //callback
        Consumer callback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.err.println("receive message:" + message);
            }
        };

        //consumer consume message
        channel.basicConsume(QUQUE_NAME, true, callback);
    }
}
