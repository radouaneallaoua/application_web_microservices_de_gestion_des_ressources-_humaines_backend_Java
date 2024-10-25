package com.example.recrutementservice.Dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OffreResponseDto {
    private Long offreId;
    private String titre;
    private String poste;
    private String description;
    private LocalDate datePublication;
    private boolean estExpiree;
    private String ville;
    private int nombreDePostes;
    private LocalDate dateLimiteCondidature;
    private List<CondidatResponseDto> condidats;
    private List<ContratResponseDto> contrats;


}
