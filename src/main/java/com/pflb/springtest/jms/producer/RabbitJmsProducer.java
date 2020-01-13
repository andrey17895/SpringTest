package com.pflb.springtest.jms.producer;

import com.pflb.springtest.configuration.MqProducerProperties;
import com.pflb.springtest.model.dto.har.HarDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitJmsProducer implements JmsProducer {

    private RabbitTemplate rabbitTemplate;

    private MqProducerProperties properties;

    @Autowired
    public RabbitJmsProducer(RabbitTemplate rabbitTemplate, MqProducerProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void sendMessage(HarDto message) {
        rabbitTemplate.convertAndSend(properties.getQueueName(), message);
    }

    public void sendDeadMessage(HarDto message) {
        rabbitTemplate.convertAndSend(properties.getDeadQueueName(), message);
    }
}
