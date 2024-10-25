package com.example.formationservice.Exception;

public class CompetenceNotFoundException extends RuntimeException {
    public CompetenceNotFoundException(String s) {
        super(s);
    }

    public CompetenceNotFoundException() {
    }
}
