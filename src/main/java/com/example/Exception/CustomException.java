package com.example.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus httpStatus;
    public CustomException(String message,HttpStatus status){
        this.message=message;
        this.httpStatus=status;
    }
}
