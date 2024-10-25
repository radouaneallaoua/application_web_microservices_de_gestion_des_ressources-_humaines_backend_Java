package com.example.formationservice.Exception;

public class FormationNotFoundException  extends  RuntimeException{
    public FormationNotFoundException(String message) {
        super(message);
    }

    public FormationNotFoundException() {
    }
}
