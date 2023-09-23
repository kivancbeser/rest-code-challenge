package com.practise.restcodechallenge.exception;

import java.util.Date;

import com.practise.restcodechallenge.model.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final String DEFAULT_ERR_MSG = "Internal server error occurred. Please contact to admin.";
    private final String BRS_ERR_MSG = "Please validate your request or contact to admin.";

    @ExceptionHandler(WalletAPIException.class)
    public ResponseEntity<Object> handleAPIException(WalletAPIException exception,
        WebRequest webRequest) {
        Response response = new Response();
        setError(webRequest, BRS_ERR_MSG, exception.getMessage(), response);
        response.setStatus(exception.getHttpStatus().name());
        response.setStatusCode(exception.getHttpStatus().value());
        exception.printStackTrace();
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception,
        WebRequest webRequest) {
        exception.printStackTrace();
        Response response = Response.internalError();
        setError(webRequest, DEFAULT_ERR_MSG, exception.getMessage(), response);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void setError(WebRequest webRequest, Object detailMessage, String exceptionMessage,
        Response response) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto()
            .setDetails(detailMessage)
            .setMessage(exceptionMessage)
            .setApi(webRequest.getDescription(false))
            .setTimestamp(new Date());
        response.setErrors(errorDetailsDto);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ErrorDetailsDto {

        private String message;
        private Object details;
        private String api;
        private Date timestamp;
    }

}


