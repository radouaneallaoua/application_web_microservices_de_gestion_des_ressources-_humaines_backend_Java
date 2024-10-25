package com.example.recrutementservice.entities;


import com.example.recrutementservice.Dtos.CondidatResponseDto;
import com.example.recrutementservice.Dtos.EntretienResponseDto;
import com.example.recrutementservice.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class Condidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private String ville;
    private String email;
    private String motivation;
    private LocalDateTime dateCondidature;
    private String CV;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne
    private Offre offre;

    @OneToMany(mappedBy = "condidat",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Entretien> entretiens;


    public CondidatResponseDto toDto() {
        List<EntretienResponseDto> responseDtos=new ArrayList<>();
        if(entretiens!=null){
            entretiens.forEach(e->{
                responseDtos.add(e.toDto());
            });
        }
        return CondidatResponseDto.builder()
                .adresse(adresse)
                .CV(CV)
                .email(email)
                .offreId(offre.getId())
                .id(id)
                .tel(tel)
                .ville(ville)
                .nom(nom)
                .motivation(motivation)
                .genre(genre)
                .dateCondidature(dateCondidature)
                .prenom(prenom)
                .entretiens(responseDtos)
                .build();
    }
}
