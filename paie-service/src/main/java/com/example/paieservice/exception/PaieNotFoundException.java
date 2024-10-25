package com.example.paieservice.exception;

public class PaieNotFoundException extends RuntimeException{
    public PaieNotFoundException() {
    }

    public PaieNotFoundException(String message) {
        super(message);
    }
}
