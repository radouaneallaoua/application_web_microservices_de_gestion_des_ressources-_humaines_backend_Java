package com.example.employeservice.Exception;

public class TypeNotFoundException extends RuntimeException{
    public TypeNotFoundException() {
    }

    public TypeNotFoundException(String message) {
        super(message);
    }
}
