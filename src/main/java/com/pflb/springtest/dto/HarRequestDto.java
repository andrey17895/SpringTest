package com.pflb.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HarRequestDto {
    private String url;

    private List<HarHeaderDto> headers;

    private HarPostDataDto postData;

    private HttpMethod method;

    public Map<String, String> getHeadersMap() {
        return headers.stream().collect(Collectors.toMap(HarHeaderDto::getName, HarHeaderDto::getValue));
    }

}
