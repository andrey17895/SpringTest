package com.pflb.springtest.jms.consumer;

import com.pflb.springtest.dto.HarDto;
import com.pflb.springtest.service.ListenerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessagingListener {

    private ListenerService listenerService;

    @Autowired
    public RabbitMessagingListener(ListenerService listenerService) {
        this.listenerService = listenerService;
    }

    @RabbitListener(queues = "${mq.queueName}")
    public void recieve(HarDto message) {
        listenerService.process(message);
    }


}
