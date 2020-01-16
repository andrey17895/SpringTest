package com.pflb.springtest.provider;

import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;

import java.util.Collections;

public class TestProfileProvider {


    public static TestProfileDto dto() {
        return TestProfileDto.builder()
                .requests(Collections.singletonList(
                        RequestProvider.dto_yaru_get()
                ))
                .build();
    }

    public static TestProfile entity(Long id) {
        return TestProfile.builder()
                .id(id)
                .requests(Collections.singletonList(
                    RequestProvider.entity_yaru_get_id_null()
                ))
                .build();
    }
}
