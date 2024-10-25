package com.example.recrutementservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = OffreNotFoundException.class)
    public ResponseEntity<ErrorMessage> offreNotFound(OffreNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntretienEvaluationNotFoundException.class)
    public ResponseEntity<ErrorMessage> entretienEvaluationNotFound(EntretienEvaluationNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new  ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CondidatureNotFoundException.class)
    public ResponseEntity<ErrorMessage> condidatureNotFound(CondidatureNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return  new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ContratNotFoundException.class)
    public ResponseEntity<ErrorMessage> contratNotFound(ContratNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntretienNotFoundException.class)
    public ResponseEntity<ErrorMessage> entretienNotFound(EntretienNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmployeNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeNotFound(EmployeNotFoundException e){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
