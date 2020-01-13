package com.pflb.springtest.configuration;

import lombok.Data;

@Data
public class MqProducerProperties {
    private String queueName;
    private String deadQueueName;
}
