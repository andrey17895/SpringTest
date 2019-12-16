package com.pflb.springtest.service.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pflb.springtest.generator.TestGenerator;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.JsonParsingException;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public class HapParserArgProvider {
    public static Stream<Arguments> parse_Exception() throws IOException {
        return Stream.of(
                Arguments.of(
                        TestGenerator.multipartFile(),
                        JsonProcessingException.class,
                        JsonParsingException.class,
                        CustomExceptionType.JSON_PROCESSING_EXCEPTION
                ),
                Arguments.of(
                        TestGenerator.multipartFile(),
                        JsonMappingException.class,
                        JsonParsingException.class,
                        CustomExceptionType.JSON_MAPPING_EXCEPTION
                ),
                Arguments.of(
                        TestGenerator.multipartFile(),
                        IOException.class,
                        ApplicationException.class,
                        CustomExceptionType.FILE_IO_EXCEPTION
                )
        );
    }

    public static Stream<MultipartFile> parse_Ok() throws IOException {
        return Stream.of(
                TestGenerator.multipartFile()
        );
    }


}
