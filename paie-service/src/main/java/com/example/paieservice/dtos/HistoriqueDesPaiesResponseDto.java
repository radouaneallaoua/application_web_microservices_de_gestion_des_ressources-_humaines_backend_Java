package com.example.paieservice.dtos;

import com.example.paieservice.enums.TypePaiement;
import com.example.paieservice.models.Employe;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class HistoriqueDesPaiesResponseDto {
    private String id;
    private double montant;
    private LocalDateTime datePaiement;
    private TypePaiement typePaiement;
    private String recu;
    private String employeId;
    private Employe employe;

}
