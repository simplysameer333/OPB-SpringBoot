package com.banking.opb.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ApiRequestException extends RuntimeException {
    @Getter
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
    }

    public ApiRequestException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
