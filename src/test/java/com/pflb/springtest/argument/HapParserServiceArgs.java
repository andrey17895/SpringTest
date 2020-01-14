package com.pflb.springtest.argument;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.provider.HarDtoProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class HapParserServiceArgs {
    public static Stream<Arguments> parse_thenThrowApplicationException() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.multipart_valid_har(),
                        JsonProcessingException.class,
                        ApplicationException.class,
                        CustomExceptionType.JSON_PROCESSING_EXCEPTION
                ),
                Arguments.of(
                        HarDtoProvider.multipart_valid_har(),
                        JsonMappingException.class,
                        ApplicationException.class,
                        CustomExceptionType.JSON_MAPPING_EXCEPTION
                ),
                Arguments.of(
                        HarDtoProvider.multipart_valid_har(),
                        IOException.class,
                        ApplicationException.class,
                        CustomExceptionType.FILE_IO_EXCEPTION
                )
        );
    }

    public static Stream<Arguments> parse_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.multipart_valid_har(),
                        HarDtoProvider.dto_good_har()
                )
        );
    }


}
