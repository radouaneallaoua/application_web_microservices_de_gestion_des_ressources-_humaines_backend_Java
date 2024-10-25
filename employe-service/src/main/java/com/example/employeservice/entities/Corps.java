package com.example.employeservice.entities;


import com.example.employeservice.Dtos.CorpsResponseDto;
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
public class Corps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "corps",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Cadre> cadres;


    public CorpsResponseDto toDto(){
        return  CorpsResponseDto.builder()
                .categorie(categorie.toDto())
                .label(label)
                .id(id)
                .build();
    }
}
