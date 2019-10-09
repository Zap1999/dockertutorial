package com.zapcorp.tutorial.messagereceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {
    @Bean
    public Receiver receiver() {
        System.out.println("receiver init and sleeping");
        try {
            Thread.sleep(11111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Receiver();
    }
}
