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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql("/db/dbsetup.sql")
@Transactional
//@Disabled
class TestProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnDto")
    @Rollback
    void postTestProfile_thenReturnDto_whenValid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(post("/testProfile").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnErrorDto")
    @Rollback
    void postTestProfile_thenReturnErrorDto_whenInalid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(post("/testProfile").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnDto")
    @Rollback
    void putTestProfile_thenReturnDto_whenTestProfileExistsAndRequestValid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(put("/testProfile/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#testProfile_thenReturnErrorDto")
    @Rollback
    void putTestProfile_thenReturnDto_whenTestProfileExistsAndRequestInalid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(put("/testProfile/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#getAllProfiles_thenReturnDtoList")
    @Rollback
    void getAllProfiles_thenReturnDtoList(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/testProfile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(expectedResponseBody));
    }

    @Test
    @Rollback
    void getTestProfileById_thenReturnErrorDto_whenTestProfileNotExists() throws Exception {
        mockMvc.perform(get("/testProfile/404"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is("TEST_PROFILE_NOT_FOUND")));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#getTestProfileById_thenReturnDto")
    @Rollback
    void getTestProfileById_thenReturnDto_whenTestProfileExists(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/testProfile/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.TestProfileControllerArgs#putTestProfile_thenReturnErrorDto")
    @Rollback
    void putTestProfile_thenReturnErrorDto_whenTestProfileNotExists(String requestBody) throws Exception {
        mockMvc.perform(
                put("/testProfile/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is("TEST_PROFILE_NOT_FOUND")));
    }

    @Test
    @Rollback
    void deleteAll() throws Exception {
        mockMvc.perform(delete("/testProfile"))
                .andExpect(status().isOk());
    }
}