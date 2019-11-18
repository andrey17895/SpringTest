package com.pflb.SpringTest.rest;

import com.pflb.SpringTest.data.entities.HistoryFile;
import com.pflb.SpringTest.data.repositories.HistoryFileRepository;
import com.pflb.SpringTest.messaging.sender.MessagingSender;
import com.pflb.SpringTest.parser.HarParserService;
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

    @Autowired
    private HarParserService harParserService;

    @Autowired
    private HistoryFileRepository historyFileRepository;

    @Autowired
    private MessagingSender messagingSender;

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public String uploadFile(@NotNull @RequestParam(value = "file")MultipartFile file) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(file.getInputStream());
        String fileContent = new BufferedReader(streamReader).lines().collect(Collectors.joining());
        HistoryFile historyFile = new HistoryFile();
        historyFile.setContent(fileContent);
        historyFile.setName(file.getName());
        historyFile.setUploadTime(new Date());
        historyFileRepository.save(historyFile);
        messagingSender.sendMessage(historyFile.getContent());
        return historyFile.toString();
    }

    @GetMapping(value = "/uploadFile")
    @ResponseBody
    public Iterable<HistoryFile> getFiles() {
        return historyFileRepository.findAll();
    }

}
