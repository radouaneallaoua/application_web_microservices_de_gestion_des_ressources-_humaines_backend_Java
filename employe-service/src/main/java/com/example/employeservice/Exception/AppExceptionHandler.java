package com.example.employeservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = EmployeNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeNotFound(EmployeNotFoundException exception){
        System.out.println("Exception caught: " + exception.getMessage());
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ErrorMessage> roleNotFound(RoleNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = IndiceEchelonNotFoundException.class)
    public ResponseEntity<ErrorMessage> indiceNotFound(IndiceEchelonNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RegionNotFoundException.class)
    public ResponseEntity<ErrorMessage> regionNotFound(RegionNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EntiteEmployeNotFoundException.class)
    public ResponseEntity<ErrorMessage> entiteEmployeNotFound(EntiteEmployeNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConjointNotFoundException.class)
    public ResponseEntity<ErrorMessage> conjointNotFound(ConjointNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MutuelleNotFoundException.class)
    public ResponseEntity<ErrorMessage> mutuelleNotFound(MutuelleNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = CategorieNotFoundException.class)
    public ResponseEntity<ErrorMessage> categorieNotFound(CategorieNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CadreNotFoundException.class)
    public ResponseEntity<ErrorMessage> cadreNotFound(CadreNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = CorpsNotFoundException.class)
    public ResponseEntity<ErrorMessage> corpsNotFound(CorpsNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = GradeNotFoundException.class)
    public ResponseEntity<ErrorMessage> gradeNotFound(GradeNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = CompteNotFoundException.class)
    public ResponseEntity<ErrorMessage> compteNotFound(CompteNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TypeNotFoundException.class)
    public ResponseEntity<ErrorMessage> typeNotFound(TypeNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotificationNotFoundException.class)
    public ResponseEntity<ErrorMessage> notificationNotFound(NotificationNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntiteNotFoundException.class)
    public ResponseEntity<ErrorMessage> entiteNotFound(EntiteNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EnfantNotFoundException.class)
    public ResponseEntity<ErrorMessage> enfantNotFound(EnfantNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PosteNotFoundException.class)
    public ResponseEntity<ErrorMessage> posteNotFound(PosteNotFoundException exception){
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .code(404)
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
