package com.example.employeservice.Dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnfantRequestDto {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Long mereId;
    private String employeId;
}