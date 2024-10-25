package com.example.employeservice.Exception;

public class CorpsNotFoundException extends RuntimeException{
    public CorpsNotFoundException() {
    }

    public CorpsNotFoundException(String message) {
        super(message);
    }
}
