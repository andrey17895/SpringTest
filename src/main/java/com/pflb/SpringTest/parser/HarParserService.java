package com.pflb.SpringTest.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.SpringTest.parser.wrapers.HarWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HarParserService {

    @Autowired
    private ObjectMapper objectMapper;

    public Optional<HarWrapper> parse(String message) {
        try {
            return Optional.of(objectMapper.readValue(message, HarWrapper.class));
        }
        catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            System.out.println();
            return Optional.empty();
        }
    }
}
