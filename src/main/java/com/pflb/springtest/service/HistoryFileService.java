package com.pflb.springtest.service;

import com.pflb.springtest.dto.HistoryFileDto;

public interface HistoryFileService {
    HistoryFileDto processFile(HistoryFileDto historyFileDto);

    Iterable<HistoryFileDto> getAllFiles();

    void deleteAllFiles();
}
