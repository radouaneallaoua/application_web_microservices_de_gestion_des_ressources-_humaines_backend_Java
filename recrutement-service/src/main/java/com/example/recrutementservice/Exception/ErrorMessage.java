package com.example.recrutementservice.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
