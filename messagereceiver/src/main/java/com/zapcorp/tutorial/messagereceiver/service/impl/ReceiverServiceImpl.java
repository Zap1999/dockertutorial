package com.zapcorp.tutorial.messagereceiver.service.impl;

import com.zapcorp.tutorial.messagereceiver.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Slf4j
@RabbitListener(queues = "testMessageQueue")
public class ReceiverServiceImpl implements ReceiverService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiverServiceImpl.class);

    @RabbitHandler
    public void receive(String input) {
        LOGGER.info(" [x] Received '" + input + "'");
    }

}
