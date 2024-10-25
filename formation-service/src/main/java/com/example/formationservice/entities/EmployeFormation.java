package com.example.formationservice.entities;

import com.example.formationservice.dtos.EmployeFormationResponseDto;
import com.example.formationservice.models.Employe;
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
public class EmployeFormation {
    @EmbeddedId
    private EmployeFormationKey id;
    private LocalDateTime dateIntegration;
    private LocalDateTime dateFin;
    private int evaluation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "formation_id")
    @MapsId("formationId")
    private Formation formation;

    @JoinColumn(name = "employe_id")
    @MapsId("employeId")
    @Transient
    private Employe employe;


    public EmployeFormationResponseDto toDto(){
        return  EmployeFormationResponseDto.builder()
                .formationId(formation.getId())
                .employeId(id.getEmployeId())
                .dateFin(dateFin)
                .employe(employe)
                .dateIntegration(dateIntegration)
                .evaluation(evaluation)
                .build();
    }


}
