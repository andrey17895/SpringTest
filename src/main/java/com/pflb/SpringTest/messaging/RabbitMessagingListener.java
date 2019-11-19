package com.pflb.SpringTest.messaging;

import com.pflb.SpringTest.data.entities.Request;
import com.pflb.SpringTest.data.entities.TestProfile;
import com.pflb.SpringTest.data.repositories.RequestRepository;
import com.pflb.SpringTest.data.repositories.TestProfileRepository;
import com.pflb.SpringTest.parser.HarParserService;
import com.pflb.SpringTest.parser.wrapers.EntryWrapper;
import com.pflb.SpringTest.parser.wrapers.HarWrapper;
import com.pflb.SpringTest.parser.wrapers.RequestWrapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "messaging")
public class RabbitMessagingListener {

    @Getter
    @Setter
    private String queue;

    @Autowired
    private HarParserService harParserService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TestProfileRepository testProfileRepository;

    @RabbitListener(queues = "fileSend")
    public void recieve(String message) {
//        System.out.println(message);
        Optional<HarWrapper> harWrapper = harParserService.parse(message);
        try {
            if (harWrapper.isPresent()) {
                TestProfile testProfile = new TestProfile();
                List<EntryWrapper> entries = harWrapper.get().getLog().getEntries();
                List<Request> requests = entries.stream().map(entry -> {
                    RequestWrapper requestWrapper = entry.getRequest();
//                    System.out.println(requestWrapper);
                    Request request = new Request();
                    request.setUrl(requestWrapper.getUrl());
                    if(requestWrapper.getPostData() != null){
                        request.setBody(requestWrapper.getPostData().getText());
                        request.setParams(requestWrapper.getPostData().getParams());
                    }
                    request.setHeaders(requestWrapper.getHeaders());
                    request.setMethod(requestWrapper.getMethod());
                    request.setTestProfile(testProfile);
                    return request;
                }).collect(Collectors.toList());
                testProfile.setRequests(requests);
//            System.out.println(testProfile);
            testProfileRepository.save(testProfile);
            }
        }
        catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
