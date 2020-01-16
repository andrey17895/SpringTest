package com.pflb.springtest.controller;

import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.service.IHistoryFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JmsProducer jmsProducer;
    @SpyBean
    private IHistoryFileService historyFileService;


    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HistoryFileControllerArgs#uploadFile_thenReturnDto")
    @Rollback
    void uploadFile_thenReturnDto_whenValid(MockMultipartFile file) throws Exception {

        doNothing().when(jmsProducer).sendMessage(any(HarDto.class));

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
    @Rollback
    void uploadFile_thenReturnErrorDto_whenInvalid( MockMultipartFile file,
                                                    String expectedExceptionType) throws Exception {

        mockMvc.perform(
                multipart("/uploadFile")
                        .file(file)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type", is(expectedExceptionType)));
    }

    @Test
    @Rollback
    void getFiles() throws Exception {
        mockMvc.perform(get("/uploadFile"))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    void deleteAll() throws Exception {
        doNothing().when(historyFileService).deleteAllFiles();

        mockMvc.perform(delete("/uploadFile")).andExpect(status().isOk());
    }
}