package com.example.formationservice.dtos;

import com.example.formationservice.models.Employe;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeFormationResponseDto {
    private String employeId;
    private Long formationId;
    private LocalDateTime dateIntegration;
    private LocalDateTime dateFin;
    private int evaluation;
    private Employe employe;
}
