package com.pflb.springtest.service.impl;

import com.pflb.springtest.jms.producer.RabbitJmsProducer;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.dto.har.HarEntryDto;
import com.pflb.springtest.model.dto.har.HarRequestDto;
import com.pflb.springtest.model.entity.Request;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.IListenerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ListenerService implements IListenerService {

    private TestProfileRepository testProfileRepository;

    private RabbitJmsProducer jmsProducer;


    @Autowired
    public ListenerService(TestProfileRepository testProfileRepository) {
        this.testProfileRepository = testProfileRepository;
    }

    @Override
    public void process(HarDto message) {
        try {

            TestProfile testProfile = new TestProfile();

            List<HarEntryDto> entries = message.getLog().getEntries();

            List<Request> requestEntities = entries.stream()
                    .map(entry -> {
                        HarRequestDto harRequestDto = entry.getRequest();
                        return Request.builder()
                                .url(harRequestDto.getUrl())
                                .body(harRequestDto.getPostData() != null ? harRequestDto.getPostData().getText() : null)
                                .params(harRequestDto.getPostData() != null ? harRequestDto.getPostData().getParamsMap() : Collections.emptyMap())
                                .headers(harRequestDto.getHeadersMap())
                                .method(harRequestDto.getMethod())
                                .testProfile(testProfile)
                                .build();
                    }).collect(Collectors.toList());
            testProfile.setRequests(requestEntities);

            testProfileRepository.save(testProfile);

        } catch (NullPointerException ex) {

            log.error("Invalid message. Probably missed critical fields\n" + message.toString(), ex);
            throw new ApplicationException(CustomExceptionType.BAD_HAR_FILE);
        }
    }
}
