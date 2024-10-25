package com.example.absenceservice.Exception;

public class AbsenceNotFoundException extends  RuntimeException{
    public AbsenceNotFoundException() {
    }

    public AbsenceNotFoundException(String message) {
        super(message);
    }
}
