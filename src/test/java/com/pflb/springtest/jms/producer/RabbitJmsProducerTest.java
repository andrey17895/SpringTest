package com.pflb.springtest.jms.producer;

import com.pflb.springtest.configuration.MqProducerProperties;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.provider.HarDtoProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitJmsProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MqProducerProperties mqProducerProperties;

    @InjectMocks
    private RabbitJmsProducer jmsProducer;

    @Test
    void sendMessage_thenVerifyRabbitTemplate_whenMqWorking() throws IOException {

        when(mqProducerProperties.getQueueName()).thenReturn("queue");

        jmsProducer.sendMessage(HarDtoProvider.dto_valid_empty_body());

        verify(rabbitTemplate).convertAndSend(eq("queue"), any(HarDto.class));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.JmsProducerArgs#sendMessage_thenThrowException")
    void sendMessage_thenThrowException_whenMqFail(HarDto message,
                                                   ApplicationException expectedException) {

        when(mqProducerProperties.getQueueName()).thenReturn("queue");
        doThrow(AmqpException.class).when(rabbitTemplate).convertAndSend(anyString(), any(HarDto.class));

        ApplicationException actualException =
                assertThrows(ApplicationException.class, () -> jmsProducer.sendMessage(message));

        assertEquals(expectedException, actualException);
    }
}