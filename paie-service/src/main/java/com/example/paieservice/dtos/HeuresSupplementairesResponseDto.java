package com.example.paieservice.dtos;

import com.example.paieservice.models.Employe;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter @Setter
public class HeuresSupplementairesResponseDto {
    private Long id;
    private int nombreHeures;
    private double montantUnitaire;
    private LocalDate date;
    private String employeId;
    private Employe employe;

}
