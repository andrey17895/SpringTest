package com.pflb.springtest.argument;

import com.pflb.springtest.provider.ResourceProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestProfileControllerArgs {
    public static Stream<Arguments> testProfile_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/testprofile/testprofile_minimal.json"),
                        ResourceProvider.getAsString("/testprofile/testprofile_minimal_expected.json")
                )
        );
    }

    public static Stream<Arguments> testProfile_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/testprofile/invalid_json.json"),
                        ResourceProvider.getAsString("/error/json_mapping_exception.json")
                )
        );
    }

    public static Stream<Arguments> getAllProfiles_thenReturnDtoList() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/testprofile/testprofile_expected_db.json")
                )
        );
    }

    public static Stream<Arguments> getTestProfileById_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/testprofile/testprofile_single_expected.json")
                )
        );
    }

    public static Stream<Arguments> putTestProfile_thenReturnErrorDto() {
        return Stream.of(
                Arguments.of(
                        ResourceProvider.getAsString("/testprofile/testprofile_minimal.json")
                )
        );
    }
}
