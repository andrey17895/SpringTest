package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.impl.ListenerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ListenerServiceTest {

    @Mock
    private TestProfileRepository testProfileRepository;

    @InjectMocks
    private ListenerService listenerService;



    @ParameterizedTest
    @MethodSource("com.pflb.springtest.service.provider.ListenerArgProvider#process")
    void process(HarDto harDto, TestProfile testProfileEntity) {
        listenerService.process(harDto);

        verify(
                testProfileRepository,
                times(1)
        ).save(eq(testProfileEntity));
    }



}