package com.example.employeservice.Exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException() {
    }

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
