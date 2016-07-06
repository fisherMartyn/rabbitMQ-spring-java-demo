package com.demo.quick;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlDemo {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new GenericXmlApplicationContext("classpath:/rabbit-simple-context.xml");
            AmqpTemplate template = context.getBean(AmqpTemplate.class);
            template.convertAndSend("myqueue", "simple xml demo");
            String foo = (String) template.receiveAndConvert("myqueue");
            System.out.println(foo);
    }
}
