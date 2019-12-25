package com.pflb.springtest.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql("/db/dbsetup.sql")
@Transactional
@Disabled
class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#getAllRequests_thenReturnDtoList")
    void getAllRequests_thenReturnDtoList(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/requests"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#getAllRequestsByTestProfileId_thenReturnDtoList")
    void getAllRequestsByTestProfileId_thenReturnDtoList_whenExists(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/testProfile/1/requests"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#getAllRequestsByTestProfileId_thenReturnErrorDto")
    void getAllRequestsByTestProfileId_thenReturnErrorDto_whenNotExists(String expectedType) throws Exception {
        mockMvc.perform(get("/testProfile/404/requests"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type").value(expectedType));
    }

    @Test
    void deleteAll_thenReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/requests"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#getRequestById_thenReturnDto")
    void getRequestById_thenReturnDto_whenRequestExistsAndTestProfileExists(String expectedResponseBody) throws Exception {
        mockMvc.perform(get("/testProfile/1/requests/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#getRequestById_thenReturnErrorDto")
    void getRequestById_thenReturnErrorDto_whenTestProfileExistsOrRequestNotExists(long testProfileId, long requestId, String expectedType) throws Exception {
        mockMvc.perform(get("/testProfile/{testProfileId}/requests/{requestsId}", testProfileId, requestId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type").value(expectedType));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#request_thenReturnDto")
    @Rollback
    void postRequest_thenReturnDto_whenValid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(post("/testProfile/1/requests").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#postRequest_thenReturnErrorDto")
    @Rollback
    void postRequest_thenReturnErrorDto_whenInvalid(long testProfileId, String requestBody, HttpStatus expectedStatus, String expectedType) throws Exception {
        mockMvc.perform(
                post("/testProfile/{testProfileId}/requests", testProfileId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(jsonPath("$.type").value(expectedType));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#request_thenReturnDto")
    @Rollback
    void putRequest_thenReturnDto_whenValid(String requestBody, String expectedResponseBody) throws Exception {
        mockMvc.perform(put("/testProfile/1/requests/1").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseBody));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.RequestControllerArgs#putRequest_thenReturnErrorDto")
    @Rollback
    void putRequest_thenReturnErrorDto_whenInvalid(long testProfileId, long requestId, String requestBody, HttpStatus expectedStatus, String expectedType) throws Exception {
        mockMvc.perform(
                put("/testProfile/{testProfileId}/requests/{requestId}", testProfileId, requestId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(jsonPath("$.type").value(expectedType));
    }
}