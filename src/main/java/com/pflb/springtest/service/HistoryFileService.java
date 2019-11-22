package com.pflb.springtest.service;

import com.pflb.springtest.dto.HistoryFileDto;
import com.pflb.springtest.entity.HistoryFileEntity;
import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.repository.HistoryFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HistoryFileService {


    private HistoryFileRepository fileRepository;
    private ModelMapper mapper;
    private JmsProducer jmsProducer;

    @Autowired
    public HistoryFileService(HistoryFileRepository fileRepository, ModelMapper mapper, JmsProducer jmsProducer) {
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.jmsProducer = jmsProducer;
    }

    public HistoryFileDto processFile(HistoryFileDto historyFileDto) {
        HistoryFileEntity historyFileEntity = mapper.map(historyFileDto, HistoryFileEntity.class);
        HistoryFileEntity response = fileRepository.save(historyFileEntity);
        sendJms(response.getContent());
        return mapper.map(response, HistoryFileDto.class);
    }

    private void sendJms(String message) {
        jmsProducer.sendMessage(message);
    }

    public Iterable<HistoryFileDto> getAllFiles() {
        Iterable<HistoryFileEntity> fileEntities = fileRepository.findAll();
        return StreamSupport.stream(fileEntities.spliterator(), false)
                .map(entity -> mapper.map(entity, HistoryFileDto.class))
                .collect(Collectors.toList());
    }

    public void deleteAllFiles() {
        fileRepository.deleteAll();
    }

}
