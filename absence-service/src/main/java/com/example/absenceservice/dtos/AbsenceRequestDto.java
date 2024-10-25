package com.example.absenceservice.dtos;

import com.example.absenceservice.enums.Motif;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AbsenceRequestDto {
    private String employeId;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeEnJours;
    private Motif motif;
    private boolean estJustifiee;

}
