package com.example.recrutementservice.Dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntretienRequestDto {
    private String label;
    private LocalDateTime dateEntretien;
    private String description;
    private Long condidatId;
}
