package com.example.formationservice.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormationResponseDto {
    private Long id;
    private String label;
    private String description;
    private String prestataireFormation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeEnJours;
    private Long competenceId;

}