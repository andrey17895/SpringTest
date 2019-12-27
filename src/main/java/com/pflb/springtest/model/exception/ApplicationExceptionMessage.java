package com.pflb.springtest.model.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationExceptionMessage {
    String type;
}
