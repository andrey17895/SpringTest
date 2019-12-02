package com.pflb.springtest.model;

public class UnableToParceHarException extends RuntimeException {
    public UnableToParceHarException(String content) {
        super("Unable to parce json: " + content);
    }
}
