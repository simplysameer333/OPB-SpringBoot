package com.banking.opb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

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
