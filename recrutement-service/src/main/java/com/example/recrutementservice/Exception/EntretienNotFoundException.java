package com.example.recrutementservice.Exception;

public class EntretienNotFoundException extends RuntimeException{
    public EntretienNotFoundException() {
    }

    public EntretienNotFoundException(String message) {
        super(message);
    }
}
