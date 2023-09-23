package com.practise.restcodechallenge.exception;

import org.springframework.http.HttpStatus;

public class BadRequestFoundException extends WalletAPIException {
    private final HttpStatus httpStatus;

    public BadRequestFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
