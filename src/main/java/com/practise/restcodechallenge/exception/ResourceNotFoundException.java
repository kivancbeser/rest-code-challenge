package com.pixelbet.restcodechallenge.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends WalletAPIException {
    private final HttpStatus httpStatus;

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
