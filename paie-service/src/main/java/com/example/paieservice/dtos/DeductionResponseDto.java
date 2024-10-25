package com.example.paieservice.dtos;

import com.example.paieservice.enums.TypeDeduction;
import com.example.paieservice.models.Employe;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter @Setter
public class DeductionResponseDto {
    private Long id;
    private String description;
    private double montant;
    private TypeDeduction typeDeduction;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String employeId;
    private Employe employe;
}
