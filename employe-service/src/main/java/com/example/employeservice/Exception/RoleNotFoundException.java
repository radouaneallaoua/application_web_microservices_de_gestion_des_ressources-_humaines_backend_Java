package com.example.employeservice.Exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
