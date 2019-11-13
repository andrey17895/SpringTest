package com.pflb.SpringTest.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.SpringTest.data.entities.TestProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarParserService {

    @Autowired
    private ObjectMapper objectMapper;

    public TestProfile parse(String message) {
        return objectMapper.convertValue(message, TestProfile.class);
    }
}
