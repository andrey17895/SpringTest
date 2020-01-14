package com.pflb.springtest.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.springtest.model.dto.har.HarDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class HarDtoProvider {

    private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static HarDto dto_good_har() throws IOException {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/sintetic_good_get.json"), HarDto.class);
    }

    public static HarDto dto_valid_empty_body() throws IOException {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/har_valid_minimal.json"), HarDto.class);
    }

    public static HarDto dto_invalid_no_log(){

        return HarDto.builder().log(null).build();
    }

    public static MultipartFile multipart_invalid_no_log() throws IOException {
        String resource = "/har/invalid_har_no_log.har";

        InputStream inputFile = HarDtoProvider.class.getResourceAsStream(resource);

        return new MockMultipartFile("file", resource, "multipart/form-data", inputFile);
    }

    public static MultipartFile multipart_valid_har() throws IOException {

        String resource = "/har/sintetic_good_get.json";

        InputStream inputFile = HarDtoProvider.class.getResourceAsStream(resource);

        return new MockMultipartFile("file", resource, "multipart/form-data", inputFile);
    }

}
