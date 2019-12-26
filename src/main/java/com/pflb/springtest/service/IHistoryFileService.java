package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHistoryFileService {
    HistoryFileDto processFile(MultipartFile file);

    List<HistoryFileDto> getAllFiles();

    void deleteAllFiles();
}
