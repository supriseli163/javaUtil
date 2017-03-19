package com.util.java.util.rabbitmq;

import com.rabbitmq.client.Channel;
import org.testng.annotations.Test;

import java.io.IOException;

public class MQLog {
    private static final String EXCHANGE_NAME_ERROR = "logs_error";
    private static final String EXCHANGE_NAME_INFO = "logs_info";

    private static Channel channel;
    static {
        channel = MQUtil.getChannel();
        try {
            channel.exchangeDeclare(EXCHANGE_NAME_ERROR, "fanout");
            channel.exchangeDeclare(EXCHANGE_NAME_INFO, "fanout");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPublish() throws IOException {
        (new Task3()).run();

    }

    public static class Task1 implements Runnable {
        @Override
        public void run() {
                String message = "log error message";
                try {
                    channel.basicPublish(EXCHANGE_NAME_ERROR, "", null, message.getBytes());
                    System.err.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static class Task2 implements Runnable {
        @Override
        public void run() {
                String message = "log info message";
                try {
                    channel.basicPublish(EXCHANGE_NAME_INFO, "", null, message.getBytes());
                    System.err.println(message);
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public static class Task3 implements Runnable {
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        @Override
        public void run() {
            while (true) {
                thread1.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread2.run();
            }
        }
    }
}
