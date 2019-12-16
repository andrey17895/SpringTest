package com.pflb.springtest.service.provider;

import com.pflb.springtest.generator.HarDtoProvider;
import com.pflb.springtest.generator.TestProfileProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ListenerArgProvider {
    public static Stream<Arguments> process() {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.harDto(0, ""),
                        TestProfileProvider.entity(null)
                )
        );
    }
}
