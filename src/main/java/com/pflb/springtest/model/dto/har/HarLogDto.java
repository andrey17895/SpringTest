package com.pflb.springtest.model.dto.har;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class HarLogDto {
    @NonNull
    private List<HarEntryDto> entries;
    private HarBrowserDto browser;
    private String version;
}
