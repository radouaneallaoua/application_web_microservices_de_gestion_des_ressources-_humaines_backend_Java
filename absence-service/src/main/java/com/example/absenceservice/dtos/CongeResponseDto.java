package com.example.absenceservice.dtos;

import com.example.absenceservice.enums.EtatConge;
import com.example.absenceservice.enums.TypeConge;
import com.example.absenceservice.models.Employe;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CongeResponseDto {
    private Long congeId;
    private String employeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeEnJours;
    private TypeConge typeConge;
    private EtatConge etatConge;
    private Employe employe;

}
