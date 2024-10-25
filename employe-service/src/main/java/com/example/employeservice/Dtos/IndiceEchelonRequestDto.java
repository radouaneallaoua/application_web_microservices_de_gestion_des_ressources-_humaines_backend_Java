package com.example.employeservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndiceEchelonRequestDto {
    private int indice;
    private int echelon;
    private Long gradeId;

}
