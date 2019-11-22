package com.pflb.springtest.jms.producer;

import com.pflb.springtest.configuration.RabbitSenderConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitJmsProducer implements JmsProducer {

    private RabbitTemplate rabbitTemplate;

    private RabbitSenderConfiguration rabbitSenderConfiguration;

    @Autowired
    public RabbitJmsProducer(RabbitTemplate rabbitTemplate, RabbitSenderConfiguration rabbitSenderConfiguration) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitSenderConfiguration = rabbitSenderConfiguration;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(rabbitSenderConfiguration.getQueueName(), message);
    }
}
