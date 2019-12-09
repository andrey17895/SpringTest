package com.pflb.springtest.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    ApplicationProperties applicationProperties;

    @Autowired
    public ApplicationConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MqConsumerProperties mqConsumer() {
        return applicationProperties.getMq().getConsumer();
    }

    @Bean
    public MqProducerProperties mqProducer() {
        return applicationProperties.getMq().getProducer();
    }

}
