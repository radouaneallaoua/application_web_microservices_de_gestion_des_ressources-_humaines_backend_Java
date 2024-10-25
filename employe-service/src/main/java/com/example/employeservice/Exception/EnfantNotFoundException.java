package com.example.employeservice.Exception;

public class EnfantNotFoundException extends RuntimeException{
    public EnfantNotFoundException() {
    }

    public EnfantNotFoundException(String message) {
        super(message);
    }
}
