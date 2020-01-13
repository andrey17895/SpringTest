package com.pflb.springtest.jms.producer;

import com.pflb.springtest.configuration.MqProducerProperties;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.provider.HarDtoProvider;
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
    private MqProducerProperties mqProducerProperties;

    @InjectMocks
    private RabbitJmsProducer jmsProducer;

    @Test
    void sendMessage() {
        when(mqProducerProperties.getQueueName()).thenReturn("queue");

        jmsProducer.sendMessage(HarDtoProvider.dto_minimal_valid());

        verify(rabbitTemplate).convertAndSend(eq("queue"), any(HarDto.class));
    }

    @Test
    void deadQueueMessage() {
        when(mqProducerProperties.getDeadQueueName()).thenReturn("deadQueue");

        jmsProducer.sendDeadMessage(HarDtoProvider.dto_minimal_valid());

        verify(rabbitTemplate).convertAndSend(eq("deadQueue"), any(HarDto.class));
    }
}