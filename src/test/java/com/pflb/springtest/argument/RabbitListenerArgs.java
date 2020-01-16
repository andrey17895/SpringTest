package com.pflb.springtest.argument;

import com.pflb.springtest.provider.HarDtoProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class RabbitListenerArgs {
    public static Stream<Arguments> recieve() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.dto_valid_empty_body()
                )
        );
    }
}
