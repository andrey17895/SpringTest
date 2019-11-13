package com.pflb.SpringTest.messaging;

import com.pflb.SpringTest.parser.HarParserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "messaging")
public class RabbitMessagingSender implements MessagingService{

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
