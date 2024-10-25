package com.example.recrutementservice.entities;
import com.example.recrutementservice.Dtos.EntretienResponseDto;
import com.example.recrutementservice.enums.EtatEntretien;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;
    private LocalDateTime dateEntretien;
    @Enumerated(EnumType.STRING)
    private EtatEntretien etatEntretien;

    @ManyToOne
    private Condidat condidat;


    @OneToOne(mappedBy = "entretien",cascade = CascadeType.ALL,orphanRemoval = true)
    private EntretienEvaluation entretienEvaluation;

    public EntretienResponseDto toDto() {
        return EntretienResponseDto.builder()
                .condidatId(condidat.getId())
                .dateEntretien(dateEntretien)
                .etatEntretien(etatEntretien)
                .label(label)
                .description(description)
                .id(id)
                .evaluation(entretienEvaluation!=null?entretienEvaluation.toDto():null)
                .build();
    }
}
