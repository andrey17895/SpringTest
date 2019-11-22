package com.pflb.springtest.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mq")
@Getter
@Setter
public class RabbitSenderConfiguration {

    private String queueName;

}
