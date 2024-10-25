package com.example.paieservice.exception;

public class PrimeNotFoundException extends  RuntimeException{
    public PrimeNotFoundException() {
    }

    public PrimeNotFoundException(String message) {
        super(message);
    }
}
