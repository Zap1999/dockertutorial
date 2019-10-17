package com.zapcorp.tutorial.messagesender.service.impl;

import com.zapcorp.tutorial.messagesender.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SenderServiceImpl implements SenderService {

    private RabbitTemplate template;

    private Queue queue;

    private String lastSentMessage = null;

    @Override
    public void send(String message) {
        if (message == null) {
            throw new IllegalArgumentException("Message to be sent cannot be a null value.");
        }

        this.template.convertAndSend(queue.getName(), message);
        lastSentMessage = message;

        log.info(" [x] Message sent. Message text: " + message);
    }

    @Override
    public String getLastMessage() {
        return lastSentMessage;
    }

    @Autowired
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    @Autowired
    public void setTemplate(RabbitTemplate template) {
        this.template = template;
    }

}
