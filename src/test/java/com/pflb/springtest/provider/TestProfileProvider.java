package com.pflb.springtest.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;
import lombok.SneakyThrows;

import java.util.Collections;

public class TestProfileProvider {

    private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @SneakyThrows
    public static TestProfileDto dtoFromFile(String resource) {
        return mapper.readValue(TestProfileProvider.class.getResourceAsStream(resource), TestProfileDto.class);
    }

    public static TestProfileDto dto() {
        return TestProfileDto.builder()
                .requests(Collections.singletonList(
                        RequestProvider.dto(null, Collections.emptyMap(), "ya.ru")
                ))
                .build();
    }

    public static TestProfile entity(Long id) {
        return TestProfile.builder()
                .id(id)
                .requests(Collections.singletonList(
                    RequestProvider.entity(id, null, Collections.emptyMap(), "ya.ru")
                ))
                .build();
    }
}
