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

    public static TestProfile entity_id_null() {
        return TestProfile.builder()
                .id(null)
                .requests(Collections.singletonList(
                    RequestProvider.entity_yaru_get_id_null()
                ))
                .build();
    }
    public static TestProfile entity_id_1() {
        return TestProfile.builder()
                .id(1L)
                .requests(Collections.singletonList(
                    RequestProvider.entity_yaru_get_id_null()
                ))
                .build();
    }
    public static TestProfile entity_id_2() {
        return TestProfile.builder()
                .id(2L)
                .requests(Collections.singletonList(
                    RequestProvider.entity_yaru_get_id_null()
                ))
                .build();
    }

    public static TestProfile entity_valid_post_with_body() {
        return TestProfile.builder()
                .id(null)
                .requests(Collections.singletonList(
                    RequestProvider.entity_valid_post_with_body()
                ))
                .build();
    }
}
