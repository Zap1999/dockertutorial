package com.zapcorp.tutorial.messagesender;

import com.zapcorp.tutorial.messagesender.service.Sender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class SenderTest {

    private Sender sender;

    @Mock
    private RabbitTemplate template;
    @Mock
    private Queue queue;

    @Before
    public void initialize() {
        sender = new Sender();
        sender.setQueue(queue);
        sender.setTemplate(template);
    }

    @Test
    public void send() {
        String message = "Some message";
        String name = "name";
        when(queue.getName()).thenReturn(name);
        sender.send(message);
        verify(template, times(1)).convertAndSend(queue.getName(), message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendNull() {
        sender.send(null);
    }
}
