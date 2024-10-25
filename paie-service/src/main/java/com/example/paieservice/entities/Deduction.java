package com.example.paieservice.entities;
import com.example.paieservice.dtos.DeductionResponseDto;
import com.example.paieservice.enums.TypeDeduction;
import com.example.paieservice.models.Employe;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double montant;
    private TypeDeduction typeDeduction;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String employeId;

    @Transient
    private Employe employe;

    public DeductionResponseDto toDto(){
        return  DeductionResponseDto.builder()
                .id(id)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .typeDeduction(typeDeduction)
                .description(description)
                .employe(employe)
                .employeId(employeId)
                .montant(montant)
                .build();
    }
}
