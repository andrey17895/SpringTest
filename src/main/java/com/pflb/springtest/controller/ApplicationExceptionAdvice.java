package com.pflb.springtest.controller;

import com.pflb.springtest.model.dto.ErrorDto;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.JsonParsingException;
import com.pflb.springtest.model.exception.ResourceNotFoundException;
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
    public ErrorDto parsingExceptionHandler(ApplicationException ex) {
        return ErrorDto.builder().type(ex.getType()).build();
    }

    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto applicationExceptionHandler(ApplicationException ex) {
        return ErrorDto.builder().type(ex.getType()).build();
    }

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto resourceNotFoundHandler(ResourceNotFoundException ex) {
        return ErrorDto.builder()
                .type(ex.getType())
                .id(ex.getId())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto httpMessageNotReadableHandler() {
        return ErrorDto.builder()
                .type(CustomExceptionType.HTTP_MESSAGE_NOT_READABLE)
                .build();
    }
}
