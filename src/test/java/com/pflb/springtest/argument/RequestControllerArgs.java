package com.pflb.springtest.argument;

import com.pflb.springtest.model.exception.CustomExceptionType;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class RequestControllerArgs {

    public static Stream<Arguments> getAllRequests_thenReturnDtoList() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_expected_all.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> getRequestById_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_expected_single.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> getAllRequestsByTestProfileId_thenReturnDtoList() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_expected_by_testprofile.json").toPath()))
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

    public static Stream<Arguments> request_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_valid.json").toPath())),
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_expected_post.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> postRequest_thenReturnErrorDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        1,
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_invalid.json").toPath())),
                        HttpStatus.BAD_REQUEST,
                        CustomExceptionType.HTTP_MESSAGE_NOT_READABLE.getMessage()
                ),
                Arguments.of(
                        404,
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_valid.json").toPath())),
                        HttpStatus.NOT_FOUND,
                        CustomExceptionType.TEST_PROFILE_NOT_FOUND.getMessage()
                )
        );
    }

    public static Stream<Arguments> putRequest_thenReturnErrorDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        1,
                        1,
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_invalid.json").toPath())),
                        HttpStatus.BAD_REQUEST,
                        CustomExceptionType.HTTP_MESSAGE_NOT_READABLE.getMessage()
                ),
                Arguments.of(
                        404,
                        1,
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_valid.json").toPath())),
                        HttpStatus.NOT_FOUND,
                        CustomExceptionType.TEST_PROFILE_NOT_FOUND.getMessage()
                ),
                Arguments.of(
                        1,
                        404,
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:request/request_valid.json").toPath())),
                        HttpStatus.NOT_FOUND,
                        CustomExceptionType.REQUEST_NOT_FOUND.getMessage()
                )
        );
    }
}
