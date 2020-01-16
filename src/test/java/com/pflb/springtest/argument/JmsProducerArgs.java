package com.pflb.springtest.argument;

import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.provider.HarDtoProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class JmsProducerArgs {

    private static Stream<Arguments> sendMessage_thenThrowException() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.dto_invalid_no_log(),
                        new ApplicationException(CustomExceptionType.AMQP_PRODUCER_EXCEPTION)
                )
        );
    }
}
