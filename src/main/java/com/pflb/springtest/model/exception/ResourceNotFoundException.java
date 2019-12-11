package com.pflb.springtest.model.exception;

import lombok.Getter;

public class ResourceNotFoundException extends ApplicationException{

    @Getter
    private Long id;

    public ResourceNotFoundException(CustomExceptionType type, Long id) {
        super(type);
        this.id = id;
    }

    public ResourceNotFoundException(CustomExceptionType type) {
        super(type);
    }

}
