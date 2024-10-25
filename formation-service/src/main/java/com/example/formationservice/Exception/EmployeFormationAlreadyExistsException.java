package com.example.formationservice.Exception;

public class EmployeFormationAlreadyExistsException extends RuntimeException {
    public EmployeFormationAlreadyExistsException(String s) {
        super(s);
    }

    public EmployeFormationAlreadyExistsException() {
    }
}
