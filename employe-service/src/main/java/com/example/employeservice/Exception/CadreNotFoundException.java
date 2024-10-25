package com.example.employeservice.Exception;

public class CadreNotFoundException extends RuntimeException{
    public CadreNotFoundException() {
    }

    public CadreNotFoundException(String message) {
        super(message);
    }
}
