package com.example.employeservice.Exception;


public class EmployeNotFoundException extends RuntimeException{
    public EmployeNotFoundException() {
    }
    public EmployeNotFoundException(String message) {
        super(message);
    }
}
