package com.example.absenceservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CadreResponseDto {
    private Long id;
    private String label;
    private CorpsResponseDto corps;
}
