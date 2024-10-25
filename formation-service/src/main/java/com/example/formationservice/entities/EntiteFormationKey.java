package com.example.formationservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EntiteFormationKey implements Serializable {
    @Column(name = "entite_id")
    private Long entiteId;
    @Column(name = "formation_id")
    private Long formationId;
}
