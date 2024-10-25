package com.example.employeservice.Exception;

public class GradeNotFoundException extends  RuntimeException{
    public GradeNotFoundException() {
    }

    public GradeNotFoundException(String message) {
        super(message);
    }
}
