package com.pflb.springtest.model.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(CustomExceptionType type, Long id) {
        super(type.name() + " (id=" + id +")");
    }

    public ResourceNotFoundException(CustomExceptionType type) {
        super(type.name());
    }

}