package com.example.employeservice.entities;

import com.example.employeservice.Dtos.ConjointResponsetDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conjoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @ManyToOne
    private Employe employe;

    @OneToMany(mappedBy = "conjoint")
    private List<Enfant> enfants;

    public ConjointResponsetDto toDto(){
        return ConjointResponsetDto.builder()
                .dateNaissance(dateNaissance)
                .employeId(employe.getId())
                .nom(nom)
                .prenom(prenom)
                .id(id)
                .build();
    }
}
