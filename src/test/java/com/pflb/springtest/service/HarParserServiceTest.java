package com.pflb.springtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.service.impl.HarParserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HarParserServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private HarParserService harParserService;

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HapParserServiceArgs#parse_thenReturnDto")
    @DisplayName("Valid Har")
    void parse_thenReturnDto_whenValidJson(MultipartFile file, HarDto harDto) throws IOException {

        when(objectMapper.readValue(any(InputStream.class), eq(HarDto.class))).thenReturn(harDto);

        HarDto actualDto = harParserService.parse(file);

        assertEquals(harDto, actualDto);
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HapParserServiceArgs#parse_thenThrowApplicationException")
    @DisplayName("Catch exceptions")
    void parse_thenThrowApplicationException_whenInvalidJson(
            MultipartFile file,
            Class<Throwable> mapperException,
            Class<ApplicationException> exceptionClass,
            CustomExceptionType exceptionType
    ) throws IOException {

        when(objectMapper.readValue(any(InputStream.class), eq(HarDto.class))).thenThrow(mapperException);

        ApplicationException ex = assertThrows(
                    exceptionClass,
                    ()->harParserService.parse(file)
        );

        assertEquals(exceptionType, ex.getType());
    }



}