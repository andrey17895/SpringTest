package com.pflb.springtest.provider;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.entity.HistoryFile;

import java.io.IOException;
import java.time.LocalDateTime;

public class HistoryFileProvider {

    public static HistoryFileDto dto_firefox_v1_2() throws IOException {
        return HistoryFileDto.builder()
                .browser("firefox")
                .name("HarFile")
                .version("1.2")
                .content(HarDtoProvider.dto_valid_empty_body())
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFileDto dto_chrome_v1_2() throws IOException {
        return HistoryFileDto.builder()
                .browser("chrome")
                .name("HarFile")
                .version("1.2")
                .content(HarDtoProvider.dto_valid_empty_body())
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFile entity_firefox_v1_2_id_1() throws IOException {
        return HistoryFile.builder()
                .id(1L)
                .browser("firefox")
                .name("HarFile")
                .version("1.2")
                .content(HarDtoProvider.dto_valid_empty_body())
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFile entity_chrome_v1_2_id_2() throws IOException {
        return HistoryFile.builder()
                .id(2L)
                .browser("chrome")
                .name("HarFile")
                .version("1.2")
                .content(HarDtoProvider.dto_valid_empty_body())
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFileDto dto_valid_without_browser() throws IOException {
        return HistoryFileDto.builder()
                .browser(null)
                .name("HarFile")
                .version("1.2")
                .content(HarDtoProvider.dto_valid_without_browser())
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFile entity_valid_without_browser() throws IOException {
        return HistoryFile.builder()
                .id(1L)
                .browser(null)
                .name("HarFile")
                .version("1.2")
                .content(HarDtoProvider.dto_valid_without_browser())
                .uploadTime(LocalDateTime.MIN)
                .build();
    }
}
