package com.example.formationservice.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeFormationRequestDto {
    private String employeId;
    private Long formationId;
    private LocalDateTime dateIntegration;
    private LocalDateTime dateFin;
    private int evaluation;
}
