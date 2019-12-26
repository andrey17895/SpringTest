package com.pflb.springtest.controller;

import com.pflb.springtest.model.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(JsonParsingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationExceptionDto parsingExceptionHandler(ApplicationException ex) {
        return ApplicationExceptionDto.builder().type(ex.getType()).build();
    }

    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApplicationExceptionDto applicationExceptionHandler(ApplicationException ex) {
        return ApplicationExceptionDto.builder().type(ex.getType()).build();
    }

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationExceptionDto resourceNotFoundHandler(ResourceNotFoundException ex) {
        return ApplicationExceptionDto.builder()
                .type(ex.getType())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationExceptionDto httpMessageNotReadableHandler() {
        return ApplicationExceptionDto.builder()
                .type(CustomExceptionType.HTTP_MESSAGE_NOT_READABLE)
                .build();
    }
}
