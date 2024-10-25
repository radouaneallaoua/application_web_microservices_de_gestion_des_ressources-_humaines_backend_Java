package com.example.avancementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = AvancementNotFoundException.class)
    public ResponseEntity<ErrorMessage> avancementNotFound(AvancementNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
