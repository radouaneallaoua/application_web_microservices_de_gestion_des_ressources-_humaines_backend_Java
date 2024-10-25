package com.example.employeservice.entities;

import com.example.employeservice.Dtos.EnfantResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enfant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @ManyToOne
    private Conjoint conjoint;

    @ManyToOne
    private Employe employe;

    public EnfantResponseDto toDto(){
        return  EnfantResponseDto.builder()
                .dateNaissance(dateNaissance)
                .nom(nom)
                .mereId(conjoint.getId())
                .employeId(employe.getId())
                .prenom(prenom)
                .id(id)
                .build();
    }
}
