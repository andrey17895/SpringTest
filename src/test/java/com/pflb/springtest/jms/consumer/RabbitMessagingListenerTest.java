package com.pflb.springtest.jms.consumer;

import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.service.IListenerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMessagingListenerTest {

    @Mock
    private IListenerService service;

    @InjectMocks
    private RabbitMessagingListener listener;

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RabbitListenerArgs#recieve")
    void recieve(HarDto harDto) throws IOException {

        listener.recieve(harDto);

        verify(service).process(harDto);
    }
}