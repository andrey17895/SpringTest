package com.pflb.springtest.service.provider;

import com.pflb.springtest.generator.TestGenerator;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Collections;
import java.util.stream.Stream;

public class RequestArgProvider {
    private static Stream<Arguments> createRequest_RequestNotFound() {
        return Stream.of(
                Arguments.of(
                        TestGenerator.requestDto("ya.ru")
                )
        );
    }

    private static Stream<Arguments> updateRequest_RequestNotFound() {
        return Stream.of(
                Arguments.of(
                        TestGenerator.requestDto("ya.ru"),
                        TestGenerator.testProfileEntity(1L, "ya.ru", "text", Collections.emptyMap())
                )
        );
    }
}
