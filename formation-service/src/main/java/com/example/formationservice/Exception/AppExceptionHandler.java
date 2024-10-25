package com.example.formationservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = FormationNotFoundException.class)
    public ResponseEntity<ErrorMessage> formationNotFound(FormationNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmployeFormationNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeFormationNotFound(EmployeFormationNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmployeFormationAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> employeFormationNotFound(EmployeFormationAlreadyExistsException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = CompetenceNotFoundException.class)
    public ResponseEntity<ErrorMessage> competenceNotFound(CompetenceNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntiteFormationNotFoundException.class)
    public ResponseEntity<ErrorMessage> entiteFormationNotFound(EntiteFormationNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
