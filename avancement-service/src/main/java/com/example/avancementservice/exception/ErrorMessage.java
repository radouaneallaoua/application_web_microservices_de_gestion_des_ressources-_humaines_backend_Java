package com.example.avancementservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
