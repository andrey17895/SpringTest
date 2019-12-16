package com.pflb.springtest.generator;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.entity.HistoryFile;

import java.time.LocalDateTime;

public class HistoryFileProvider {

    public static HistoryFileDto dto(String browser, String version, String harFile) {
        return HistoryFileDto.builder()
                .browser(browser)
                .name(harFile)
                .version(version)
                .content(HarDtoProvider.harDto(0, ""))
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFileDto dto() {
        return dto("firefox", "1.2", "HarFile");
    }

    public static HistoryFile entity(Long id, String browser, String version, String harFile) {
        return HistoryFile.builder()
                .id(id)
                .browser(browser)
                .name(harFile)
                .version(version)
                .content(HarDtoProvider.harDto(0, ""))
                .uploadTime(LocalDateTime.MIN)
                .build();
    }

    public static HistoryFile entity() {
        return entity(null, "firefox", "1.2", "HarFile");
    }
}
