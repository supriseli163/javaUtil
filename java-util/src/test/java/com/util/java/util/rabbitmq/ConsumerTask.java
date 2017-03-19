package com.util.java.util.rabbitmq;

        import com.rabbitmq.client.*;

        import java.io.IOException;

        import static com.util.java.util.rabbitmq.MQConstraint.EXCHANGE_NAME;
        import static com.util.java.util.rabbitmq.MQConstraint.FRUIT_QUQUE_NME;
        import static com.util.java.util.rabbitmq.MQConstraint.FRUIT_ROUTING_KEY;

public class ConsumerTask {
    public static void main(String[] args) throws IOException {
        Channel channel = MQUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(FRUIT_QUQUE_NME, true, false, false, null);
        channel.queueBind(FRUIT_QUQUE_NME, EXCHANGE_NAME, FRUIT_ROUTING_KEY);

        Consumer callback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.err.println("receive message:" + message);
            }
        };
        channel.basicConsume(FRUIT_QUQUE_NME, true, callback);
    }
}
