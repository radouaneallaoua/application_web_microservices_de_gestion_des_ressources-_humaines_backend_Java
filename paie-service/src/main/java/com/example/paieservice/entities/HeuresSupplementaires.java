package com.example.paieservice.entities;


import com.example.paieservice.dtos.HeuresSupplementairesResponseDto;
import com.example.paieservice.models.Employe;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class HeuresSupplementaires {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nombreHeures;
    private double montantUnitaire;
    private LocalDate date;
    private String employeId;

    @Transient
    private Employe employe;

    public HeuresSupplementairesResponseDto toDto(){
        return  HeuresSupplementairesResponseDto.builder()
                .date(date)
                .id(id)
                .nombreHeures(nombreHeures)
                .montantUnitaire(montantUnitaire)
                .employe(employe)
                .employeId(employeId)
                .build();
    }
}
