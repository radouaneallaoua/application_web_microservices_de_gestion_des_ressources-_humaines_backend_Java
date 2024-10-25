package com.example.formationservice.entities;

import com.example.formationservice.dtos.FormationResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @ToString @Builder @AllArgsConstructor @NoArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int dureeEnJours;
    private String prestataireFormation;
    @ManyToOne
    private Competence competence;

    @OneToMany(mappedBy = "formation",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EntiteFormation> entiteFormations;

    public FormationResponseDto toDto(){
        return FormationResponseDto.builder()
                .competenceId(competence.getId())
                .dateDebut(dateDebut)
                .id(id)
                .description(description)
                .prestataireFormation(prestataireFormation)
                .dureeEnJours(dureeEnJours)
                .label(label)
                .dateFin(dateFin)
                .build();
    }








}
