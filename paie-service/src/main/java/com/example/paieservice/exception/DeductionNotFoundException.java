package com.example.paieservice.exception;

public class DeductionNotFoundException extends RuntimeException{
    public DeductionNotFoundException() {
    }

    public DeductionNotFoundException(String message) {
        super(message);
    }
}
