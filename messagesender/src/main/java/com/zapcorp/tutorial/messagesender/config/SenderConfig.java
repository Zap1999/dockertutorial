package com.zapcorp.tutorial.messagesender.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SenderConfig {


    @Bean
    public Queue testMessageQueue() {
        return new Queue("testMessageQueue");
    }

}
