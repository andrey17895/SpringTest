package com.pflb.springtest.argument;

import com.pflb.springtest.provider.RequestProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class RequestServiceArgs {

    public static Stream<Arguments> getAllRequests_thenReturnDtoList() {
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

    public static Stream<Arguments> getAllRequestsByTestProfileId_thenReturnDtoList() {
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
    public static Stream<Arguments> getRequestById_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto("ya.ru"),
                        RequestProvider.entity(1L, "ya.ru")
                )
        );
    }
    public static Stream<Arguments> createRequest_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto("ya.ru"),
                        RequestProvider.entity(1L, "ya.ru")
                )
        );
    }

    public static Stream<Arguments> updateRequest_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto("ya.ru"),
                        RequestProvider.entity(1L, "ya.ru")
                )
        );
    }
}
