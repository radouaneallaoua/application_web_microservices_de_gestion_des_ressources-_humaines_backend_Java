package com.example.paieservice.entities;


import com.example.paieservice.dtos.HistoriqueDesPaiesResponseDto;
import com.example.paieservice.enums.TypePaiement;
import com.example.paieservice.models.Employe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class HistoriqueDesPaies {
    @Id
    private String id;
    private double montant;
    @DateTimeFormat
    private LocalDateTime datePaiement;
    private TypePaiement typePaiement;

    @Column(nullable = true)
    private String recu;
    private String employeId;

    @Transient
    private Employe employe;

    public HistoriqueDesPaiesResponseDto toDto(){
        return  HistoriqueDesPaiesResponseDto.builder()
                .datePaiement(datePaiement)
                .employe(employe)
                .montant(montant)
                .employeId(employeId)
                .id(id)
                .recu(recu)
                .typePaiement(typePaiement)
                .build();
    }
}
