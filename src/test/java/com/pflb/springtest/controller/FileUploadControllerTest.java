package com.pflb.springtest.controller;

import com.pflb.springtest.service.IHistoryFileService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Disabled
@AutoConfigureMockMvc
class FileUploadControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private IHistoryFileService historyFileService;
    @Autowired
    private MockMvc mockMvc;


    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HistoryFileControllerArgs#uploadFile_thenReturnDto")
    void uploadFile_thenReturnDto_whenValid(String filePath) throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", this.getClass().getResourceAsStream(filePath));

        mockMvc.perform(
                    multipart("/uploadFile")
                        .file(file)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.version", is("1.2")))
                .andExpect(jsonPath("$.browser", is("Firefox")));
    }

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HistoryFileControllerArgs#uploadFile_thenReturnErrorDto")
    void uploadFile_thenReturnErrorDto_whenInvalid(String filePath, String expectedExceptionType) throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", this.getClass().getResourceAsStream(filePath));

        mockMvc.perform(
                multipart("/uploadFile")
                        .file(file)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type", is(expectedExceptionType)));
    }

    @Test
    void getFiles() throws Exception {
        mockMvc.perform(get("/uploadFile"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(delete("/uploadFile"))
                .andExpect(status().isOk());
    }
}