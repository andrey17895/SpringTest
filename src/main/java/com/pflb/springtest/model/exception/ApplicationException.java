package com.pflb.springtest.model.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
public class ApplicationException extends RuntimeException {

    @Getter
    private CustomExceptionType type;

    public ApplicationException(CustomExceptionType type) {
        super("Application exception: " + type.name());
        this.type = type;
    }
}
