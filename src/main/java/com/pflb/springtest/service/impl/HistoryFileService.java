package com.pflb.springtest.service.impl;

import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.entity.HistoryFile;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.repository.HistoryFileRepository;
import com.pflb.springtest.service.IHistoryFileService;
import com.pflb.springtest.service.IParserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
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

        try {
            HarDto harDto = harParserService.parse(file);
            HistoryFile historyFile = HistoryFile.builder()
                    .name("HarFile")
                    .content(harDto)
                    .uploadTime(LocalDateTime.now())
                    .version(harDto.getLog().getVersion())
                    .browser(harDto.getLog().getBrowser() != null ? harDto.getLog().getBrowser().getName() : null)
                    .build();

            HistoryFile response = fileRepository.save(historyFile);

            jmsProducer.sendMessage(response.getContent());

            return mapper.map(response, HistoryFileDto.class);
        } catch (NullPointerException ex) {
            log.error("Null pointer exception while processing har file. Check uploaded file content", ex);
            throw new ApplicationException(CustomExceptionType.BAD_HAR_FILE);
        }
    }

    @Override
    public List<HistoryFileDto> getAllFiles() {

        List<HistoryFile> fileEntities = fileRepository.findAll();

        return mapper.map(fileEntities, new TypeToken<List<HistoryFileDto>>() {}.getType());
    }

    @Override
    public void deleteAllFiles() {
        fileRepository.deleteAll();
    }

}
