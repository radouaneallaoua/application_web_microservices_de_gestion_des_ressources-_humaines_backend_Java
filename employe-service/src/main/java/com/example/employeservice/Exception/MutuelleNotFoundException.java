package com.example.employeservice.Exception;

public class MutuelleNotFoundException extends  RuntimeException{
    public MutuelleNotFoundException() {
    }

    public MutuelleNotFoundException(String message) {
        super(message);
    }
}
