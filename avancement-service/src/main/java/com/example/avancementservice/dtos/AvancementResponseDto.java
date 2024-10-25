package com.example.avancementservice.dtos;

import com.example.avancementservice.enums.MotifAvancement;
import com.example.avancementservice.enums.TypeAvancement;
import com.example.avancementservice.models.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AvancementResponseDto {
    private Long id;
    private LocalDate dateAvancement;
    private TypeAvancement typeAvancement;
    private MotifAvancement motifAvancement;
    private Long nouveauGradeId;
    private Long nouveauIndiceEchelonId;
    private Long nouvelleEntiteId;
    private Long posteId;
    private String employeId;
    private Employe employe;
    private Grade grade;
    private Poste poste;
    private IndiceEchelon indiceEchelon;
    private Entite entite;
}
