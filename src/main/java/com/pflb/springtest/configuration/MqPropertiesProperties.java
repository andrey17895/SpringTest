package com.pflb.springtest.configuration;

import lombok.Data;

@Data
public class MqPropertiesProperties {
    private MqConsumerProperties consumer;
    private MqProducerProperties producer;
}
