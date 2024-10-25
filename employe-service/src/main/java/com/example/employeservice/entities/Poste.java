package com.example.employeservice.entities;

import com.example.employeservice.Dtos.PosteResponseDto;
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
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String label;
    private String description;

    @OneToMany(mappedBy = "poste")
    private List<Employe> employes;

    public PosteResponseDto toDto(){
        return  PosteResponseDto.builder()
                .description(description)
                .label(label)
                .id(id)
                .build();
    }

}
