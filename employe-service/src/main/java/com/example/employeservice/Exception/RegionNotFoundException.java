package com.example.employeservice.Exception;

public class RegionNotFoundException extends RuntimeException{
    public RegionNotFoundException() {
    }

    public RegionNotFoundException(String message) {
        super(message);
    }
}
