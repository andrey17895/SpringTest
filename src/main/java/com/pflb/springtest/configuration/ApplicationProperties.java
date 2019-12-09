package com.pflb.springtest.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "springtest")
public class ApplicationProperties {

    private MqPropertiesProperties mq;

}
