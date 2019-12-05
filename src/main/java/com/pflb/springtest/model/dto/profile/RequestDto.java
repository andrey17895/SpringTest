package com.pflb.springtest.model.dto.profile;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class RequestDto {

    private String url;

    private String body;

    private Map<String, String> headers;

    private Map<String, String> params;

    private HttpMethod method;

    private Double perc = 0D;

}

