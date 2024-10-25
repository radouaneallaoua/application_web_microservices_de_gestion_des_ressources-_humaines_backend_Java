package com.example.recrutementservice.entities;

import com.example.recrutementservice.Dtos.ContratResponseDto;
import com.example.recrutementservice.enums.TypeContrat;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeContrat typeContrat;

    @ManyToMany
    @JoinTable(name = "offre_contrat",joinColumns = @JoinColumn(name="contrat_id"),inverseJoinColumns = @JoinColumn(name = "offre_id"))
    private List<Offre> offres;

    public ContratResponseDto toDto(){
        return  ContratResponseDto.builder()
                .id(id)
                .typeContrat(typeContrat)
                .build();
    }
}
