package com.example.recrutementservice.entities;


import com.example.recrutementservice.Dtos.CondidatResponseDto;
import com.example.recrutementservice.Dtos.ContratResponseDto;
import com.example.recrutementservice.Dtos.OffreResponseDto;
import com.example.recrutementservice.enums.TypeContrat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String poste;
    private String description;
    private LocalDate datePublication;
    private LocalDate dateLimiteCondidature;
    private String ville;
    private int nombreDePostes;
    private boolean estExpiree;

    @OneToMany(mappedBy = "offre",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Condidat> condidats;

    @ManyToMany()
    @JoinTable(name = "offre_contrat",joinColumns = @JoinColumn(name="offre_id"),inverseJoinColumns = @JoinColumn(name = "contrat_id"))
    private List<Contrat> typeContrats;

    public OffreResponseDto toDto(){
        List<CondidatResponseDto> dtos=new ArrayList<>();
        if(condidats!=null){
            condidats.forEach(c->{
                dtos.add(c.toDto());
            });
        }
        List<ContratResponseDto> contrats=new ArrayList<>();
        if(typeContrats!=null){
            typeContrats.forEach(t->{
                contrats.add(t.toDto());
            });
        }
        return  OffreResponseDto.builder()
                .offreId(id)
                .datePublication(datePublication)
                .description(description)
                .dateLimiteCondidature(dateLimiteCondidature)
                .nombreDePostes(nombreDePostes)
                .poste(poste)
                .titre(titre)
                .ville(ville)
                .contrats(contrats)
                .estExpiree(estExpiree)
                .condidats(dtos)
                .build();
    }

}
