package com.example.recrutementservice.entities;

import com.example.recrutementservice.Dtos.EvaluationResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class EntretienEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avis;
    private int score;

    @OneToOne
    private Entretien entretien;

    public EvaluationResponseDto toDto(){
        return  EvaluationResponseDto.builder()
                .entretienId(entretien.getId())
                .score(score)
                .avis(avis)
                .id(id)
                .build();
    }


}
