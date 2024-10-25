package com.example.absenceservice.dtos;

import com.example.absenceservice.enums.Motif;
import com.example.absenceservice.models.Employe;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AbsenceResponseDto {
    private Long absenceId;
    private String employeId;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeEnJours;
    private Motif motif;
    private boolean estJustifiee;
    private Employe employe;

}
