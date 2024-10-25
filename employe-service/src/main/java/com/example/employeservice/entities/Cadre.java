package com.example.employeservice.entities;

import com.example.employeservice.Dtos.CadreResponseDto;
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
public class Cadre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @ManyToOne
    private Corps corps;

    @OneToMany(mappedBy = "cadre",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Grade> grades;

    public CadreResponseDto toDto(){
        return  CadreResponseDto.builder()
                .corps(corps.toDto())
                .label(label)
                .id(id)
                .build();
    }
}
