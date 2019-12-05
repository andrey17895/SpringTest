package com.pflb.springtest.service;

import com.pflb.springtest.jms.consumer.RabbitMessagingListener;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.dto.har.HarEntryDto;
import com.pflb.springtest.model.dto.har.HarRequestDto;
import com.pflb.springtest.model.entity.Request;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.repository.TestProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListenerServiceImpl implements IListenerService {

    private TestProfileRepository testProfileRepository;

    private final Logger logger = LoggerFactory.getLogger(RabbitMessagingListener.class);

    @Autowired
    public ListenerServiceImpl(TestProfileRepository testProfileRepository) {
        this.testProfileRepository = testProfileRepository;
    }

    @Override
    public void process(HarDto message) {
        TestProfile testProfile = new TestProfile();
        List<HarEntryDto> entries = message.getLog().getEntries();
        List<Request> requestEntities = entries.stream()
                .map(entry -> {
                    HarRequestDto harRequestDto = entry.getRequest();
                    return Request.builder()
                            .url(harRequestDto.getUrl())
                            .body  (harRequestDto.getPostData() != null ? harRequestDto.getPostData().getText() : null)
                            .params(harRequestDto.getPostData() != null ? harRequestDto.getPostData().getParamsMap() : null)
                            .headers(harRequestDto.getHeadersMap())
                            .method(harRequestDto.getMethod())
                            .testProfile(testProfile)
                            .build();
                }).collect(Collectors.toList());
        testProfile.setRequests(requestEntities);
        testProfileRepository.save(testProfile);
    }
}
