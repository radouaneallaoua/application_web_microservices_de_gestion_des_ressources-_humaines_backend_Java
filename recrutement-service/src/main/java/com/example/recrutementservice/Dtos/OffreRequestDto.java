package com.example.recrutementservice.Dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OffreRequestDto {
    private String titre;
    private String poste;
    private String description;
    private String ville;
    private LocalDate dateLimiteCondidature;
    private int nombreDePostes;
    private List<Long> contratIds;

}
