package com.base.java.util.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MQConnectorPool {
    private List<MQConnection> aliveCOnnections = new LinkedList<>();
    private List<MQConnection> deadConnection = new LinkedList<>();

    private ConnectionFactory factory;
    private MQConfig config;

    public MQConnectorPool(MQConfig config) {
        this.config = config;
        factory = new ConnectionFactory();
        URI uri = URI.create(config.getUrl());
        factory.setHost(uri.getHost());
        factory.setPort(uri.getPort());
        factory.setPassword(config.getPassword());
        factory.setUsername(config.getUrl());
        factory.setVirtualHost(config.getVirtualHost());
        factory.setAutomaticRecoveryEnabled(true);
        factory.setRequestedHeartbeat(5);
        factory.setConnectionTimeout(2000);
    }

    public void init() {
        for(int i = 0; i < config.getCount(); i++) {
            Connection connection = null;
            try {
                connection = factory.newConnection();
                aliveCOnnections.add(new MQConnection(connection, config.getChannelCount()));
            } catch (IOException e) {
                throw new IllegalStateException("create connection fail",e);
            }
        }
    }

    public MQConnection getConnection() {
        Random random = new Random(System.currentTimeMillis());
        if(aliveCOnnections.size() <= 0) {
            throw new RuntimeException("all connection interrupted.");
        }

        int i = random.nextInt(aliveCOnnections.size());

        MQConnection connection = aliveCOnnections.get(i);
        if(!connection.isOPen()) {
            aliveCOnnections.remove(i);
            deadConnection.add(connection);
        }
        return connection;
    }
}
