package com.demo.quick;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class SimpleJavaDemo {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
            AmqpTemplate template = context.getBean(AmqpTemplate.class);
            template.convertAndSend("myqueue", "simple java demo");
            String foo = (String) template.receiveAndConvert("myqueue");
            System.out.println(foo);

    }
    
    @Configuration
    public static class RabbitConfiguration {

        @Bean
        public CachingConnectionFactory connectionFactory() {
            return new CachingConnectionFactory("localhost", 5672);
        }

        @Bean
        public AmqpAdmin amqpAdmin() {
            return new RabbitAdmin(connectionFactory());
        }

        @Bean
        public RabbitTemplate rabbitTemplate() {
            return new RabbitTemplate(connectionFactory());
        }

        @Bean
        public Queue myQueue() {
           return new Queue("myqueue");
        }
    }

}

