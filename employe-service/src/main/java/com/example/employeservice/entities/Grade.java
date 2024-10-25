package com.example.employeservice.entities;


import com.example.employeservice.Dtos.GradeResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @ManyToOne
    private Cadre cadre;

    @OneToMany(mappedBy = "grade",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Employe> employes;

    @OneToMany(mappedBy = "grade",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<IndiceEchelon> indiceEchelons;

    public GradeResponseDto toDto(){
        return  GradeResponseDto.builder()
                .cadre(cadre.toDto())
                .label(label)
                .id(id)
                .build();
    }
}

