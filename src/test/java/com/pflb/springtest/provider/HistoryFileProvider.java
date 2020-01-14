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

//    public static HistoryFileDto dto(String browser, String version, String harFile) throws IOException {
//        return HistoryFileDto.builder()
//                .browser(browser)
//                .name(harFile)
//                .version(version)
//                .content(HarDtoProvider.dto_valid_empty_body())
//                .uploadTime(LocalDateTime.MIN)
//                .build();
//    }
//
//    public static HistoryFileDto dto() throws IOException {
//        return dto("firefox", "1.2", "HarFile");
//    }
//
//    public static HistoryFile entity(Long id, String browser, String version, String harFile) throws IOException {
//        return HistoryFile.builder()
//                .id(id)
//                .browser(browser)
//                .name(harFile)
//                .version(version)
//                .content(HarDtoProvider.dto_valid_empty_body())
//                .uploadTime(LocalDateTime.MIN)
//                .build();
//    }
//
//    public static HistoryFile entity() throws IOException {
//        return entity(null, "firefox", "1.2", "HarFile");
//    }
}
