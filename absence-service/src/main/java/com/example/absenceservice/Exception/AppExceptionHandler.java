package com.example.absenceservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = CongeNotFoundException.class)
    public ResponseEntity<ErrorMessage> congeNotFound(CongeNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new  ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AbsenceNotFoundException.class)
    public ResponseEntity<ErrorMessage> absenceNotFound(AbsenceNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
