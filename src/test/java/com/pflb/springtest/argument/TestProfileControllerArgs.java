package com.pflb.springtest.argument;

import org.junit.jupiter.params.provider.Arguments;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class TestProfileControllerArgs {
    public static Stream<Arguments> testProfile_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:testprofile/testprofile_minimal.json").toPath())),
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:testprofile/testprofile_minimal_expected.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> testProfile_thenReturnErrorDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:testprofile/invalid_json.json").toPath())),
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:error/json_mapping_exception.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> getAllProfiles_thenReturnDtoList() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:testprofile/testprofile_expected_db.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> getTestProfileById_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:testprofile/testprofile_single_expected.json").toPath()))
                )
        );
    }

    public static Stream<Arguments> putTestProfile_thenReturnErrorDto() throws IOException {
        return Stream.of(
                Arguments.of(
//                        new String(Files.readAllBytes(Paths.get("classpath:testprofile/testprofile_minimal.json"))),
                        new String(Files.readAllBytes(ResourceUtils.getFile("classpath:testprofile/testprofile_minimal.json").toPath())),
                        "Test Profile not found"
                )
        );
    }

}
