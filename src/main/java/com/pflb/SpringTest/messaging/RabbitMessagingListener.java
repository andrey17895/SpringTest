package com.pflb.SpringTest.messaging;

import com.pflb.SpringTest.data.entities.TestProfile;
import com.pflb.SpringTest.parser.HarParserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
        System.out.println("Recieved, mazafaka!");
        System.out.println(message);
//        TestProfile profile = harParserService.parse(message);
//        System.out.println(profile.toString());
    }
}
