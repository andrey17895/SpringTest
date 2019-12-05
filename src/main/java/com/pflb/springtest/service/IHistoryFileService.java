package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;

import java.util.Collection;

public interface IHistoryFileService {
    HistoryFileDto processFile(String content);

    Collection<HistoryFileDto> getAllFiles();

    void deleteAllFiles();
}
