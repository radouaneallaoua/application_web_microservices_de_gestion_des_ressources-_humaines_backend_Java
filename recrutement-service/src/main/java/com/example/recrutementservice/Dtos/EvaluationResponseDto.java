package com.example.recrutementservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvaluationResponseDto {
    private Long id;
    private int score;
    private String avis;
    private Long entretienId;
}
