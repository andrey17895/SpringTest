package com.pflb.springtest.service.impl;

import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.entity.HistoryFile;
import com.pflb.springtest.repository.HistoryFileRepository;
import com.pflb.springtest.service.IHistoryFileService;
import com.pflb.springtest.service.IParserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HistoryFileService implements IHistoryFileService {


    private HistoryFileRepository fileRepository;
    private ModelMapper mapper;
    private JmsProducer jmsProducer;
    private IParserService harParserService;

    @Autowired
    public HistoryFileService(HistoryFileRepository fileRepository, ModelMapper mapper, JmsProducer jmsProducer, IParserService harParserService) {
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.jmsProducer = jmsProducer;
        this.harParserService = harParserService;
    }

    @Override
    public HistoryFileDto processFile(MultipartFile file) {
        HarDto harDto = harParserService.parse(file);
        HistoryFile historyFile = HistoryFile.builder()
                .name("HarFile")
                .content(harDto)
                .uploadTime(new Date())
                .version(harDto.getLog().getVersion())
                .browser(harDto.getLog().getBrowser() != null ? harDto.getLog().getBrowser().getName() : null)
                .build();
        HistoryFile response = fileRepository.save(historyFile);
        sendJms(response.getContent());
        return mapper.map(response, HistoryFileDto.class);

    }

    private void sendJms(HarDto message) {
        jmsProducer.sendMessage(message);
    }

    @Override
    public Collection<HistoryFileDto> getAllFiles() {
        Iterable<HistoryFile> fileEntities = fileRepository.findAll();
        return StreamSupport.stream(fileEntities.spliterator(), false)
                .map(entity -> mapper.map(entity, HistoryFileDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllFiles() {
        fileRepository.deleteAll();
    }

}
