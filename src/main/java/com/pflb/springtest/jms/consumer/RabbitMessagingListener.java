package com.pflb.springtest.jms.consumer;

import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.service.IListenerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessagingListener {

    private IListenerService listenerService;

    @Autowired
    public RabbitMessagingListener(IListenerService listenerService) {
        this.listenerService = listenerService;
    }

    @RabbitListener(queues = "${springtest.mq.consumer.queueName}")
    public void recieve(HarDto message) {
        listenerService.process(message);
    }


}
