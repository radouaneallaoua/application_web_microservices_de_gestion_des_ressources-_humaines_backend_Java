package com.example.absenceservice.Exception;

public class CongeNotFoundException extends  RuntimeException{
    public CongeNotFoundException() {
    }

    public CongeNotFoundException(String message) {
        super(message);
    }
}
