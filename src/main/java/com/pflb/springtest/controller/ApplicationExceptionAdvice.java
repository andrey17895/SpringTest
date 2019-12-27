package com.pflb.springtest.controller;

import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.ApplicationExceptionMessage;
import com.pflb.springtest.model.exception.CustomExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionMessage> applicationExceptionHandler(ApplicationException ex) {

        ApplicationExceptionMessage message = ApplicationExceptionMessage.builder()
                .type(ex.getType().getMessage())
                .build();

        return ResponseEntity
                .status(ex.getType().getStatusCode())
                .body(message);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationExceptionMessage httpMessageNotReadableHandler() {
        return ApplicationExceptionMessage.builder()
                .type(CustomExceptionType.HTTP_MESSAGE_NOT_READABLE.getMessage())
                .build();
    }
}
