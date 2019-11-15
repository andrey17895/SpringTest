package com.pflb.SpringTest.messaging.sender;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "messaging")
public class RabbitMessagingSender implements MessagingSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Getter
    @Setter
    private String queue;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(this.queue, message);
//        String responseMessage = rabbitTemplate.receiveAndConvert(this.queue, ParameterizedTypeReference.forType(String.class));
//        String responseMessage = rabbitTemplate.receive(queue).toString();
//        System.out.println(responseMessage);
    }
}
