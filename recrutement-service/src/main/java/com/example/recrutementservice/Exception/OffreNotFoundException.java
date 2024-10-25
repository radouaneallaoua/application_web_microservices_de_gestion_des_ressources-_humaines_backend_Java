package com.example.recrutementservice.Exception;

public class OffreNotFoundException extends RuntimeException{
    public OffreNotFoundException() {
    }

    public OffreNotFoundException(String message) {
        super(message);
    }
}
