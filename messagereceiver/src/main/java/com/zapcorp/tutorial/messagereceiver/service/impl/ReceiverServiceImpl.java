package com.zapcorp.tutorial.messagereceiver.service.impl;

import com.zapcorp.tutorial.messagereceiver.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;


@Slf4j
@RabbitListener(queues = "testMessageQueue")
public class ReceiverServiceImpl implements ReceiverService {

    @Value("${server.ssl.key-store}")
    private String keyStorePath;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePass;


    @RabbitHandler
    public void receive(String input) {
        log.info(String.format(" [x] Received '%s'", input));
    }

}
