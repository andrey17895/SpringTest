package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface IHistoryFileService {
    HistoryFileDto processFile(MultipartFile file);

    Collection<HistoryFileDto> getAllFiles();

    void deleteAllFiles();
}
