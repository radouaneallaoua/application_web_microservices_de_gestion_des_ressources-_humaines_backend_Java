package com.example.employeservice.Dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConjointResponsetDto {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String employeId;
}
