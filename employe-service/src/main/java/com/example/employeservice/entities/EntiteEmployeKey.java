package com.example.employeservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class EntiteEmployeKey implements Serializable {
    @Column(name = "employe_id")
    private String employeId;

    @Column(name = "entite_id")
    private Long entiteId;
}
