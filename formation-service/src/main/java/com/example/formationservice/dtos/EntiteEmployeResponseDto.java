package com.example.formationservice.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntiteEmployeResponseDto {
    private Long id;
    private String employeId;
    private Long entiteId;
    private LocalDate dateDebut;
}
