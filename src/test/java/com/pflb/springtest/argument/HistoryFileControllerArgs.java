package com.pflb.springtest.argument;

import org.junit.jupiter.params.provider.Arguments;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class HistoryFileControllerArgs {

    public static Stream<Arguments> uploadFile_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new MockMultipartFile("file", Files.readAllBytes(ResourceUtils.getFile("classpath:har/sintetic_good_get.json").toPath()))

                ),
                Arguments.of(
                        new MockMultipartFile("file", Files.readAllBytes(ResourceUtils.getFile("classpath:har/har_valid_minimal.json").toPath()))
                )
        );
    }


    public static Stream<Arguments> uploadFile_thenReturnErrorDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new MockMultipartFile("file", Files.readAllBytes(ResourceUtils.getFile("classpath:har/har_invalid.json").toPath())),
                        "Json mapping exception"
                )
        );
    }
}
