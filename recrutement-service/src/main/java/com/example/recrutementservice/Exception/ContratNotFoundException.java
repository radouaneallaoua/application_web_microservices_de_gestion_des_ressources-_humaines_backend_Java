package com.example.recrutementservice.Exception;

public class ContratNotFoundException extends RuntimeException{
    public ContratNotFoundException() {
    }

    public ContratNotFoundException(String message) {
        super(message);
    }
}
