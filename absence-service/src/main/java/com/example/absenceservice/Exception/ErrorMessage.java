package com.example.absenceservice.Exception;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
