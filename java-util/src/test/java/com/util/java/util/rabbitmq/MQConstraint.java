package com.util.java.util.rabbitmq;

public final class MQConstraint {
    private MQConstraint() {}

    //exchange name
    public static final String EXCHANGE_NAME = "fruit_exchange";
    //routing key
    public static final String FRUIT_ROUTING_KEY = "routing.fruit.#";
    //queue name
    public static final String FRUIT_QUQUE_NME = "fruit_queue_name";
}
