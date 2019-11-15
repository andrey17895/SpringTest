package com.pflb.SpringTest.messaging;

import com.pflb.SpringTest.parser.HarParserService;
import com.pflb.SpringTest.parser.wrapers.HarWrapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "messaging")
public class RabbitMessagingListener {

    @Getter
    @Setter
    private String queue;

    @Autowired
    private HarParserService harParserService;

    @RabbitListener(queues = "fileSend")
    public void recieve(String message) {
        System.out.println(message);
        Optional<HarWrapper> harWrapper = harParserService.parse(message);
        if (harWrapper.isPresent()) {
            System.out.println(harWrapper.get());
        }
    }
}
