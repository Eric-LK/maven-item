//package com.eric.rabbitmq;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class Producer {
//    @Test
//    public void contextLoads(){
//
//    }
//
//    @Autowired
//    private RabbitAdmin rabbitAdmin;
//
//    @Test
//    public void testAdmin() throws Exception{
//        // 创建交换机
//        rabbitAdmin.declareExchange(new DirectExchange("test.direct",false,false));
//        rabbitAdmin.declareExchange(new TopicExchange("test.topic",false,false));
//        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout",false,false));
//        // 创建消息队列
//        rabbitAdmin.declareQueue(new Queue("test.direct.queue",false));
//        rabbitAdmin.declareQueue(new Queue("test.topic.queue",false));
//        rabbitAdmin.declareQueue(new Queue("test.fanout.queue",false));
//        // 创建绑定关系
//        rabbitAdmin.declareBinding(new Binding("test.direct.queue",Binding.DestinationType.QUEUE,"test.direct","direct",new HashMap<>()));
//
//        rabbitAdmin.declareBinding
//                (BindingBuilder
//                        .bind(new Queue("test.topic.queue",false))
//                        .to(new TopicExchange("test.topic",false,false))
//                        .with("user.#"));
//
//        rabbitAdmin.declareBinding
//                (BindingBuilder
//                        .bind(new Queue("test.fanout.queue",false))
//                        .to(new FanoutExchange("test.fanout",false,false)));// 全部分配
//
//    }
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Test
//    public void testSendMessage() throws Exception{
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.getHeaders().put("desc","信息描述");
//        messageProperties.getHeaders().put("type","自定义资源类型");
//        Message message = new Message("Hello rabbitMq".getBytes(),messageProperties);
//        rabbitTemplate.convertAndSend("test.topic", "user.123", message, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                System.err.println("________哈哈哈哈");
//                message.getMessageProperties().getHeaders().put("desc","额外修改的信息描述");
//                message.getMessageProperties().getHeaders().put("attr","额外新加的属性");
//                return message;
//            }
//        });
//    }
//
//    @Test
//    public void testSendMessageTwo() throws Exception{
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setContentType("text/plain");
//        Message message = new Message("Hello rabbitMq".getBytes(),messageProperties);
//
//        rabbitTemplate.send("test_consumer_exchange","consumer.123",message);
//        rabbitTemplate.convertAndSend("test.topic", "user.345","345");
//        rabbitTemplate.convertAndSend("test.topic", "user.345","456");
//    }
//
//
//}
