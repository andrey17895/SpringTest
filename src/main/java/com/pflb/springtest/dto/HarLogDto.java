package com.pflb.springtest.dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class HarLogDto {
    @NonNull
    private List<HarEntryDto> entries;
    private String version;
}
