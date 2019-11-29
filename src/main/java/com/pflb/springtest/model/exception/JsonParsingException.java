package com.pflb.springtest.model.exception;

public class JsonParsingException extends ApplicationException {

    public JsonParsingException(CustomExceptionType type) {
        super(type);
    }
}
