package com.pflb.springtest.model.exception;

public class UnableToParceHarException extends RuntimeException {
    public UnableToParceHarException(String content) {
        super("Unable to parce json: " + content);
    }
}
