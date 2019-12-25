package com.pflb.springtest.argument;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class HistoryFileControllerArgs {

    public static Stream<Arguments> uploadFile_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        "/har/sintetic_good_get.json"
                ),
                Arguments.of(
                        "/har/har_valid_minimal.json"
                )
        );
    }


    public static Stream<Arguments> uploadFile_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(
                        "/har/har_invalid.json",
                        "JSON_MAPPING_EXCEPTION"
                )
        );
    }
}
