package com.pflb.springtest.jms.producer;

import com.pflb.springtest.dto.HarDto;

public interface JmsProducer {
    void sendMessage(HarDto message);
}
