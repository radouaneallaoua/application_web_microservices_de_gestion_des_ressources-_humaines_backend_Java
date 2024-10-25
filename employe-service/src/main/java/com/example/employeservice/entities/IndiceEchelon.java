package com.example.employeservice.entities;

import com.example.employeservice.Dtos.IndiceEchelonResponseDto;
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
public class IndiceEchelon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int indice;
    private int echelon;

    @ManyToOne
    private Grade grade;
    @OneToMany(mappedBy = "indiceEchelon")
    private List<Employe> employes;

    public IndiceEchelonResponseDto toDto(){
        return  IndiceEchelonResponseDto.builder()
                .echelon(echelon)
                .indice(indice)
                .gradeId(grade.getId())
                .id(id)
                .build();
    }


}

