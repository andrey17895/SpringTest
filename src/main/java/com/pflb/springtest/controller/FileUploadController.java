package com.pflb.springtest.controller;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.service.IHistoryFileService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    private IHistoryFileService historyFileService;

    @Autowired
    public FileUploadController(IHistoryFileService historyFileService) {
        this.historyFileService = historyFileService;
    }

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public HistoryFileDto uploadFile(@NotNull @RequestParam(value = "file")MultipartFile file) {
        return historyFileService.processFile(file);
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
