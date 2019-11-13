package com.pflb.SpringTest.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
//import org.springframework.amqp.support.converter.SmartMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessagingConfig {
//
//    @Bean
//    public SmartMessageConverter messageConverter() {
//        return new SmartMessageConverter() {
//            @Override
//            public Object fromMessage(Message message, Object o) throws MessageConversionException {
//                return null;
//            }
//
//            @Override
//            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
//                return null;
//            }
//
//            @Override
//            public Object fromMessage(Message message) throws MessageConversionException {
//                return null;
//            }
//        };
//    }
//
//}