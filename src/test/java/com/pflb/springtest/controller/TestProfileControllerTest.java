package com.pflb.springtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
@Disabled
class TestProfileControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TestProfileController testProfileController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Rollback
    void postTestProfile() throws IOException {
        HttpEntity<String> entity = getResourceAsHttpEntity("/testprofile/testprofile.json");
        ResponseEntity<TestProfileDto> response = testRestTemplate.postForEntity("/testProfile", entity, TestProfileDto.class);
        //TODO Добавить валидацию ответа
    }

    @Test
    void getAllProfiles() {
//        ResponseEntity<Collection<TestProfile>> response = testRestTemplate.getForEntity("/testProfile", new ParameterizedTypeReference<List<TestProfile>>(){});
        ResponseEntity<Collection<TestProfile>> response = testRestTemplate.exchange("/testProfile", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<TestProfile>>(){});
        //TODO Добавить валидацию ответа


    }

    @Test
    void getTestProfileById() {
    }

    @Test
    @Rollback
    void putTestProfile() {
        HttpEntity<String> entity = getResourceAsHttpEntity("/testprofile/testprofile.json");
        ResponseEntity<TestProfileDto> response = testRestTemplate.postForEntity("/testProfile", entity, TestProfileDto.class);
        //TODO Добавить валидацию ответа
    }

    @Test
    void deleteAll() {
    }

    @Test
    void delete() {
    }

    private HttpEntity<String> getResourceAsHttpEntity(String resource) {
        InputStreamReader is = new InputStreamReader(this.getClass().getResourceAsStream(resource));
        BufferedReader br = new BufferedReader(is);
        String requestJson = br.lines().collect(Collectors.joining());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(requestJson, headers);
    }
}