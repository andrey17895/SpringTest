package com.pflb.SpringTest.messaging;

import com.pflb.SpringTest.data.entities.TestProfile;
import com.pflb.SpringTest.data.repositories.RequestRepository;
import com.pflb.SpringTest.data.repositories.TestProfileRepository;
import com.pflb.SpringTest.parser.HarParserService;
import com.pflb.SpringTest.parser.wrapers.EntryWrapper;
import com.pflb.SpringTest.parser.wrapers.HarWrapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
        if (harWrapper.isPresent()) {
            List<EntryWrapper> entries = harWrapper.get().getLog().getEntries();
            TestProfile testProfile = new TestProfile(
                    entries.stream()
                            .map(EntryWrapper::getRequest)
                            .collect(Collectors.toList())
            );
//            requestRepository.saveAll(testProfile.getRequests());
            try {
                testProfile.getRequests().forEach(x->x.setTestProfile(testProfile));
                testProfileRepository.save(testProfile);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
