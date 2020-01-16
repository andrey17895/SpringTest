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

    public static HarDto dto_valid_har() throws IOException {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/sintetic_good_get.json"), HarDto.class);
    }

    public static HarDto dto_valid_with_body() throws IOException {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/har_valid_with_body.json"), HarDto.class);
    }

    public static HarDto dto_valid_empty_body() throws IOException {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/har_valid_minimal.json"), HarDto.class);
    }

    public static HarDto dto_valid_without_browser() throws IOException {

        return mapper.readValue(HarDto.class.getResourceAsStream("/har/har_valid_without_browser.json"), HarDto.class);
    }

    public static MultipartFile multipart_valid_without_browser() throws IOException {

        InputStream inputFile = HarDtoProvider.class.getResourceAsStream("/har/har_valid_without_browser.json");

        return new MockMultipartFile("file", "/har/har_valid_without_browser.json", "multipart/form-data", inputFile);
    }

    public static HarDto dto_invalid_no_log(){

        return HarDto.builder().log(null).build();
    }

    public static MultipartFile multipart_invalid_no_log() throws IOException {

        InputStream inputFile = HarDtoProvider.class.getResourceAsStream("/har/invalid_har_no_log.har");

        return new MockMultipartFile("file", "/har/invalid_har_no_log.har", "multipart/form-data", inputFile);
    }

    public static MultipartFile multipart_valid_har() throws IOException {

        InputStream inputFile = HarDtoProvider.class.getResourceAsStream("/har/sintetic_good_get.json");

        return new MockMultipartFile("file", "/har/sintetic_good_get.json", "multipart/form-data", inputFile);
    }

}
