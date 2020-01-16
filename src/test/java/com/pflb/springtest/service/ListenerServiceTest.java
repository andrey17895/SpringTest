package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.impl.ListenerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @MethodSource("com.pflb.springtest.argument.ListenerServiceArgs#process_thenSaveTestProfile")
    void process_thenSaveTestProfile_whenValid(HarDto harDto, TestProfile testProfileEntity) {

        listenerService.process(harDto);

        verify(
                testProfileRepository,
                times(1)
        ).save(eq(testProfileEntity));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.ListenerServiceArgs#process_thenThrowException")
    void process_thenThrowException_whenInvalid(HarDto harDto, ApplicationException expectedException) {

        ApplicationException actualException =
                assertThrows(ApplicationException.class, () -> listenerService.process(harDto));

        assertEquals(expectedException, actualException);
    }
}