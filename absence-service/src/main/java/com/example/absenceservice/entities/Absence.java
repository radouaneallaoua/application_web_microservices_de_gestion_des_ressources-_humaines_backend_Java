package com.example.absenceservice.entities;


import com.example.absenceservice.dtos.AbsenceResponseDto;
import com.example.absenceservice.enums.Motif;
import com.example.absenceservice.models.Employe;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeId;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeEnJours;
    private Motif motif;
    private boolean estJustifiee;

    @Transient
    private Employe employe;

    public AbsenceResponseDto toDto(){
        return  AbsenceResponseDto.builder()
                .absenceId(id)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .dureeEnJours(dureeEnJours)
                .employeId(employeId)
                .employe(employe)
                .estJustifiee(estJustifiee)
                .motif(motif)
                .build();
    }
}
