package com.example.formationservice.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormationRequestDto {
    private String label;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeEnJours;
    private String prestataireFormation;
    private Long competenceId;
}
