package com.pflb.springtest.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.service.IParserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Log4j2
public class HarParserService implements IParserService {

    private ObjectMapper objectMapper;


    @Autowired
    public HarParserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public HarDto parse(MultipartFile file) {
        try {

            return objectMapper.readValue(file.getInputStream(), HarDto.class);

        } catch (JsonMappingException ex) {

            log.error("Invalid har structure", ex);
            throw new ApplicationException(CustomExceptionType.JSON_MAPPING_EXCEPTION);

        } catch (JsonProcessingException ex) {

            log.error("Har parsing error", ex);
            throw new ApplicationException(CustomExceptionType.JSON_PROCESSING_EXCEPTION);

        } catch (IOException ex) {

            log.error("File exception", ex);
            throw new ApplicationException(CustomExceptionType.FILE_IO_EXCEPTION);
        }
    }
}
