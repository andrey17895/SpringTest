package com.pflb.springtest.model.dto.profile;

import com.pflb.springtest.model.dto.har.HarDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class HistoryFileDto {

    private String name;

    @Type(type = "jsonb")
    private HarDto content;

    private LocalDateTime uploadTime;

    private String browser;

    private String version;
}