package com.pflb.springtest.jms.producer;

import com.pflb.springtest.model.dto.har.HarDto;

public interface JmsProducer {
    void sendMessage(HarDto message);
}
