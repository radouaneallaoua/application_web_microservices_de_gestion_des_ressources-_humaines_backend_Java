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
public class EmployeFormationKey  implements Serializable {
    @Column(name = "employe_id")
    private String employeId;
    @Column(name = "formation_id")
    private Long formationId;
}
