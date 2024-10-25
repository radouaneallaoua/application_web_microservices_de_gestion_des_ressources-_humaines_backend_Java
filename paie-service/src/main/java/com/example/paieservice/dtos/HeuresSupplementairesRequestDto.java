package com.example.paieservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter @Setter
public class HeuresSupplementairesRequestDto {
    private int nombreHeures;
    private double montantUnitaire;
    private LocalDate date;
    private String employeId;
}
