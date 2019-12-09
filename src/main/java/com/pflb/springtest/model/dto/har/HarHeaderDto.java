package com.pflb.springtest.model.dto.har;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class HarHeaderDto {

    @NonNull
    private String name;

    @NonNull
    private String value;
}
