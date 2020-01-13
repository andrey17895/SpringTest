package com.pflb.springtest.jms.consumer;

import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.provider.HarDtoProvider;
import com.pflb.springtest.service.IListenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMessagingListenerTest {

    @Mock
    private IListenerService service;

    @InjectMocks
    private RabbitMessagingListener listener;

    @Test
    void recieve() {
        HarDto harDto = HarDtoProvider.dto_minimal_valid();

        listener.recieve(harDto);

        verify(service).process(harDto);
    }
}