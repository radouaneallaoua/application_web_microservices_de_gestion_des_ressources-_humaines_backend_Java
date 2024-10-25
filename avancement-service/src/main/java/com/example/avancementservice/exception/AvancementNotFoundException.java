package com.example.avancementservice.exception;

public class AvancementNotFoundException extends RuntimeException{
    public AvancementNotFoundException() {
    }

    public AvancementNotFoundException(String message) {
        super(message);
    }
}
