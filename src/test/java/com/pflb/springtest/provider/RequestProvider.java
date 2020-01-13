package com.pflb.springtest.provider;

import com.pflb.springtest.model.dto.profile.RequestDto;
import com.pflb.springtest.model.entity.Request;
import org.springframework.http.HttpMethod;

import java.util.Collections;

public class RequestProvider {

    public static RequestDto dto_yaru_get() {
        return RequestDto.builder()
                .url("ya.ru")
                .body(null)
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }

    public static RequestDto dto_boomqio_get() {
        return RequestDto.builder()
                .url("boomq.io")
                .body(null)
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }
    public static Request entity_yaru_get_id_1() {
        return Request.builder()
                .id(1L)
                .url("ya.ru")
                .body(null)
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }

    public static Request entity_boomqio_get_id_2() {
        return Request.builder()
                .id(2L)
                .url("boomqio")
                .body(null)
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }

    public static Request entity_yaru_get_id_null() {
        return Request.builder()
                .id(null)
                .url("ya.ru")
                .body(null)
                .headers(Collections.emptyMap())
                .params(Collections.emptyMap())
                .method(HttpMethod.GET)
                .build();
    }
}
