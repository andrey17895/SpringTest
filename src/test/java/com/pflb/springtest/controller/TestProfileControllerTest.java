package com.pflb.springtest.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class TestProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnDto")
    @Rollback
    void postTestProfile_thenReturnDto_whenValid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(post("/testProfile").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnErrorDto")
    @Rollback
    void postTestProfile_thenReturnErrorDto_whenInalid(String requestBody,
                                                       String expectedResponseBody) throws Exception {

        mockMvc.perform(post("/testProfile").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnDto")
    @Rollback
    void putTestProfile_thenReturnDto_whenTestProfileExistsAndRequestValid(
            String requestBody,
            String expectedResponseBody
    ) throws Exception {

        mockMvc.perform(put("/testProfile/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnErrorDto")
    @Rollback
    void putTestProfile_thenReturnDto_whenTestProfileExistsAndRequestInalid(String requestBody,
                                                                            String expectedResponseBody) throws Exception {
        mockMvc.perform(put("/testProfile/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#getAllProfiles_thenReturnDtoList")
    @Rollback
    void getAllProfiles_thenReturnDtoList(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/testProfile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(expectedResponseBody));
    }

    @Test
    @Rollback
    void getTestProfileById_thenReturnErrorDto_whenTestProfileNotExists() throws Exception {
        mockMvc.perform(get("/testProfile/404"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is("Test Profile not found")));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#getTestProfileById_thenReturnDto")
    @Rollback
    void getTestProfileById_thenReturnDto_whenTestProfileExists(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/testProfile/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#putTestProfile_thenReturnErrorDto")
    @Rollback
    void putTestProfile_thenReturnErrorDto_whenTestProfileNotExists(String requestBody,
                                                                    String expectedExceptionType) throws Exception {
        mockMvc.perform(
                put("/testProfile/404")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is(expectedExceptionType)));
    }
}