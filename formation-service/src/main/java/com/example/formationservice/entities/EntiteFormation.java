package com.example.formationservice.entities;

import com.example.formationservice.dtos.EntiteFormationResponseDto;
import com.example.formationservice.models.Entite;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntiteFormation {
    @EmbeddedId
    private EntiteFormationKey id;
    private LocalDateTime dateAjout;
    private LocalDateTime dateFin;
    @ManyToOne
    @JoinColumn(name = "formation_id")
    @MapsId("formationId")
    private Formation formation;

    @JoinColumn(name = "entite_id")
    @MapsId("entiteId")
    @Transient
    private Entite entite;


    public EntiteFormationResponseDto toDto(){
        return  EntiteFormationResponseDto.builder()
                .formationId(formation.getId())
                .entiteId(id.getEntiteId())
                .dateAjout(dateAjout)
                .dateFin(dateFin)
                .build();
    }


}
