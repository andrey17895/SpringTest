package com.pflb.springtest.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.springtest.model.dto.har.HarDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class HarDtoProvider {

    private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @SneakyThrows
    public static HarDto dtoFromFile(String resource) {
        return mapper.readValue(HarDto.class.getResourceAsStream(resource), HarDto.class);
    }

    public static MultipartFile multipartFile(String resource) throws IOException {
        InputStream inputFile = HarDtoProvider.class.getResourceAsStream(resource);
        return new MockMultipartFile("file", resource, "multipart/form-data", inputFile);
    }

}
