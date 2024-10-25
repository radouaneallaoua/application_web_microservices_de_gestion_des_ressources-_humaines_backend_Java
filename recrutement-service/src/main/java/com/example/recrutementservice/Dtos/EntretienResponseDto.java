package com.example.recrutementservice.Dtos;


import com.example.recrutementservice.enums.EtatEntretien;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntretienResponseDto {
    private Long id;
    private String label;
    private LocalDateTime dateEntretien;
    private EtatEntretien etatEntretien;
    private String description;
    private Long condidatId;
    private EvaluationResponseDto evaluation;

}
