package com.example.avancementservice.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndiceEchelon {
    private Long id;
    private int indice;
    private int echelon;
    private Long gradeId;
}
