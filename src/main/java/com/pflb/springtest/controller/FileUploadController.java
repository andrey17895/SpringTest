package com.pflb.springtest.controller;

import com.pflb.springtest.dto.HistoryFileDto;
import com.pflb.springtest.service.HistoryFileService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    private HistoryFileService historyFileService;

    @Autowired
    public FileUploadController(HistoryFileService historyFileService) {
        this.historyFileService = historyFileService;
    }

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public HistoryFileDto uploadFile(@NotNull @RequestParam(value = "file")MultipartFile file) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(file.getInputStream());
        String fileContent = new BufferedReader(streamReader).lines().collect(Collectors.joining());
        HistoryFileDto historyFile = HistoryFileDto.builder()
                .content(fileContent)
                .name(file.getName())
                .uploadTime(new Date())
                .build();
        historyFileService.processFile(historyFile);
        return historyFile;
    }

    @GetMapping(value = "/uploadFile")
    @ResponseBody
    public Iterable<HistoryFileDto> getFiles() {
        return historyFileService.getAllFiles();
    }

    @DeleteMapping(value = "uploadFile")
    public void deleteAll() {
        historyFileService.deleteAllFiles();
    }

}
