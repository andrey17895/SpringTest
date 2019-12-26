package com.pflb.springtest.controller;

import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.service.IHistoryFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class FileUploadController {

    private IHistoryFileService historyFileService;

    @Autowired
    public FileUploadController(IHistoryFileService historyFileService) {
        this.historyFileService = historyFileService;
    }

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public HistoryFileDto uploadFile(@NotNull @RequestParam(value = "file")MultipartFile file) {
        return historyFileService.processFile(file);
    }

    @GetMapping(value = "/uploadFile")
    @ResponseBody
    public List<HistoryFileDto> getFiles() {
        return historyFileService.getAllFiles();
    }

    @DeleteMapping(value = "/uploadFile")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteAll() {
        historyFileService.deleteAllFiles();
    }

}
