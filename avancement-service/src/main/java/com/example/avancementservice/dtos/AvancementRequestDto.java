package com.example.avancementservice.dtos;

import com.example.avancementservice.enums.MotifAvancement;
import com.example.avancementservice.enums.TypeAvancement;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AvancementRequestDto {
    private LocalDate dateAvancement;
    private TypeAvancement typeAvancement;
    private MotifAvancement motifAvancement;
    private Long nouveauGradeId;
    private Long nouveauIndiceEchelonId;
    private Long nouvelleEntiteId;
    private Long posteId;
    private String employeId;
}
