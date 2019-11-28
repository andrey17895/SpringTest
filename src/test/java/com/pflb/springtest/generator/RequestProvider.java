package com.pflb.springtest.generator;

import com.pflb.springtest.model.dto.profile.RequestDto;
import com.pflb.springtest.model.entity.Request;
import org.springframework.http.HttpMethod;

import java.util.Collections;
import java.util.Map;

public class RequestProvider {

    public static RequestDto dto(String body, Map<String, String> headers, Map<String, String> params, HttpMethod method, String url) {
        return RequestDto.builder()
                .url(url)
                .body(body)
                .headers(headers)
                .params(params)
                .method(method)
                .build();
    }

    public static RequestDto dto(String body ,Map<String, String> params) {
        return dto(body, Collections.emptyMap(), params, HttpMethod.GET, "ya.ru");
    }

    public static Request entity(Long id, String body, Map<String, String> headers, Map<String, String> params, HttpMethod method, String url) {
        return Request.builder()
                .id(id)
                .url(url)
                .body(body)
                .headers(headers)
                .params(params)
                .method(method)
                .build();
    }

    public static Request entity(Long id, String body, Map<String, String> params, String url) {
        return entity(id, body, Collections.emptyMap(), params, HttpMethod.GET, url);
    }
}
