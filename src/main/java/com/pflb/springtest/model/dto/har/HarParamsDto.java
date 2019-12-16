package com.pflb.springtest.model.dto.har;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class HarParamsDto {

    @NonNull
    private String name;

    @NonNull
    private String value;


}
