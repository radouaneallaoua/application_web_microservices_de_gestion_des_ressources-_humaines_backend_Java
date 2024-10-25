package com.example.employeservice.Exception;

public class CategorieNotFoundException extends  RuntimeException{
    public CategorieNotFoundException() {
    }

    public CategorieNotFoundException(String message) {
        super(message);
    }
}
