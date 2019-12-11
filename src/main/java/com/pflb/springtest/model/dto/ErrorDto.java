package com.pflb.springtest.model.dto;

import com.pflb.springtest.model.exception.CustomExceptionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    CustomExceptionType type;
    Long id;
}
