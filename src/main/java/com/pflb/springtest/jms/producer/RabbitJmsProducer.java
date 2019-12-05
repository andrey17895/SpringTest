package com.pflb.springtest.jms.producer;

import com.pflb.springtest.configuration.MqConsumerProperties;
import com.pflb.springtest.model.dto.har.HarDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitJmsProducer implements JmsProducer {

    private RabbitTemplate rabbitTemplate;

    private MqConsumerProperties properties;

    @Autowired
    public RabbitJmsProducer(RabbitTemplate rabbitTemplate, MqConsumerProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void sendMessage(HarDto message) {
        rabbitTemplate.convertAndSend(properties.getQueueName(), message);
    }
}
