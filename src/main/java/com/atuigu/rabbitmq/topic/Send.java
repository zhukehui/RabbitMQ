package com.atuigu.rabbitmq.topic;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * 生产者，模拟为商品服务
 */
public class Send {
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为topic                        持久化
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);
        // 消息内容
        String message = "删除商品 : id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品        消息持久化
        channel.basicPublish(EXCHANGE_NAME, "item.delete",MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}