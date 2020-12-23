//package com.eric.rabbitmq;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//@Component
//@Slf4j
//public class Consumer {
//
//    //@RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "test01",durable = "true"),// 设置选择队列名称
//            exchange = @Exchange(
//                    value = "exchange01", // 选择交换机的名称
//                    ignoreDeclarationExceptions = "true", // 消息持久化
//                    type = "topic"// 选择可用topic的交换机
//            ),
//            // 指定 routingKey，使用通配符，直接匹配上边的 a.b
//            key = {"test01"}))
//    public void get(String msg) {
//        //channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
//        System.out.println("接收到的消息:  " + msg);
//    }
//
//
//    @RabbitListener(queues = "test01")
//    public void process(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        log.info("receive: " + new String(message.getBody()));
//    }
//
//    @RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "queue-1",durable = "true"),
//            exchange = @Exchange(value = "exchange-1", type="topic",ignoreDeclarationExceptions = "true"),
//            key = "springboot.*"
//    ))
//    private void onMessage(Message message,Channel channel) throws Exception{
//        System.err.println("---------------");
//        System.err.println("消费端body ："+ Arrays.toString(message.getBody()));
//        //Long deliveryTag = (Long)message.;
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//    }
//}
