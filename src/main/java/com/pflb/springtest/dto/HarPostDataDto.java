package com.pflb.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HarPostDataDto {

    private String text;

    private List<HarParamsDto> params;

    public Map<String, String> getParamsMap() {
        return params.stream().collect(Collectors.toMap(HarParamsDto::getName, HarParamsDto::getValue));
    }
}
