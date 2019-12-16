package com.pflb.springtest.model.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class TestProfileDto {

    private List<RequestDto> requests;

}
