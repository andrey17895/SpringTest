package com.pflb.springtest.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TestProfileDto {

    private List<RequestDto> requests;

}
