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
                        Collections.singletonList(RequestProvider.dto_yaru_get()),
                        Collections.singletonList(RequestProvider.entity_yaru_get_id_1()
                ),
                Arguments.of(
                        Arrays.asList(
                                RequestProvider.dto_yaru_get(),
                                RequestProvider.dto_boomqio_get()
                        ),
                        Arrays.asList(
                                RequestProvider.entity_yaru_get_id_1(),
                                RequestProvider.entity_boomqio_get_id_2()
                        )
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
            )
        );
    }

    public static Stream<Arguments> getAllRequestsByTestProfileId_thenReturnDtoList() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(RequestProvider.dto_yaru_get()),
                        Collections.singletonList(RequestProvider.entity_yaru_get_id_1())
                ),
                Arguments.of(
                        Arrays.asList(
                                RequestProvider.dto_yaru_get(),
                                RequestProvider.dto_boomqio_get()
                        ),
                        Arrays.asList(
                                RequestProvider.entity_yaru_get_id_1(),
                                RequestProvider.entity_boomqio_get_id_2()
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
                        RequestProvider.dto_yaru_get(),
                        RequestProvider.entity_yaru_get_id_1()
                )
        );
    }
    public static Stream<Arguments> createRequest_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto_yaru_get(),
                        RequestProvider.entity_yaru_get_id_1()
                )
        );
    }

    public static Stream<Arguments> updateRequest_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        RequestProvider.dto_yaru_get(),
                        RequestProvider.entity_yaru_get_id_1()
                )
        );
    }
}
