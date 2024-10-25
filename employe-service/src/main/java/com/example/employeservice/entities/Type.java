package com.example.employeservice.entities;

import com.example.employeservice.Dtos.TypeResponseDto;
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
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Entite> entites;

    public TypeResponseDto toDto(){
        return  TypeResponseDto.builder()
                .id(id)
                .label(label)
                .build();
    }
}
