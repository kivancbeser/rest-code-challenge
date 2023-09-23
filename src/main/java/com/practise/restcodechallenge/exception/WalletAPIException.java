package com.practise.restcodechallenge.exception;

import org.springframework.http.HttpStatus;

public class WalletAPIException extends RuntimeException {
    private final HttpStatus httpStatus;

    public WalletAPIException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
