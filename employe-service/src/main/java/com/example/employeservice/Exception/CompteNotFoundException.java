package com.example.employeservice.Exception;

public class CompteNotFoundException extends RuntimeException{
    public CompteNotFoundException() {
    }

    public CompteNotFoundException(String message) {
        super(message);
    }
}
