package com.demo.quick;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class VerifyQuickDemo {

    public static void main(String[] args) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost", 5672);
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myqueue", "this is a very quick demo");
        String foo = (String) template.receiveAndConvert("myqueue");
        System.out.println(foo);

    }

}
