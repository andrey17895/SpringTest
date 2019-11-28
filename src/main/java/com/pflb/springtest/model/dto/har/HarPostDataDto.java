package com.pflb.springtest.model.dto.har;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class HarPostDataDto {

    @NonNull
    private String text;

    @NonNull
    private List<HarParamsDto> params;

    public Map<String, String> getParamsMap() {
        return params.stream().collect(Collectors.toMap(HarParamsDto::getName, HarParamsDto::getValue));
    }
}
