package com.zapcorp.tutorial.messagereceiver.service.impl;

import com.zapcorp.tutorial.messagereceiver.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Slf4j
@RabbitListener(queues = "testMessageQueue")
public class ReceiverServiceImpl implements ReceiverService {

    @RabbitHandler
    public void receive(String input) {
        log.info(String.format(" [x] Received '%s'", input));
    }

}
