package com.example.employeservice.Exception;

public class EntiteNotFoundException extends RuntimeException{
    public EntiteNotFoundException() {
    }

    public EntiteNotFoundException(String message) {
        super(message);
    }
}
