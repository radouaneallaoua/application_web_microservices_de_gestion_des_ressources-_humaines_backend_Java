package com.example.recrutementservice.Exception;

public class EmployeNotFoundException extends RuntimeException{
    public EmployeNotFoundException() {
    }

    public EmployeNotFoundException(String message) {
        super(message);
    }
}
