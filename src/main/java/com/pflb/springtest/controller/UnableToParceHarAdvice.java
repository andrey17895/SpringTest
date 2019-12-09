package com.pflb.springtest.controller;

import com.pflb.springtest.model.exception.UnableToParceHarException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnableToParceHarAdvice {
    @ResponseBody
    @ExceptionHandler(UnableToParceHarException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resourceNotFoundHandler(UnableToParceHarException exception) {
        return exception.getMessage();
    }
}
