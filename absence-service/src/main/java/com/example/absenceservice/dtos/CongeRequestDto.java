package com.example.absenceservice.dtos;

import com.example.absenceservice.enums.EtatConge;
import com.example.absenceservice.enums.Motif;
import com.example.absenceservice.enums.TypeConge;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CongeRequestDto {
    private String employeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeEnJours;
    private TypeConge typeConge;
    private EtatConge etatConge;

}
