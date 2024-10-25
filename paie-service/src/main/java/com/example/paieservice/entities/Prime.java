package com.example.paieservice.entities;


import com.example.paieservice.dtos.PrimeResponseDto;
import com.example.paieservice.enums.TypePrime;
import com.example.paieservice.models.Employe;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Prime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double montant;
    private TypePrime typePrime;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String employeId;

    @Transient
    private Employe employe;

    public PrimeResponseDto toDto(){
        return  PrimeResponseDto.builder()
                .id(id)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .typePrime(typePrime)
                .description(description)
                .employe(employe)
                .employeId(employeId)
                .montant(montant)
                .build();
    }
}
