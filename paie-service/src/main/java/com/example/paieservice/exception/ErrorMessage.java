package com.example.paieservice.exception;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor   @NoArgsConstructor @Getter @Setter
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
