/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/25 16:10
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/25 16:10
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/25 16:10
 */
public class Consumer01Test {

    private static String exchange = "test-exchange";
    private static String routKey = "test-key";
    private static String queue = "test-queue";
    private static String ip = "192.168.109.144";
    private static int port = 5672;
    static ConnectionFactory factory = new ConnectionFactory();
    static Connection connection = null;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT,true,false,null);
//        channel.queueDeclare(queue,true,false,false,null);
//        channel.queueBind(queue,exchange,routKey);
//        channel.basicPublish(exchange,routKey, MessageProperties.TEXT_PLAIN,
//                "hello word".getBytes());
//        Consumer consumer = new DefaultConsumer(channel);
//        if(channel.)
        channel.basicConsume(queue,true,new DefaultConsumer(channel) {

            public void handleDelivery(String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body)
                    throws IOException
            {
                System.out.println(envelope.getDeliveryTag());
                System.out.println(envelope.getExchange());
                System.out.println(envelope.getRoutingKey());
                System.out.println(new String(body,"utf-8"));
                System.out.println(properties.getMessageId());
            }

        });
        Thread.sleep(1000);
        channel.close();
        close();
    }

    public static void close() throws IOException {
        connection.close();
    }
    public static Connection getConnection() {

        try {
            factory.setUsername("admin");
            factory.setPassword("123456");
            factory.setPort(port);
            factory.setHost(ip);
            if(null == connection) {
                connection = factory.newConnection();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
