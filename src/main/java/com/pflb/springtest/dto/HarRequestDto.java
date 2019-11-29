package com.pflb.springtest.dto;

import lombok.*;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class HarRequestDto {
    @NonNull
    private String url;

    @NonNull
    private List<HarHeaderDto> headers;

    private HarPostDataDto postData;

    @NonNull
    private HttpMethod method;

    public Map<String, String> getHeadersMap() {
        return headers.stream().collect(Collectors.toMap(HarHeaderDto::getName, HarHeaderDto::getValue));
    }

}
