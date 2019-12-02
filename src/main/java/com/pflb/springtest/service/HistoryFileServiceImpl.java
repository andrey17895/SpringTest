package com.pflb.springtest.service;

import com.pflb.springtest.dto.HarDto;
import com.pflb.springtest.dto.HistoryFileDto;
import com.pflb.springtest.entity.HistoryFileEntity;
import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.model.UnableToParceHarException;
import com.pflb.springtest.repository.HistoryFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HistoryFileServiceImpl implements HistoryFileService {


    private HistoryFileRepository fileRepository;
    private ModelMapper mapper;
    private JmsProducer jmsProducer;
    private HarParserService harParserService;

    @Autowired
    public HistoryFileServiceImpl(HistoryFileRepository fileRepository, ModelMapper mapper, JmsProducer jmsProducer, HarParserService harParserService) {
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.jmsProducer = jmsProducer;
        this.harParserService = harParserService;
    }

    @Override
    public HistoryFileDto processFile(String content) {
        Optional<HarDto> harDto = harParserService.parse(content);

        if (harDto.isPresent()) {
            HistoryFileEntity historyFileEntity = HistoryFileEntity.builder()
                    .name("HarFile")
                    .content(harDto.get())
                    .uploadTime(new Date())
                    .version(harDto.get().getLog().getVersion())
                    .browser(harDto.get().getLog().getBrowser() != null ? harDto.get().getLog().getBrowser().getName() : null)
                    .build();
            HistoryFileEntity response = fileRepository.save(historyFileEntity);
            sendJms(response.getContent());
            return mapper.map(response, HistoryFileDto.class);
        } else {
            throw new UnableToParceHarException(content);
        }
    }

    private void sendJms(HarDto message) {
        jmsProducer.sendMessage(message);
    }

    @Override
    public Iterable<HistoryFileDto> getAllFiles() {
        Iterable<HistoryFileEntity> fileEntities = fileRepository.findAll();
        return StreamSupport.stream(fileEntities.spliterator(), false)
                .map(entity -> mapper.map(entity, HistoryFileDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllFiles() {
        fileRepository.deleteAll();
    }

}
