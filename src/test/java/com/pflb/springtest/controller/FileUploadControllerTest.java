package com.pflb.springtest.controller;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.service.IHistoryFileService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Disabled
class FileUploadControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private IHistoryFileService historyFileService;


    @Test
    void uploadFile() throws Exception {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        parameters.add("file", new ClassPathResource("har/sintetic_good_get.json"));

        ResponseEntity<HistoryFileDto> response = testRestTemplate.postForEntity("/uploadFile", parameters, HistoryFileDto.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), isA(HistoryFileDto.class));
        //TODO дополнить валидацию ответа
    }

    @Test
    void getFiles() throws Exception {
        ResponseEntity<List<HistoryFileDto>> response = testRestTemplate.exchange("/uploadFile", HttpMethod.GET, null, new ParameterizedTypeReference<List<HistoryFileDto>>(){});
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    void deleteAll() throws Exception {

        ResponseEntity<String> response = testRestTemplate.exchange("/uploadFile", HttpMethod.DELETE, null, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}