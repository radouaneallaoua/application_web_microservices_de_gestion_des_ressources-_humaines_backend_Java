package com.example.formationservice.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntiteFormationRequestDto {
    private Long entiteId;
    private Long formationId;
    private LocalDateTime dateAjout;
    private LocalDateTime dateFin;
}
