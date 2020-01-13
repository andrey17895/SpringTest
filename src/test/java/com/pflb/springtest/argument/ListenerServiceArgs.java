package com.pflb.springtest.argument;

import com.pflb.springtest.provider.HarDtoProvider;
import com.pflb.springtest.provider.TestProfileProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ListenerServiceArgs {
    public static Stream<Arguments> process() {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.dto_minimal_valid(),
                        TestProfileProvider.entity(null)
                )
        );
    }
}
