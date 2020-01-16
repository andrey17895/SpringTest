package com.pflb.springtest.argument;

import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.provider.HarDtoProvider;
import com.pflb.springtest.provider.TestProfileProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class ListenerServiceArgs {
    public static Stream<Arguments> process_thenSaveTestProfile() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.dto_valid_empty_body(),
                        TestProfileProvider.entity_id_null()
                ),
                Arguments.of(
                        HarDtoProvider.dto_valid_with_body(),
                        TestProfileProvider.entity_valid_post_with_body()
                )
        );
    }
    public static Stream<Arguments> process_thenThrowException() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.dto_invalid_no_log(),
                        new ApplicationException(CustomExceptionType.BAD_HAR_FILE)
                )
        );
    }
}
