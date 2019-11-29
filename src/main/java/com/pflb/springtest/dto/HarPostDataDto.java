package com.pflb.springtest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class HarPostDataDto {

    @NonNull
    private String text;

    @NonNull
    private List<HarParamsDto> params;

    public Map<String, String> getParamsMap() {
        return params.stream().collect(Collectors.toMap(HarParamsDto::getName, HarParamsDto::getValue));
    }
}
