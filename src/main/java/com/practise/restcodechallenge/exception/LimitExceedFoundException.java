package com.pixelbet.restcodechallenge.exception;

import org.springframework.http.HttpStatus;

public class LimitExceedFoundException extends WalletAPIException {
    private final HttpStatus httpStatus;

    public LimitExceedFoundException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
        this.httpStatus = HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
