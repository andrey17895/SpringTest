package com.pflb.SpringTest;

import com.pflb.SpringTest.data.entities.HistoryFile;
import com.pflb.SpringTest.data.repositories.HistoryFileRepository;
import com.pflb.SpringTest.messaging.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    @Autowired
    private HistoryFileRepository historyFileRepository;

    @Autowired
    private MessagingService messagingService;

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam(value = "file")MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        InputStreamReader streamReader = new InputStreamReader(file.getInputStream());
        String fileContent = new BufferedReader(streamReader).lines().collect(Collectors.joining());
        HistoryFile historyFile = HistoryFile.builder()
                .content(fileContent)
                .name(file.getName())
                .uploadTime(new Date())
                .build();
        historyFileRepository.save(historyFile);
        messagingService.sendMessage(historyFile.getContent());
        return historyFile.toString();
    }

    @GetMapping(value = "/uploadFile")
    @ResponseBody
    public Iterable<HistoryFile> getFiles() {
        return historyFileRepository.findAll();
    }

}
