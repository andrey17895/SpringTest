package com.pflb.SpringTest.data.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
public class Request {
    private String url;
    private String body;
    private Map<String, String> headers;
    private Map<String, String> params;
    private HttpMethod method;
    private Double perc;
}
