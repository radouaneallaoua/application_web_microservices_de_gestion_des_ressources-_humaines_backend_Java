package com.example.formationservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceRequestDto {
    private String label;
    private String description;
}
