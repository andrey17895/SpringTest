package com.pflb.springtest.model.dto.har;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HarBrowserDto {
    private String name;
    private String version;
}
