package com.zapcorp.tutorial.messagesender;

import lombok.Setter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Setter
public class Sender {

    private RabbitTemplate template;
    private Queue queue;

    public void send(String message) {
        if (message == null) {
            throw new IllegalArgumentException("Message to be sent cannot be a null value.");
        }
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] 10 messages sent. Messages text: " + message);
    }

}
