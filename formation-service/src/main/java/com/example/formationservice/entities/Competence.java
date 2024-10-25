package com.example.formationservice.entities;

import com.example.formationservice.dtos.CompetenceResponseDto;

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
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;

    @OneToMany(mappedBy = "competence")
    private List<Formation> formations;


    public CompetenceResponseDto toDto(){
        return  CompetenceResponseDto.builder()
                .description(description)
                .label(label)
                .id(id)
                .build();
    }
}
