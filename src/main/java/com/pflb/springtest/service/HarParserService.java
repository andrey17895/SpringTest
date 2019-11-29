package com.pflb.springtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.pflb.springtest.dto.HarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HarParserService {

    private ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(HarParserService.class);

    @Autowired
    public HarParserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<HarDto> parse(String message) {
        try {
            return Optional.of(objectMapper.readValue(message, HarDto.class));
        } catch (ValueInstantiationException exception) {
            logger.error("Invalid har structure", exception);
            return Optional.empty();
        } catch (JsonProcessingException exception) {
            logger.error("Har parsing error", exception);
            return Optional.empty();
        }
    }
}
