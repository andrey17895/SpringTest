package com.pflb.springtest.generator;

import com.pflb.springtest.model.dto.har.*;
import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.dto.profile.RequestDto;
import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.HistoryFile;
import com.pflb.springtest.model.entity.Request;
import com.pflb.springtest.model.entity.TestProfile;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestGenerator {

    public static Request requestEntity(Long id, String url) {
        return Request.builder()
                .id(id)
                .url(url)
                .body("body")
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }

    public static RequestDto requestDto(String url) {
        return RequestDto.builder()
                .url(url)
                .body("body")
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }

    public static HistoryFileDto historyFileDto(String url) {
        return HistoryFileDto.builder()
                .browser("firefox")
                .version("1.2")
                .name("HarFile")
                .content(
                        harDto(url, null)
                )
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFile historyFileEntity(Long id, String url) {
        return HistoryFile.builder()
                .id(id)
                .browser("firefox")
                .version("1.2")
                .name("HarFile")
                .content(
                        harDto(url, null)
                )
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static TestProfile testProfileEntity(Long id, String url, String body, Map<String, String> params) {
        TestProfile entity = new TestProfile();
        entity.setRequests(
                Collections.singletonList(
                        new Request(
                                id,
                                url,
                                body,
                                Collections.emptyMap(),
                                params,
                                HttpMethod.GET,
                                null,
                                entity
                        )
                )
        );
        return entity;
    }

    public static TestProfileDto testProfileDto(String url) {
        return TestProfileDto.builder()
                .requests(Collections.singletonList(requestDto(url)))
                .build();
    }

    public static HarDto harDto(String url, HarPostDataDto postDataDto) {
        return new HarDto(
                new HarLogDto(
                        Collections.singletonList(
                                new HarEntryDto(
                                        new HarRequestDto(
                                                url,
                                                Collections.emptyList(),
                                                postDataDto,
                                                HttpMethod.GET
                                        )

                                )
                        )
                )
        );
    }

    public static HarPostDataDto harPostDataDto (List<HarParamsDto> harParamsDtos) {
        return HarPostDataDto.builder().text("text").params(harParamsDtos).build();
    }

    public static MultipartFile multipartFile() throws IOException {
        InputStream inputFile = TestGenerator.class.getResourceAsStream("sintetic_good_get.json");
        return new MockMultipartFile("file", "sintetic_good_get.json", "multipart/form-data", inputFile);
    }

}
