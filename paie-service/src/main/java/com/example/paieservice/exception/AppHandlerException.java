package com.example.paieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppHandlerException{

    @ExceptionHandler(value = PaieNotFoundException.class)
    public ResponseEntity<ErrorMessage> paieNotFound(PaieNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PrimeNotFoundException.class)
    public ResponseEntity<ErrorMessage> primeNotFound(PrimeNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DeductionNotFoundException.class)
    public ResponseEntity<ErrorMessage> deductionNotFound(DeductionNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return  new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HeuresSuppNotFoundException.class)
    public ResponseEntity<ErrorMessage> heuresNotFound(HeuresSuppNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
