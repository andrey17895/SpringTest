package com.pflb.springtest.argument;

import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.provider.ResourceProvider;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public class RequestControllerArgs {

    public static Stream<Arguments> getAllRequests_thenReturnDtoList() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/request/request_expected_all.json")
                )
        );
    }

    public static Stream<Arguments> getRequestById_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/request/request_expected_single.json")
                )
        );
    }

    public static Stream<Arguments> getAllRequestsByTestProfileId_thenReturnDtoList() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/request/request_expected_by_testprofile.json")
                )
        );
    }

    public static Stream<Arguments> getAllRequestsByTestProfileId_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(
                        "Test Profile not found"
                )
        );
    }

    public static Stream<Arguments> getRequestById_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(1, 404, "Request not found"),
                Arguments.of(404, 1, "Test Profile not found")
        );
    }

    public static Stream<Arguments> request_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/request/request_valid.json"),
                        ResourceProvider.getAsString("/request/request_expected_post.json")
                )
        );
    }

    public static Stream<Arguments> postRequest_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(
                        1,
                        ResourceProvider.getAsString("/request/request_invalid.json"),
                        HttpStatus.BAD_REQUEST,
                        CustomExceptionType.HTTP_MESSAGE_NOT_READABLE.getMessage()
                ),
                Arguments.of(
                        404,
                        ResourceProvider.getAsString("/request/request_valid.json"),
                        HttpStatus.NOT_FOUND,
                        CustomExceptionType.TEST_PROFILE_NOT_FOUND.getMessage()
                )
        );
    }

    public static Stream<Arguments> putRequest_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(
                        1,
                        1,
                        ResourceProvider.getAsString("/request/request_invalid.json"),
                        HttpStatus.BAD_REQUEST,
                        CustomExceptionType.HTTP_MESSAGE_NOT_READABLE.getMessage()
                ),
                Arguments.of(
                        404,
                        1,
                        ResourceProvider.getAsString("/request/request_valid.json"),
                        HttpStatus.NOT_FOUND,
                        CustomExceptionType.TEST_PROFILE_NOT_FOUND.getMessage()
                ),
                Arguments.of(
                        1,
                        404,
                        ResourceProvider.getAsString("/request/request_valid.json"),
                        HttpStatus.NOT_FOUND,
                        CustomExceptionType.REQUEST_NOT_FOUND.getMessage()
                )
        );
    }
}
