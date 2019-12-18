package com.pflb.springtest.service.provider;

import com.pflb.springtest.generator.RequestProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class RequestArgProvider {

    public static Stream<Arguments> getAllRequests_Ok() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(RequestProvider.dto("ya.ru")),
                        Collections.singletonList(RequestProvider.entity(1L, "ya.ru"))
                ),
                Arguments.of(
                        Arrays.asList(
                                RequestProvider.dto("ya.ru"),
                                RequestProvider.dto("boomq.io")
                        ),
                        Arrays.asList(
                                RequestProvider.entity(1L, "ya.ru"),
                                RequestProvider.entity(2L, "boomq.io")
                        )
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
    }

    public static Stream<Arguments> getAllRequestsByTestProfileId() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(RequestProvider.dto("ya.ru")),
                        Collections.singletonList(RequestProvider.entity(1L, "ya.ru"))
                ),
                Arguments.of(
                        Arrays.asList(
                                RequestProvider.dto("ya.ru"),
                                RequestProvider.dto("boomq.io")
                        ),
                        Arrays.asList(
                                RequestProvider.entity(1L, "ya.ru"),
                                RequestProvider.entity(2L, "boomq.io")
                        )
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
    }
    public static Stream<Arguments> getRequestById_Ok() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto("ya.ru"),
                        RequestProvider.entity(1L, "ya.ru")
                )
        );
    }
    public static Stream<Arguments> createRequest_Ok() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto("ya.ru"),
                        RequestProvider.entity(1L, "ya.ru")
                )
        );
    }

    public static Stream<Arguments> updateRequest_Ok() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto("ya.ru"),
                        RequestProvider.entity(1L, "ya.ru")
                )
        );
    }
}
