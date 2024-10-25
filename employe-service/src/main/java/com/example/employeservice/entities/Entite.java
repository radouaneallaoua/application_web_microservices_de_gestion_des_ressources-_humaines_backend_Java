package com.example.employeservice.entities;

import com.example.employeservice.Dtos.EntiteResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @OneToMany(mappedBy = "entiteMere",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Entite> sousEntites;

    @ManyToOne
    private Entite entiteMere;

    @ManyToOne
    private Type type;

    @OneToMany(mappedBy = "entite",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EntiteEmploye> entiteEmployes;

    public EntiteResponseDto toDto(){
        return EntiteResponseDto.builder()
                .entiteMereId(entiteMere==null?null:entiteMere.getId())
                .name(label)
                .id(id)
                .typeId(type.getId())
                .build();
    }
}
