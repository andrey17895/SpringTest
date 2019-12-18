package com.pflb.springtest.argument;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.JsonParsingException;
import com.pflb.springtest.provider.HarDtoProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class HapParserServiceArgs {
    public static Stream<Arguments> parse_thenThrowApplicationException() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.multipartFile("sintetic_good_get.json"),
                        JsonProcessingException.class,
                        JsonParsingException.class,
                        CustomExceptionType.JSON_PROCESSING_EXCEPTION
                ),
                Arguments.of(
                        HarDtoProvider.multipartFile("sintetic_good_get.json"),
                        JsonMappingException.class,
                        JsonParsingException.class,
                        CustomExceptionType.JSON_MAPPING_EXCEPTION
                ),
                Arguments.of(
                        HarDtoProvider.multipartFile("sintetic_good_get.json"),
                        IOException.class,
                        ApplicationException.class,
                        CustomExceptionType.FILE_IO_EXCEPTION
                )
        );
    }

    public static Stream<Arguments> parse_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.multipartFile("sintetic_good_get.json"),
                        HarDtoProvider.dtoFromFile("/har/sintetic_good_get.json")
                )
        );
    }


}
