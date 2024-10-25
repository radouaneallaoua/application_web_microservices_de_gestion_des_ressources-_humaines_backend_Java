package com.example.employeservice.Dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndiceEchelonResponseDto {
    private Long id;
    private int indice;
    private int echelon;
    private Long gradeId;

}
