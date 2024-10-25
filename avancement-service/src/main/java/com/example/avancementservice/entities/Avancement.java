package com.example.avancementservice.entities;

import com.example.avancementservice.dtos.AvancementResponseDto;
import com.example.avancementservice.enums.MotifAvancement;
import com.example.avancementservice.enums.TypeAvancement;
import com.example.avancementservice.models.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
public class Avancement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateAvancement;
    private TypeAvancement typeAvancement;
    private MotifAvancement motifAvancement;
    private Long nouveauGradeId;
    private Long nouveauIndiceEchelonId;
    private Long nouvelleEntiteId;
    private Long nouveauPosteId;
    private String employeId;

    @Transient
    private Employe employe;

    @Transient
    private Grade grade;

    @Transient
    private Poste poste;

    @Transient
    private IndiceEchelon indiceEchelon;

    @Transient
    private Entite entite;

    public AvancementResponseDto toDto(){
        return  AvancementResponseDto.builder()
                .employe(employe)
                .dateAvancement(dateAvancement)
                .grade(grade)
                .employeId(employeId)
                .entite(entite)
                .id(id)
                .indiceEchelon(indiceEchelon)
                .motifAvancement(motifAvancement)
                .nouveauGradeId(nouveauGradeId)
                .poste(poste)
                .nouveauIndiceEchelonId(nouveauIndiceEchelonId)
                .typeAvancement(typeAvancement)
                .posteId(nouveauPosteId)
                .nouvelleEntiteId(nouvelleEntiteId)
                .build();
    }



}
