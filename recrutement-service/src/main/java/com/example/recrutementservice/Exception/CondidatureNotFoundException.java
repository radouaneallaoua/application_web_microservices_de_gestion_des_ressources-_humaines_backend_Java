package com.example.recrutementservice.Exception;

public class CondidatureNotFoundException extends RuntimeException{
    public CondidatureNotFoundException() {
    }

    public CondidatureNotFoundException(String message) {
        super(message);
    }
}
