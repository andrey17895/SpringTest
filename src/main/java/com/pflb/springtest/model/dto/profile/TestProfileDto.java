package com.pflb.springtest.model.dto.profile;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TestProfileDto {

    private List<RequestDto> requests;

}
