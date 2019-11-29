package com.pflb.springtest.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HarHeaderDto {

    @NonNull
    private String name;

    @NonNull
    private String value;
}
