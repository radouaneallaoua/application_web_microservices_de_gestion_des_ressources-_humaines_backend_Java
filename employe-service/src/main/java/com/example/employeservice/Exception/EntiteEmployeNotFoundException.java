package com.example.employeservice.Exception;

public class EntiteEmployeNotFoundException extends RuntimeException{
    public EntiteEmployeNotFoundException() {
    }

    public EntiteEmployeNotFoundException(String message) {
        super(message);
    }
}
