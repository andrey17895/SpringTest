package com.pflb.springtest.model.dto.har;

import lombok.*;


@Data
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
public class HarEntryDto {
    @NonNull
    private HarRequestDto request;
}
