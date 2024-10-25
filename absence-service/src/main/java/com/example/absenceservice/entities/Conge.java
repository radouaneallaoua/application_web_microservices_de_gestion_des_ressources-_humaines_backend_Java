package com.example.absenceservice.entities;


import com.example.absenceservice.dtos.CongeResponseDto;
import com.example.absenceservice.enums.EtatConge;
import com.example.absenceservice.enums.Motif;
import com.example.absenceservice.enums.TypeConge;
import com.example.absenceservice.models.Employe;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeEnJours;
    private EtatConge etatConge;
    private TypeConge typeConge;

    @Transient
    private Employe employe;

    public CongeResponseDto toDto(){

        return  CongeResponseDto.builder()
                .congeId(id)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .etatConge(etatConge)
                .dureeEnJours(dureeEnJours)
                .typeConge(typeConge)
                .employeId(employeId)
                .employe(employe)
                .build();
    }
}
