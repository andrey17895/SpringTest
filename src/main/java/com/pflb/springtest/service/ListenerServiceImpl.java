package com.pflb.springtest.service;

import com.pflb.springtest.dto.HarDto;
import com.pflb.springtest.dto.HarEntryDto;
import com.pflb.springtest.dto.HarRequestDto;
import com.pflb.springtest.entity.RequestEntity;
import com.pflb.springtest.entity.TestProfileEntity;
import com.pflb.springtest.jms.consumer.RabbitMessagingListener;
import com.pflb.springtest.repository.TestProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListenerServiceImpl implements ListenerService {

    private TestProfileRepository testProfileRepository;

    private final Logger logger = LoggerFactory.getLogger(RabbitMessagingListener.class);

    @Autowired
    public ListenerServiceImpl(TestProfileRepository testProfileRepository) {
        this.testProfileRepository = testProfileRepository;
    }

    @Override
    public void process(HarDto message) {
        TestProfileEntity testProfileEntity = new TestProfileEntity();
        List<HarEntryDto> entries = message.getLog().getEntries();
        List<RequestEntity> requestEntities = entries.stream()
                .map(entry -> {
                    HarRequestDto harRequestDto = entry.getRequest();
                    return RequestEntity.builder()
                            .url(harRequestDto.getUrl())
                            .body  (harRequestDto.getPostData() != null ? harRequestDto.getPostData().getText() : null)
                            .params(harRequestDto.getPostData() != null ? harRequestDto.getPostData().getParamsMap() : null)
                            .headers(harRequestDto.getHeadersMap())
                            .method(harRequestDto.getMethod())
                            .testProfile(testProfileEntity)
                            .build();
                }).collect(Collectors.toList());
        testProfileEntity.setRequests(requestEntities);
        testProfileRepository.save(testProfileEntity);
    }
}
