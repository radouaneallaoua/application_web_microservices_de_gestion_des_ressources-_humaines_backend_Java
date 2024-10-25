package com.example.employeservice.entities;
import com.example.employeservice.Dtos.EntiteEmployeResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class EntiteEmploye {
    @EmbeddedId
    private EntiteEmployeKey id;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    @MapsId("employeId")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "entite_id")
    @MapsId("entiteId")
    private Entite entite;

    public EntiteEmployeResponseDto toDto(){
        return  EntiteEmployeResponseDto.builder()
                .entiteId(entite.getId())
                .employeId(employe.getId())
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .build();
    }
}

