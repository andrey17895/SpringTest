package com.pflb.springtest.model.exception;

public enum CustomExceptionType {
        TEST_PROFILE_NOT_FOUND(404, "Test Profile not found"),
        REQUEST_NOT_FOUND(404, "Request not found"),
        JSON_MAPPING_EXCEPTION(400, "Json mapping exception"),
        JSON_PROCESSING_EXCEPTION(400, "Json processing exception"),
        FILE_IO_EXCEPTION(500, "File IO exception"),
        HTTP_MESSAGE_NOT_READABLE(400, "HTTP message not readable");

        private int statusCode;
        private String message;

        CustomExceptionType(int statusCode, String message){
                this.statusCode = statusCode;
                this.message = message;
        }
}
