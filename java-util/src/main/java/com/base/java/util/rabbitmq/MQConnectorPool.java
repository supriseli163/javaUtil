package com.base.java.util.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MQConnectorPool {
    private List<MQConnection> aliveConnections = new LinkedList<>();
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
                aliveConnections.add(new MQConnection(connection, config.getChannelCount()));
            } catch (IOException e) {
                throw new IllegalStateException("create connection fail",e);
            }
        }
    }

    public MQConnection getConnection() {
        Random random = new Random(System.currentTimeMillis());
        if(aliveConnections.size() <= 0) {
            throw new RuntimeException("all connection interrupted.");
        }

        int i = random.nextInt(aliveConnections.size());

        MQConnection connection = aliveConnections.get(i);
        if(!connection.isOPen()) {
            aliveConnections.remove(i);
            deadConnection.add(connection);
        }
        return connection;
    }
}
