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
    public static HarDto dto_good_har() {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/sintetic_good_get.json"), HarDto.class);
    }

    @SneakyThrows
    public static HarDto dto_minimal_valid() {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/har_valid_minimal.json"), HarDto.class);
    }
    public static MultipartFile multipart_good_har() throws IOException {

        String resource = "/har/sintetic_good_get.json";

        InputStream inputFile = HarDtoProvider.class.getResourceAsStream(resource);

        return new MockMultipartFile("file", resource, "multipart/form-data", inputFile);
    }

}
