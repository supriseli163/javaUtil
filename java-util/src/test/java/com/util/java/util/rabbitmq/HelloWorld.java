package com.util.java.util.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class HelloWorld {
    private static final String QUEUE_NAME = "hello.world.quque";
    private static final String EXCHANGE_NAME = "fruit_exchange";
    private static final String APPLE_ROUTING_KEY = "routing.fruit.apple";
    private static final String BANNER_ROUTING_KEY = "routing.fruit.banner";
    public static void main(String[] arsg) throws IOException, InterruptedException {
        consumerTask();
        producerTask();
        produceApple();
        produceBanner();
    }

    public static void consumerTask() throws IOException {
        Channel channel = MQUtil.getChannel();
        //declare queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //set call back
        Consumer callback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                String message = new String(body);
                System.err.println(Thread.currentThread().getName() + "receive message" + message);
            }
        };

        //consumer message
        channel.basicConsume(QUEUE_NAME, true, callback);
    }

    public static void producerTask() throws IOException, InterruptedException {
        //get connection
        Channel channel = MQUtil.getChannel();
        for(int i = 0; i < 10; i++) {
            //define message
            String message = "message" + i;
            Thread.sleep(1000);
            channel.basicPublish(EXCHANGE_NAME, QUEUE_NAME, null, message.getBytes());
            System.err.println("product message success!");
        }
    }

    public static void produceApple() throws IOException {
        //get channel
        Channel channel = MQUtil.getChannel();

        //declare exchange type
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message = "create apple";
        //produce message
        channel.basicPublish(EXCHANGE_NAME, APPLE_ROUTING_KEY, null, message.getBytes());
        System.err.println("produce apple message success!");
    }

    public static void produceBanner() throws IOException {
        //get channel
        Channel channel = MQUtil.getChannel();
        //declare exchange type
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String message = "create banner";

        channel.basicPublish(EXCHANGE_NAME, BANNER_ROUTING_KEY, null, message.getBytes());
        System.err.println("produce banner message success!");

    }
}
