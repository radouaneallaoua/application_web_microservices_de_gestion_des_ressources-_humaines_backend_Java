package com.example.employeservice.entities;

import com.example.employeservice.Dtos.RegionResponseDto;
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
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Employe> employes;

    public RegionResponseDto toDto(){
        return  RegionResponseDto.builder()
                .id(id)
                .label(label)
                .build();
    }
}
