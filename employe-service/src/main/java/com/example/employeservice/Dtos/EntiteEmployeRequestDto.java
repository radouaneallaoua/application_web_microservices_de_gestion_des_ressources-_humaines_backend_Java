package com.example.employeservice.Dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntiteEmployeRequestDto {
    private String employeId;
    private Long entiteId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
