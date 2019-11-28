package com.pflb.springtest.jms.producer;

import com.pflb.springtest.configuration.MqConsumerProperties;
import com.pflb.springtest.generator.TestGenerator;
import com.pflb.springtest.model.dto.har.HarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RabbitJmsProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MqConsumerProperties mqConsumerProperties;

    @InjectMocks
    private RabbitJmsProducer jmsProducer;

    @Test
    void sendMessage() {
        when(mqConsumerProperties.getQueueName()).thenReturn("queue");

        jmsProducer.sendMessage(TestGenerator.harDto("ya.ru", null));
        verify(rabbitTemplate).convertAndSend(eq("queue"), any(HarDto.class));
    }
}