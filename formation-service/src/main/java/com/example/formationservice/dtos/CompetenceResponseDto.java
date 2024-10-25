package com.example.formationservice.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceResponseDto {
    private Long id;
    private String label;
    private String description;

}
