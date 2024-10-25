package com.example.employeservice.entities;

import com.example.employeservice.Dtos.MutuelleResponseDto;
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
public class Mutuelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employe_mutuelles",joinColumns = @JoinColumn(name = "mutuelle_id"),inverseJoinColumns = @JoinColumn(name="employe_id"))
    private List<Employe> employes;

    public MutuelleResponseDto toDto(){
        return  MutuelleResponseDto.builder()
                .label(label)
                .id(id)
                .build();
    }
}
