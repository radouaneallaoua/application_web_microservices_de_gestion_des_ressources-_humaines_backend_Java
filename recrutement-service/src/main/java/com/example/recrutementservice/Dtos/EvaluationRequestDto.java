package com.example.recrutementservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvaluationRequestDto {
    private int score;
    private Long entretienId;
    private String avis;
}
