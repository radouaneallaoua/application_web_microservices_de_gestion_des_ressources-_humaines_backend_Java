package com.example.employeservice.entities;


import com.example.employeservice.Dtos.CategorieResponseDto;
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
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "categorie",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Corps> corps;

    public CategorieResponseDto toDto(){
        return  CategorieResponseDto.builder()
                .name(name)
                .id(id)
                .build();
    }
}
