package com.pflb.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HarLogDto {
    @NonNull
    private List<HarEntryDto> entries;
    private String version;
}
