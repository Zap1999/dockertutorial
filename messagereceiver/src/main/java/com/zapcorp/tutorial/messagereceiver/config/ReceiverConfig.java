package com.zapcorp.tutorial.messagereceiver.config;

import com.zapcorp.tutorial.messagereceiver.service.Receiver;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

}
