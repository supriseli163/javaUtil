package com.base.java.util.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class MQConnection {
    private Connection connection;
    private int max;
    private Object lock = new Object();

    private LinkedTransferQueue<Channel> queues = new LinkedTransferQueue<>();

    public MQConnection(Connection connection, int max) {
        this.connection = connection;
        this.max = max;
    }

    public Channel getChannel() throws InterruptedException, IOException{
        Channel channel = queues.take();
        if(channel == null) {
            synchronized (lock) {
                channel = queues.take();
                if(channel != null) {
                    return channel;
                }
                if(queues.size() >= max) {
                    throw new IllegalAccessError("");
                }
                channel = connection.createChannel();
                queues.offer(channel, 100, TimeUnit.MICROSECONDS);
            }
        }
        return channel;
    }

    public void returnChannel(Channel channel) throws IOException {
        if(queues.size() >= max) {
            if(channel.isOpen()) {
                channel.close();
            }
        } else {
            if(channel.isOpen()) {
                queues.add(channel);
            }
        }
    }

    public boolean isOPen() {
        if(connection == null) {
            return false;
        }
        return connection.isOpen();
    }
}
